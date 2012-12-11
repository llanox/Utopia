package co.edu.udea.ludens.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.ludens.dao.GameDAO;
import co.edu.udea.ludens.dao.PlayerDAO;
import co.edu.udea.ludens.dao.UserDAO;
import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.util.UtopiaUtil;

@Service()
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class PlayerServiceImpl implements PlayerService {

	@Autowired()
	private PlayerDAO playerDao;

	@Autowired()
	private GameDAO gameDao;

	@Autowired()
	private UserDAO userDao;

	private Logger logger = Logger.getLogger(getClass());

	@Override()
	public Player findPlayer(String userName) {
		Player player = playerDao.findPlayerByUserName(userName);

		return player;
	}

	public void setPlayerDao(PlayerDAO playerDao) {
		this.playerDao = playerDao;
	}

	public PlayerDAO getPlayerDao() {

		return (this.playerDao);
	}

	@Override()
	public List<Player> findAllPlayersByGameName(String gameName) {
		List<Player> players = playerDao.findAllPlayersByGameName(gameName);

		return players;
	}

	@Override()
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public Player save(Player player) {
		playerDao.saveOrUpdate(player);

		return (player);
	}

	public void setGameDao(GameDAO gameDao) {
		this.gameDao = gameDao;
	}

	public GameDAO getGameDao() {

		return (this.gameDao);
	}

	@Override()
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(Player player) {
		playerDao.delete(player);
	}

	@Override()
	public List<Player> findAllPlayers() {

		return (playerDao.findAllPlayers());
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public UserDAO getUserDao() {

		return (this.userDao);
	}

	@Override()
	public List<Player> findAllPlayersByGameName(boolean participatingInGame,
			String gameName) {
		List<Player> players = playerDao.findAllPlayersByGameName(
				participatingInGame, gameName);

		return players;
	}

	@Override()
	public boolean meetReqToGettingStart(Player player) {
		List<Element> elements = new ArrayList<Element>();

		elements.addAll(player.getMaterials());
		elements.addAll(player.getDevelopmentFactors());
		System.out.println("Elements " + elements.size());

		boolean elementsOk = isHigherThanRequired(elements);
		System.out.println("Elements OK? " + elementsOk);

		return elementsOk;
	}

	private boolean isHigherThanRequired(List<Element> factors) {
		for (Element el : factors) {
			Integer level = el.getLevel();

			if (level >= UtopiaUtil.LEVEL_TO_GETTING_START) {
				logger.info("level " + level + " element "
						+ el.getIncrementable().getName() + " quantity "
						+ el.getQuantity());

				return true;
			}
		}

		return false;
	}

	@Override()
	public Element getElementPlayerByName(List<Element> elementList, String name) {
		for (Element element : elementList) {
			if (element.getIncrementable().getName().equals(name)) {

				return element;
			}
		}

		return null;
	}

	@Override()
	public void checkOutResources(List<IncrementableConstraint> levelResources,
			Element element, Player player) throws LudensException {
		boolean checked = true;
		StringBuffer bf = new StringBuffer();

		for (IncrementableConstraint pk : levelResources) {
			Integer neededQuantity = pk.getQuantity();
			String resourceName = pk.getElementName();
			Element resource = this.getElementPlayerByName(
					player.getElements(), resourceName);
			int compare = resource.getQuantity() - neededQuantity;

			if (compare < 0) {
				checked = false;
				bf.append(resource.getIncrementable().getName()).append(", ");
			}
		}

		if (!checked) {
			throw new LudensException(EnumMsgs.INSUFFICIENT_RESOURCE, bf,
					element.getIncrementable().getName(),
					(element.getLevel() + 1));
		}

	}

	@Override()
	public List<IncrementableConstraint> getIncrementableConstraintByLevel(
			List<IncrementableConstraint> levelConstraints, Integer newLevel) {
		List<IncrementableConstraint> list = new ArrayList<IncrementableConstraint>();

		for (IncrementableConstraint ic : levelConstraints) {
			if (ic.getConstrainedLevel().equals(newLevel)) {
				list.add(ic);
			}
		}

		return (list);
	}

	@Override()
	public List<Element> getElementsByName(List<Element> elements, String name) {
		List<Element> list = new ArrayList<Element>();

		for (Element e : elements) {
			if (e.getIncrementable().getName().equals(name)) {
				list.add(e);
			}
		}

		return (list);
	}

	@Override()
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void releasePlayersGame(String gameName) {
		List<Player> players = this.playerDao
				.findAllPlayersByGameName(gameName);

		User user = null;
		for (Player player : players) {
			user = player.getUser();
			user.setParticipatingInGame(Boolean.FALSE);
			this.userDao.saveOrUpdate(user);

			this.playerDao.delete(player);
		}
	}
}
