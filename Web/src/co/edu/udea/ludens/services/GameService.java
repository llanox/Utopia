package co.edu.udea.ludens.services;

import java.util.List;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Player;

public interface GameService {

	public Game findByUserLogin(String userName);

	public Game save(Game game);

	public List<Game> findAllGames();

	public void delete(Game actualGame);

	public Game findGameByName(String gameName);

	public void removePlayer(Game game, String login);

	public void meetRequirements(Game game);

	public void generateUnexpectedEvents(Game game);

	public void addPlayer(Game game, Player player);

	public void removePlayer(Game game, Player player);
}
