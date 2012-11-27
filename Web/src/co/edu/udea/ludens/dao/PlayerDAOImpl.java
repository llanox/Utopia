package co.edu.udea.ludens.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import co.edu.udea.ludens.domain.Player;

@Repository
public class PlayerDAOImpl extends ObjectDBDAO implements PlayerDAO {
	public Logger logger = Logger.getLogger(this.getClass());

	@Override()
	@SuppressWarnings("unchecked")
	public Player findPlayerByUserName(final String userName) {

		List<Player> players = (List<Player>) this.findObjectByAttribute(
				Player.class, "user.login", userName);
		Player player = null;

		if (players != null & !players.isEmpty())
			player = players.get(0);

		return player;
	}

	@Override()
	@SuppressWarnings("unchecked")
	public List<Player> findAllPlayersByGameName(String gameName) {

		// Game game = null;
		// List<Game> games = new ArrayList<Game>();
		// String SQL = "SELECT o FROM " + clazz.getName() + " o  WHERE o.name "
		// + " LIKE '%" + gameName + "'";
		// CriteriaQuery<Object> query =
		// entityManager.getCriteriaBuilder().createQuery();
		// TypedQuery<Game> q2 = entityManager.createQuery(SQL, clazz);

		List<Player> players = (List<Player>) this.findObjectByAttribute(
				Player.class, "game.name", gameName);

		return players;
	}

	@Override()
	@SuppressWarnings("unchecked")
	public List<Player> findAllPlayers() {
		List<Player> result = (List<Player>) this
				.findObjectByType(Player.class);

		return result;
	}

	@Override()
	@SuppressWarnings("unchecked")
	public List<Player> findAllPlayersByGameName(boolean participatingInGame,
			String gameName) {
		List<Player> players = (List<Player>) this.findObjectByAttribute(
				Player.class, "user.participatingInGame", participatingInGame,
				"game.name", gameName);
		// Class clazz = Player.class;
		// String SQL = "SELECT o FROM " + clazz.getName()
		// + " o JOIN FETCH o.materials WHERE o.game.name " + " LIKE '%"
		// + gameName + "' AND o.user.participatingInGame.toString() "
		// + " LIKE '%" + participatingInGame + "'";
		// logger.info(SQL);
		// CriteriaQuery<Object> query =
		// entityManager.getCriteriaBuilder().createQuery();
		// TypedQuery<Player> q2 = entityManager.createQuery(SQL, clazz);

		return players;
	}

	// @Override()
	// public Object saveOrUpdate(Object o) {
	// logger.info("Saving player instance ");
	// Player player = (Player) o;
	// Game game =null;
	// User user = null;

	// if(player.getGame()!=null && player.getGame().getId()!=null){
	// game = em.find(Game.class, player.getGame().getId());
	// logger.debug("getting saved game ");
	// player.setGame(game);
	// }
	// //
	// if(player.getUser()!=null && player.getUser().getId()!=null){
	// user = em.find(User.class, player.getUser().getId());
	// logger.debug("getting saved user ");
	// player.setUser(user);
	// }

	// em.merge(player.getGame());
	// em.merge(player.getUser());
	//
	// if(player.getId()!=null)
	// em.merge(player);
	// else
	// em.persist(player);
	//
	// em.flush();
	//
	// return player;
	// }
}
