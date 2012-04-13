package co.edu.udea.ludens.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Interchange;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.scheduling.UtopiaExecutor;
import co.edu.udea.ludens.scheduling.UtopiaTask;
import co.edu.udea.ludens.web.InterchangeBean;



@Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
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
