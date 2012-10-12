package co.edu.udea.ludens.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import co.edu.udea.ludens.domain.Game;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class GameDAOImpl extends ObjectDBDAO implements GameDAO {
	private Logger logger = Logger.getLogger(getClass());

	public List<Game> findAllByStatus(String status) {
		throw new RuntimeException("Función No implementada");

	}

	public Game findGameByUserName(String userName) {

		throw new RuntimeException("Función No implementada");
	}

	@Override
	public Game findGameByName(String gameName) {

		Game game = null;
		String jpql = "Select g from Game g Where g.name like '%" + gameName
				+ "'";
		TypedQuery<Game> q2 = em.createQuery(jpql, Game.class);
		List<Game> games = q2.getResultList();

		if (games != null && !games.isEmpty())
			game = games.get(0);

		return game;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> findAllGames() {
		List<Game> result = (List<Game>) this.findObjectByType(Game.class);

		return result;
	}
}
