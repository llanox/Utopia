package co.edu.udea.ludens.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Game;

import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.enums.EnumUserRole;
import co.edu.udea.ludens.services.FirstRunConfiguration;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.IncrementableService;
import co.edu.udea.ludens.services.UserService;

public class FirstRunConfigurationImpl implements FirstRunConfiguration {

	@Autowired
	private UserService userService;

	@Autowired
	private GameService gameService;

	// @Autowired
	// private PlayerService playerService;

	@Autowired
	private IncrementableService incrementableService;
	private static boolean EXECUTED_CONFIGURATION = false;
	private Logger logger = Logger.getLogger(getClass());
	private String TEST_GAME_NAME = "JuegoPrueba";

	@Override
	public void createDefaultUsers() {
		User admin = userService.findUser("admin");

		if (admin == null) {
			logger.debug("Creating admin user ......");
			admin = new User();
			admin.setEmail("correo.del.llanox@gmail.com");
			admin.setName("EL q' manda");
			admin.setLogin("admin");
			admin.setPassword("admin");
			admin.setRole(EnumUserRole.ADMIN);

			userService.save(admin);
			logger.info("creando usuario admin");
		} else {
			logger.info("usuario admin ya existe");
		}
	}

	@Override
	public Game createDefaultGame() {
		Game game = gameService.findGameByName(TEST_GAME_NAME);

		if (game == null) {
			game = new Game();
			game.setName(TEST_GAME_NAME);

			game.setDuration(1000 * 60 * 60);
			game.setProductionTime(1000 * 60 * 60 * 15);

			game.setStatus(EnumGameStatus.NO_STARTED);
			gameService.save(game);
		}

		return game;
	}

	@Override
	public void loadAllDefaultConfiguration() {
		if (!EXECUTED_CONFIGURATION) {
			logger.info("Execute one time");
			createDefaultUsers();
			createDefaultGame();

			EXECUTED_CONFIGURATION = true;
		} else {
			logger.info("Execute two time");
		}
	}

	// It would be deleted

	// private void createDefaultPlayers() {
	//
	// List<User> users = userService.findAllUsers();
	// Game game = gameService.findGameByName(TEST_GAME_NAME);
	//
	// Player player = null;
	//
	//
	// for (User user : users) {
	//
	// player = new Player();
	//
	// if (EnumUserRole.ADMIN == user.getRole()) {
	// continue;
	// }
	//
	// player.setUser(user);
	// player.setStartTime(0l);
	// logger.info("User "+user);
	// String login = user.getLogin();
	// logger.info("Login "+login);
	// game.getPlayers().add(player);
	// playerService.save(player);
	// gameService.save(game);
	//
	// }
	//
	// }

	// /**
	// * @param gameService
	// * the gameService to set
	// */
	// public void setGameService(GameService gameService) {
	// this.gameService = gameService;
	// }
	//
	// /**
	// * @return the gameService
	// */
	// public GameService getGameService() {
	// return gameService;
	// }
	//
	// /**
	// * @param playerService
	// * the playerService to set
	// */
	// public void setPlayerService(PlayerService playerService) {
	// this.playerService = playerService;
	// }
	//
	// /**
	// * @return the playerService
	// */
	// public PlayerService getPlayerService() {
	// return playerService;
	// }
	//
	// /**
	// * @param incrementableService
	// * the incrementableService to set
	// */
	// public void setIncrementableService(
	// IncrementableService incrementableService) {
	// this.incrementableService = incrementableService;
	// }
	//
	// /**
	// * @return the incrementableService
	// */
	// public IncrementableService getIncrementableService() {
	// return incrementableService;
	// }
}
