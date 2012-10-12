package co.edu.udea.ludens.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.ludens.dao.GameDAO;
import co.edu.udea.ludens.dao.PlayerDAO;
import co.edu.udea.ludens.dao.UserDAO;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.services.PlayerService;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDAO playerDao;

	@Autowired
	private GameDAO gameDao;

	@Autowired
	private UserDAO userDao;

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public Player findPlayer(String userName) {
		Player player = playerDao.findPlayerByUserName(userName);

		return player;
	}

	/**
	 * @param playerDao
	 *            the playerDao to set
	 */
	public void setPlayerDao(PlayerDAO playerDao) {
		this.playerDao = playerDao;
	}

	/**
	 * @return the playerDao
	 */
	public PlayerDAO getPlayerDao() 

		return (this.playerDao);
	}

	@Override
	public List<Player> findAllPlayersByGameName(String gameName) {
		List<Player> players = playerDao.findAllPlayersByGameName(gameName);

		return players;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public Player save(Player player) {
		playerDao.saveOrUpdate(player);

		return (player);
	}

	/**
	 * @param gameDao
	 *            the gameDao to set
	 */
	public void setGameDao(GameDAO gameDao) {
		this.gameDao = gameDao;
	}

	/**
	 * @return the gameDao
	 */
	public GameDAO getGameDao() {

		return (this.gameDao);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(Player player) {
		playerDao.delete(player);

	}

	@Override
	public List<Player> findAllPlayers() {

		return (playerDao.findAllPlayers());
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * @return the userDao
	 */
	public UserDAO getUserDao() {

		return (this.userDao);
	}

	@Override
	public List<Player> findAllPlayersByGameName(boolean participatingInGame,
			String gameName) {
		List<Player> players = playerDao.findAllPlayersByGameName(
				participatingInGame, gameName);

		return players;
	}
}
