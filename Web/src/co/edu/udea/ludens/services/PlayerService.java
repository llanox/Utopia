package co.edu.udea.ludens.services;

import java.util.List;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.exceptions.LudensException;

public interface PlayerService {

	public Player findPlayer(String senderName);

	public List<Player> findAllPlayersByGameName(String gameName);

	public Player save(Player player);

	public void delete(Player player);

	public List<Player> findAllPlayers();

	public List<Player> findAllPlayersByGameName(boolean participatingInGame,
			String name);

	public boolean meetReqToGettingStart(Player player);
	
	public Element getElementPlayerByName(List<Element> elementList, String name);

	public void checkOutResources(List<IncrementableConstraint> ctrs,
			Element element, Player player) throws LudensException;

	public List<IncrementableConstraint> getIncrementableConstraintByLevel(
			List<IncrementableConstraint> levelConstraints, Integer newLevel);

}
