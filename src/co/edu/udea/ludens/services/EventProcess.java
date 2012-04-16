package co.edu.udea.ludens.services;



import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.UnexpectedEventListener;

public interface EventProcess  extends Process {
	
	
	
	
	public void eventHappening();
	public void setGame(Game game);   
	
	
	public void removeUnexpectedEventListener(UnexpectedEventListener listener);
	public void addUnexpectedEventListener(UnexpectedEventListener listener);
	
	public void addMessageListener(MessageListener listener);
	public void removeMessageListener(MessageListener listener);

	
	
	

	
	

}
