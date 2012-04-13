package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.Player;

public interface PlayerDAO extends FundamentalsOperationsDAO{
	
	public Player findPlayerByUserName(String userName);

	public List<Player> findAllPlayersByGameName(String gameName);

	public List<Player> findAllPlayers();

	public List<Player> findAllPlayersByGameName(boolean participatingInGame,
			String gameName);

}
