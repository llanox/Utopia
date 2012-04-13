package co.edu.udea.ludens.web;



import org.apache.log4j.Logger;

import co.edu.udea.ludens.domain.Interchange;
import co.edu.udea.ludens.services.TradeProcess;


public class InterchangeBean {

	private TradeProcess process;
	private Interchange interchange;
	private String       message;
	private Logger logger = Logger.getLogger(getClass());
	private String viewers;
	
	public InterchangeBean(){
		interchange = new Interchange();
		
	}

	
	
	public void acceptOffer(javax.faces.event.ActionEvent event) {
		
		String player = (String) event.getComponent().getAttributes().get("player");	
		logger.info("player who accept "+player);
		process.acceptOffer(interchange, player);
	}

	public void cancelOffer(javax.faces.event.ActionEvent ae) {
		logger.info("canceling interchange ");
		process.cancelOffer(interchange);
	}

	public void modifyOffer(javax.faces.event.ActionEvent ae) {
		process.modifyOffer(interchange);
	
	}



	public void setInterchange(Interchange interchange) {
		this.interchange = interchange;
	}



	public Interchange getInterchange() {
		return interchange;
	}



	public void setProcess(TradeProcess process) {
		this.process = process;
	}



	public TradeProcess getProcess() {
		return process;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getMessage() {
		return message;
	}



	public void setViewers(String viewers) {
		this.viewers = viewers;
	}



	public String getViewers() {
		return viewers;
	}

	
	
	

}
