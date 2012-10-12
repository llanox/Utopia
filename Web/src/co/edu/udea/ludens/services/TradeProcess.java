package co.edu.udea.ludens.services;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Interchange;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.TradeListener;

public interface TradeProcess extends Process {

	public void cancelOffer(Interchange interchange);

	public void modifyOffer(Interchange interchange);

	public void addMessageListener(MessageListener listener);

	public void addTradeListener(TradeListener listener);

	public void removeMessageListener(MessageListener listener);

	public void removeTradeListener(TradeListener listener);

	public void postOffer(Interchange interchange);

	public void acceptOffer(Interchange interchange, String playerUser);

	public void setGame(Game game);

	public Game getGame();

	public void postOffer(String elFromSender, int quantityFromSender,
			String elFromReceiver, int quantityFromReceiver, String offerSender);

}
