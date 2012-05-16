package co.edu.udea.ludens.services.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.EventProcess;
import co.edu.udea.ludens.services.GameProcess;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.ServiceLocator;
import co.edu.udea.ludens.services.TradeProcess;
import co.edu.udea.ludens.util.UtopiaUtil;




public class GameProcessImpl  implements GameProcess   {

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
	
	@Autowired
	private PlayerService playerService;
	
	




	public void produceElements() {
		
		 logger.info("********** Producing elements ********");
		 
		 logger.info("********** Element Porcess ******** size: "+mapElementProcess.size());
		
		for( ElementProcess process : mapElementProcess.values()){
			Player player = process.getPlayer();
			
			logger.info("********** Process each element ********");
			
			boolean meet = meetReqToGettingStart(player);
			
			if(meet){
		   
			process.produceElements();
			
			}else{
				logger.info("********** didn't meet requirements ********");
				
			}  			
		}

		
	}
	
	public  boolean meetReqToGettingStart(Player player) {

		Set<Element> elements  =  new HashSet<Element>();
		
		logger.debug("materials player "+player.getMaterials().size());

		Set<Element> materials = elementService.getAllElementsByPlayer(EnumElementType.MATERIAL, player.getUser().getLogin());
		elements.addAll(materials);
		
		Set<Element> factors = elementService.getAllElementsByPlayer(EnumElementType.FACTOR, player.getUser().getLogin());
		elements.addAll(factors);
		
		System.out.println("Elements "+elements.size());

		boolean elementsOk = isHigherThanRequired(elements);
		System.out.println("Elements OK? "+elementsOk);
		
		
		return elementsOk;
	}


	public static boolean isHigherThanRequired(Set<Element> elements) {

		for (Element el : elements) {

		
			Integer level = el.getLevel();

			if (level >= UtopiaUtil.LEVEL_TO_GETTING_START) {
				logger.info("level " + level + " element " + el.getIncrementable().getName()
						+ " quantity " + el.getQuantity());
				return true;
			}

		}

		return false;
	}





	@Override
	public void startGame() {

		
		logger.info("Game (starting Game)...." + game);

		gameService.meetRequirements(game);
		gameService.generateUnexpectedEvents(game);
		elementService.createElementsForEachPlayer(game);
	    game.setStatus(EnumGameStatus.STARTED);
		game.setStartTime(new Date());		
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
			
			List<Player> players = playerService.findAllPlayersByGameName(true, game.getName());	
			
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





	public String getId() {

		return GAME_PROCESS+"_"+game.getName();
	}








}
