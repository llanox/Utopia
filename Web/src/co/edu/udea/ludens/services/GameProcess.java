package co.edu.udea.ludens.services;

import co.edu.udea.ludens.domain.Game;





public interface GameProcess  extends  Process  {
	
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
