package co.edu.udea.ludens.services;

import java.util.List;

import co.edu.udea.ludens.domain.Player;

public interface PlayerService {

	public Player findPlayer(String senderName);

	public List<Player> findAllPlayersByGameName(String gameName);

	public Player save(Player player);

	public void delete(Player player);

	public List<Player> findAllPlayers();

	public List<Player> findAllPlayersByGameName(boolean participatingInGame,
			String name);
}
