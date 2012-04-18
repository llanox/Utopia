package co.edu.udea.ludens.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.icesoft.faces.async.render.SessionRenderer;

import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.PlayerStatus;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumEventType;
import co.edu.udea.ludens.services.GameProcess;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.TradeProcess;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.UserSessionEvent;
import co.edu.udea.ludens.util.UserSessionListener;
import co.edu.udea.ludens.util.UtopiaUtil;

public class ReportController implements MessageListener,UserSessionListener{

	private List<MessageEvent> events = new ArrayList<MessageEvent>();
	private List<PlayerStatus>  playersStatus = new ArrayList<PlayerStatus>();
	private UserSessionBean userSession;
	private PlayerService playerService;
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void notifyMsg(MessageEvent event) {	
		
	EnumEventType eventType = event.getEventType();
	
	logger.info("Report Controller "+eventType);
	
	loadAllData();			
	SessionRenderer.render(getUserSession().getUser().getLogin());	
		
	}
	
	
	public void loadAllData(){
		loadDataReport();
		loadDataPlayerStatus();
		
	}

	
	public void loadDataReport() {
		
		List<MessageEvent> temp = new ArrayList<MessageEvent>();

		logger.info("loading data ....");
		String game = userSession.getActualGame();
		String login = userSession.getUser().getLogin();
		Player player = playerService.findPlayer(login);
		
		
		if(player!=null){		
			temp = player.getEvents();
			
			
			HashSet<MessageEvent> hset = new HashSet<MessageEvent>(temp);
			List<MessageEvent> eventsTmp = new ArrayList<MessageEvent>(hset);
			
			events = new ArrayList<MessageEvent>();
			
			for(MessageEvent e : eventsTmp){
				
				if(e.getEventType()==null)
					continue;
				
				msgFormatter(e);
				
				if(  !login.equalsIgnoreCase(e.getExecutorName()) && !login.equalsIgnoreCase(e.getReceiverName())){
					continue;					
				}
				
				events.add(e);
				
			}
			
	       Collections.sort(events,Collections.reverseOrder());
	     
			
		
			
		}
		
		
		
	}
	
	private void msgFormatter(MessageEvent e) {
		String msg = e.getMsg();
		
	  int start = msg.indexOf('<'); 	
	  	
		
	}


	public void loadDataPlayerStatus(){
		String game = userSession.getActualGame();
		List<Player> players = playerService.findAllPlayersByGameName(game);
		playersStatus =new ArrayList<PlayerStatus>();
		
		PlayerStatus playerStatus = null;
		
		for(Player player:players ){
		    
			
			playerStatus = UtopiaUtil.generatePlayerStatus(player);
			playersStatus.add(playerStatus);
			
		}
		
		Collections.sort(playersStatus);
		
		SessionRenderer.render(getUserSession().getUser().getLogin());	
		
	}
	
	
	
	
	/**
	 * @param events the events to set
	 */
	public void setEvents(List<MessageEvent> events) {
		this.events = events;
	}

	/**
	 * @return the events
	 */
	public List<MessageEvent> getEvents() {
		return events;
	}




	/**
	 * @param userSession the userSession to set
	 */
	public void setUserSession(UserSessionBean userSession) {
		this.userSession = userSession;
	}




	/**
	 * @return the userSession
	 */
	public UserSessionBean getUserSession() {
		return userSession;
	}


	/**
	 * @param playerService the playerService to set
	 */
	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}


	/**
	 * @return the playerService
	 */
	public PlayerService getPlayerService() {
		return playerService;
	}



	public Logger getLogger() {
		return logger;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}


	/**
	 * @param playersStatus the playersStatus to set
	 */
	public void setPlayersStatus(List<PlayerStatus> playersStatus) {
		this.playersStatus = playersStatus;
	}


	/**
	 * @return the playersStatus
	 */
	public List<PlayerStatus> getPlayersStatus() {
		return playersStatus;
	}


	@Override
	public void handlingSessionEvent(UserSessionEvent event) {
		loadDataPlayerStatus();
		
	}





	

}
