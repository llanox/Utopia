package co.edu.udea.ludens.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.udea.ludens.domain.Game;

@Repository
public class GameDAOImpl extends ObjectDBDAO implements GameDAO {

	public List<Game> findAllByStatus(String status) {
		throw new RuntimeException("Función No implementada");
	}

	public Game findGameByUserName(String userName) {
		throw new RuntimeException("Función No implementada");
	}

	@Override
	@SuppressWarnings("unchecked")
	public Game findGameByName(String gameName) {
		Game game = null;
		List<Game> games = (List<Game>) this.findObjectByAttribute(Game.class,
				"name", gameName);

		if (games != null && !games.isEmpty())
			game = games.get(0);

		return game;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Game> findAllGames() {
		List<Game> result = (List<Game>) this.findObjectByType(Game.class);

		return result;
	}
}
