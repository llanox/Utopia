package co.edu.udea.ludens.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.ludens.dao.GameDAO;
import co.edu.udea.ludens.dao.PlayerDAO;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.UnexpectedEvent;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.exceptions.InvalidNumberOfFactorsException;
import co.edu.udea.ludens.exceptions.InvalidNumberOfMaterialsException;
import co.edu.udea.ludens.exceptions.InvalidNumberOfPlayersException;
import co.edu.udea.ludens.exceptions.ThereisnotPopulationException;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.UserService;
import co.edu.udea.ludens.util.ConstantsLudens;
import co.edu.udea.ludens.util.GameRequirement;
import co.edu.udea.ludens.util.UtopiaUtil;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class GameServiceImpl implements GameService {
	private Logger logger = Logger.getLogger(GameServiceImpl.class);

	@Autowired
	private GameDAO gameDao;

	@Autowired
	private PlayerDAO playerDao;

	@Autowired
	private UserService userService;

	@Autowired
	private PlayerService playerService;

	public GameServiceImpl(GameDAO gameDao, PlayerDAO playerDao) {
		this.gameDao = gameDao;
		this.playerDao = playerDao;
	}

	public GameServiceImpl() {
	}

	@Override
	public Game findByUserLogin(String userName) {
		Player player = playerDao.findPlayerByUserName(userName);
		Game game = player.getGame();

		return game;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Game save(Game game) {

		return (Game) gameDao.saveOrUpdate(game);
	}

	public void setGameDao(GameDAO gameDao) {
		this.gameDao = gameDao;
	}

	public GameDAO getGameDao() {

		return (this.gameDao);
	}

	@Override
	public List<Game> findAllGames() {
		List<Game> games = gameDao.findAllGames();

		return games;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(Game game) {
		List<Player> players = game.getPlayers();

		for (Player player : players) {
			User user = userService.findUser(player.getUser().getLogin());
			user.setParticipatingInGame(false);
			playerService.delete(player);
			userService.save(user);
		}
		this.gameDao.delete(game);
	}

	@Override
	public Game findGameByName(String gameName) {
		Game game = this.gameDao.findGameByName(gameName);

		return game;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void removePlayer(Game game, String login) {
		logger.info("User Service" + userService);
		logger.info("Player Service" + userService);
		User user = userService.findUser(login);
		user.setParticipatingInGame(false);
		Player player = playerService.findPlayer(login);
		playerService.delete(player);
		userService.save(user);
		game.getPlayers().remove(login);
		gameDao.saveOrUpdate(game);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void removePlayer(Game game, Player player) {
		User user = userService.findUser(player.getUser().getLogin());
		user.setParticipatingInGame(false);
		playerService.delete(player);
		userService.save(user);
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {

		return (this.userService);
	}

	public PlayerService getPlayerService() {

		return (this.playerService);
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public void setPlayerDao(PlayerDAO playerDao) {
		this.playerDao = playerDao;
	}

	public PlayerDAO getPlayerDao() {

		return (this.playerDao);
	}

	@Override
	public void meetRequirements(Game game) {
		logger.info("meeting Requirements");

		for (GameRequirement req : gameRequirements) {
			req.verify(game);
		}
	}

	private GameRequirement[] gameRequirements = { new AtLeastNPlayers(),
			new AtLeastNMaterial(), new AtLeastNFactors(),
			new AtLeastNPopulations() };

	public class AtLeastNPlayers implements GameRequirement {
		public static final int AT_LEAST_N_PLAYERS = 2;

		@Override
		public void verify(Game game) {
			List<Player> players = playerDao.findAllPlayersByGameName(true,
					game.getName());

			if (players == null)
				throw new InvalidNumberOfPlayersException(
						"Se debe tener al menos "
								+ AT_LEAST_N_PLAYERS
								+ " jugadores matriculados en el juego. Se tiene en cambio "
								+ 0 + " jugadores");

			if (players.isEmpty() || players.size() < AT_LEAST_N_PLAYERS) {
				logger.debug("Jugadores " + players.size());
				throw new InvalidNumberOfPlayersException(
						"Se debe tener al menos "
								+ AT_LEAST_N_PLAYERS
								+ " jugadores matriculados en el juego. Se tiene en cambio "
								+ players.size() + " jugadores");
			}
		}
	}

	public class AtLeastNMaterial implements GameRequirement {
		public static final int ALEAST_N_MATERIALS = 1;

		@Override
		public void verify(Game game) {

			int counter = 0;
			List<Incrementable> incrementables = game
					.getDefaultIncrementables();

			logger.info("incrementables --> " + incrementables);

			if (incrementables == null) {
				throw new InvalidNumberOfMaterialsException("No hay materiales");
			}

			for (Incrementable material : incrementables) {
				if (material.getType() == EnumElementType.MATERIAL)
					counter++;
			}

			if (counter < ALEAST_N_MATERIALS) {
				throw new InvalidNumberOfMaterialsException("Se tiene "
						+ counter + " materiales y se debe tener al menos "
						+ ALEAST_N_MATERIALS
						+ " materiales definidos en el juego");
			}
		}
	}

	public class AtLeastNFactors implements GameRequirement {
		public static final int ALEAST_N_FACTORS = 1;

		@Override
		public void verify(Game game) {

			int counter = 0;
			List<Incrementable> incrementables = game
					.getDefaultIncrementables();

			if (incrementables == null) {
				throw new InvalidNumberOfFactorsException(
						"No hay factores definidos");
			}

			for (Incrementable material : incrementables) {
				if (material.getType() == EnumElementType.FACTOR)
					counter++;
			}

			if (counter < ALEAST_N_FACTORS) {
				throw new InvalidNumberOfFactorsException(
						"Se debe tener al menos " + ALEAST_N_FACTORS
								+ " factores definidos en el juego");
			}
		}
	}

	public class AtLeastNPopulations implements GameRequirement {
		public static final int ALEAST_N_POPULATIONS = 1;

		@Override
		public void verify(Game game) {
			int counter = 0;
			List<Incrementable> incrementables = game
					.getDefaultIncrementables();

			if (incrementables == null) {
				throw new ThereisnotPopulationException(
						"No hay población definida");
			}

			for (Incrementable material : incrementables) {
				if (material.getType() == EnumElementType.POPULATION)
					counter++;
			}

			if (counter < ALEAST_N_POPULATIONS) {
				throw new ThereisnotPopulationException(
						"Se debe tener al menos " + ALEAST_N_POPULATIONS
								+ " población definida en el juego");
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
	public void generateUnexpectedEvents(Game game) {
		List<Incrementable> incrementables = game.getDefaultIncrementables();
		List<UnexpectedEvent> unexpectedEvents = game.getUnexpectedEvents();

		logger.info("Unexpected events generated " + unexpectedEvents);

		for (Incrementable incr : incrementables) {

			logger.debug("Generating unexpected event for incrementable... "
					+ incr.getName());

			if (incr.getType() == EnumElementType.FACTOR
					|| incr.getType() == EnumElementType.MATERIAL) {
				UnexpectedEvent good = new UnexpectedEvent();
				UnexpectedEvent bad = new UnexpectedEvent();

				good.setElementName(incr.getName());
				int quantity = UtopiaUtil.generateNumberBetweenRange(
						ConstantsLudens.LOWER_THRESHOLD,
						ConstantsLudens.UPPER_THRESHOLD);
				good.setQuantity(quantity);
				good.setMessage("Evento Fortuito: " + incr.getName()
						+ " ha sido afectado positivamente en un "
						+ good.getQuantity() + " % ");
				logger.debug("Evento Fortuito: " + incr.getName()
						+ " ha sido afectado positivamente en un "
						+ good.getQuantity() + " % ");

				bad.setElementName(incr.getName());
				logger.debug("Evento Fortuito: " + incr.getName()
						+ " ha sido afectado negativamente en un "
						+ good.getQuantity() + " % ");
				bad.setMessage("Evento Fortuito: " + incr.getName()
						+ " ha sido afectado negativamente en un "
						+ good.getQuantity() + " % ");
				quantity = UtopiaUtil.generateNumberBetweenRange(
						ConstantsLudens.LOWER_THRESHOLD,
						ConstantsLudens.UPPER_THRESHOLD);
				bad.setQuantity(quantity);

				unexpectedEvents.add(bad);
				unexpectedEvents.add(good);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void addPlayer(Game game, Player player) {
		player.getUser().setParticipatingInGame(true);
		player.setGame(game);
		playerDao.saveOrUpdate(player);
	}
}
