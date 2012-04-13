package co.edu.udea.ludens.services.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.EventProcess;
import co.edu.udea.ludens.services.GameProcess;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.ServiceLocator;
import co.edu.udea.ludens.services.TradeProcess;
import co.edu.udea.ludens.util.UtopiaUtil;




public class GameProcessImpl  implements GameProcess  {

	private static Logger logger = Logger.getLogger(GameProcessImpl.class);
	private static final String GAME_PROCESS="gameProcess";
	private HashMap<String,ElementProcess> mapElementProcess = new HashMap<String,ElementProcess> ();
	private TradeProcess tradeProcess;
	private EventProcess eventProcess;
	
	private Game game;
	private ServiceLocator serviceLocator;
	@Autowired
	private GameService gameService;	
	@Autowired
	private ElementService elementService;




	public void produceElements() {
		
	
		
		for( ElementProcess process : mapElementProcess.values()){
			Player player = process.getPlayer();
			
			boolean meet = UtopiaUtil.meetReqToGettingStart(player);
			
			if(meet){
		    logger.info("********** Producing elements ********");
			process.produceElements();
			
			}  			
		}

		
	}








	@Override
	public void startGame() {

		
		logger.info("Game (starting Game)...." + game);

		gameService.meetRequirements(game);
		gameService.generateUnexpectedEvents(game);
		elementService.createElementsForEachPlayer(game);
	    game.setStatus(EnumGameStatus.STARTED);
		game.setStartTime(new Date());

		String name = game.getName();
		game.setName(name + "Started" + System.currentTimeMillis());
		gameService.save(game);
		
				
		createAdditionalProcesses();
	
	}




	@Override
	public void stopGame() {
	
		
	}




	@Override
	public void setGame(Game game) {
		this.game = game;
			
	}








	public void createAdditionalProcesses() {
		if(game!=null){
			
			List<Player> players = game.getPlayers();			

			tradeProcess = serviceLocator.createTradeProcess();
			tradeProcess.setGame(game);	
			
			for(Player player :players){
				
				logger.debug("Creating processes by player "+player.getUser().getLogin());
				
				ElementProcess elementProcess =  serviceLocator.createElementProcess();
				elementProcess.setPlayer(player);
				
				mapElementProcess.put(player.getUser().getLogin(), elementProcess);
						
				
			}
			setEventProcess(serviceLocator.createEventProcess());
			getEventProcess().setGame(game);
			
			
		}
	}

	@Override
	public ElementProcess getElementProcess(String userName) {

		return mapElementProcess.get(userName);
	}

	@Override
	public Game getGame() {
		return game;
	}


	public void setTradeProcess(TradeProcess tradeProcess) {
		this.tradeProcess = tradeProcess;
	}




	public TradeProcess getTradeProcess() {
		return tradeProcess;
	}




	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}








	public void setEventProcess(EventProcess eventProcess) {
		this.eventProcess = eventProcess;
	}








	public EventProcess getEventProcess() {
		return eventProcess;
	}











	@Override
	public void doWork() {
		produceElements(); 		
	}








	@Override
	public String getId() {

		return GAME_PROCESS+"_"+game.getName();
	}












}
