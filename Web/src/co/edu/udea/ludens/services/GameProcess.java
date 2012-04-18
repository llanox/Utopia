package co.edu.udea.ludens.services;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.scheduling.UtopiaTask;




public interface GameProcess extends UtopiaTask, Process  {
	
	public void setGame(Game game);
	public Game getGame();	
	
	public void startGame();
	public void stopGame();
	
	public void produceElements();
	
	
	public ElementProcess getElementProcess(String userName);
	
	public void setServiceLocator(ServiceLocator serviceLocator);
	
	public void setTradeProcess(TradeProcess tradeProcess) ;

	public TradeProcess getTradeProcess();
	

	public void setEventProcess(EventProcess eventProcess) ;


	public EventProcess getEventProcess();
	
}
