package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.Game;

public interface GameDAO extends FundamentalsOperationsDAO {

	public List<Game> findAllByStatus(String status);

	public Game findGameByName(String gameName);

	public List<Game> findAllGames();

	public Game findGameByUserName(String userName);
}
