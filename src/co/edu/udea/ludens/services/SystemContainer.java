package co.edu.udea.ludens.services;

import java.util.HashMap;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.util.IncrementableStuffListener;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.UserSessionListener;
import co.edu.udea.ludens.util.TradeListener;
import co.edu.udea.ludens.util.UnexpectedEventListener;
import co.edu.udea.ludens.web.BoardController;
import co.edu.udea.ludens.web.ReportController;
import co.edu.udea.ludens.web.TradeController;
import co.edu.udea.ludens.web.UnexpectedEventController;

public interface SystemContainer {



	public void suscribeMessageListener(MessageListener listener, String gameName,String userName);

	
	public void executeGlobalProduction();

	

	public TradeProcess getTrader(String gameName);

	public void suscribeTradeListener(TradeListener listener, String name);

	public void suscribeUnexpectedEventListener(UnexpectedEventListener listener, String name);

	

	public void suscribeUserSessionListener(UserSessionListener userSessionListener);

	public void unsubscribeMessageListener(MessageListener messageListener,	String gameName, String login);

	public void unsubscribeTradeListener(TradeListener tradeListener,String gameName);

	public void unsubscribeUnexpectedEventListener(UnexpectedEventListener unexpectedEventListener, String gameName);

	public void unsubscribeUserSessionListener(UserSessionListener userSessionListener);

	public void logIn(User user);

	public void logOut(User user);

	public void startGame(Game game) throws LudensException; 


}
