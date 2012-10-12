package co.edu.udea.ludens.services;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;

import co.edu.udea.ludens.util.IncrementableStuffListener;
import co.edu.udea.ludens.util.MessageListener;

public interface ElementProcess extends Process {

	public void addIncrementableStuffListener(
			IncrementableStuffListener listener);

	public void removeIncrementableStuffListener(
			IncrementableStuffListener listener);

	public void removeAllIncrementableStuffListeners();

	public void setPlayer(Player player);

	public Player getPlayer();

	public void produceElements();

	public void initElements();

	public void upLevel(Element incrementable);

	public MessageEvent upLevel(String incrementableName);

	public void addMessageListener(MessageListener listener);

	public void removeMessageListener(MessageListener listener);
}
