package co.edu.udea.ludens.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.User;
import co.edu.udea.ludens.enums.EnumEventType;
import co.edu.udea.ludens.enums.EnumGameStatus;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.scheduling.MaterialsProductionTask;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.services.EventProcess;
import co.edu.udea.ludens.services.GameContainerService;
import co.edu.udea.ludens.services.GameProcess;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.MessagesCentralService;
import co.edu.udea.ludens.services.ProcessHolderService;
import co.edu.udea.ludens.services.ServiceLocator;
import co.edu.udea.ludens.services.TradeProcess;
import co.edu.udea.ludens.util.LudensListener;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.TradeListener;
import co.edu.udea.ludens.util.UnexpectedEventListener;
import co.edu.udea.ludens.util.UserSessionEvent;
import co.edu.udea.ludens.util.UserSessionListener;
import co.edu.udea.ludens.web.UserSessionBean;

@Service("gameContainerService")
@Scope("singleton")
public class GameContainerServiceImpl implements GameContainerService {

	@Autowired
	private ProcessHolderService processHolderService;

	@Autowired
	private ServiceLocator serviceLocator;

	@Autowired
	private MessagesCentralService messagesCentralService;

	@Autowired
	private GameService gameService;

	@Autowired
	private TaskScheduler productionScheduler;

	private List<UserSessionListener> userSessionListeners = new ArrayList<UserSessionListener>();

	private Logger logger = Logger.getLogger(GameContainerServiceImpl.class);

	public GameContainerServiceImpl() {
	}

	public void suscribeListeners(Game game, User user,
			List<LudensListener> listeners) {
		if (user == null)
			return;

		if (game == null)
			return;

		for (Object listener : listeners) {
			if (listener != null)
				logger.debug("listener " + listener.getClass().getName());
		}

		for (LudensListener listener : listeners) {
			if (listener instanceof TradeListener)
				suscribeTradeListener((TradeListener) listener, game.getName());

			if (listener instanceof UnexpectedEventListener)
				suscribeUnexpectedEventListener(
						(UnexpectedEventListener) listener, game.getName());

			if (listener instanceof UserSessionListener)
				suscribeUserSessionListener((UserSessionListener) listener);

			if (listener instanceof MessageListener)
				suscribeMessageListener((MessageListener) listener,
						game.getName(), user.getLogin());
		}
	}

	public void unsuscribeListeners(Game game, User user,
			List<LudensListener> ludensListeners) {
		if (user == null)
			return;

		if (game == null)
			return;

		for (Object listener : ludensListeners) {
			if (listener != null)
				logger.debug("listener " + listener.getClass().getName());
		}

		for (LudensListener listener : ludensListeners) {
			if (listener instanceof TradeListener)
				unsubscribeTradeListener((TradeListener) listener,
						game.getName());

			if (listener instanceof UnexpectedEventListener)
				unsubscribeUnexpectedEventListener(
						(UnexpectedEventListener) listener, game.getName());

			if (listener instanceof UserSessionListener)
				unsubscribeUserSessionListener((UserSessionListener) listener);

			if (listener instanceof MessageListener)
				unsubscribeMessageListener((MessageListener) listener,
						game.getName(), user.getLogin());
		}
	}

	public void startGame(Game game) throws LudensException {
		if (game.getStatus() == EnumGameStatus.NO_STARTED) {
			logger.debug("starting game ");
			game.setStatus(EnumGameStatus.STARTED);
		}

		if (game.getStatus() == EnumGameStatus.STARTED
				|| game.getStatus() == EnumGameStatus.PAUSED) {
			logger.debug("restarting game ");
			game.setStatus(EnumGameStatus.STARTED);
		}

		if (game.getStatus() == EnumGameStatus.FINISHED) {
			throw new LudensException("GAME OVER :-(");
		}

		GameProcess gameProcess = startGameProcess(game);
		createTasksSchedule(game, gameProcess);
	}

	private void createTasksSchedule(Game game, GameProcess gameProcess) {
		logger.debug("Production time " + game.getProductionTime());
		MaterialsProductionTask materialsProductionTask = new MaterialsProductionTask(
				gameProcess);
		productionScheduler.scheduleAtFixedRate(materialsProductionTask,
				game.getProductionTime());
	}

	private GameProcess startGameProcess(Game game) {
		GameProcess process = null;
		process = (GameProcess) processHolderService.findProcessById(
				GameProcess.class, game.getName());

		if (process != null)
			return process;

		process = serviceLocator.createGameProcess();
		processHolderService.putProcess(game.getId().toString(), process);

		process.setGame(game);
		process.startGame();

		return process;
	}

	@Override
	public TradeProcess getTrader(String gameName) {
		GameProcess gameProcess = (GameProcess) processHolderService
				.findProcessById(GameProcess.class, gameName);

		if (gameProcess == null)
			return null;

		TradeProcess tradeProcess = gameProcess.getTradeProcess();

		return tradeProcess;
	}

	@Override
	public void suscribeMessageListener(MessageListener listener,
			String gameName, String userName) {

		GameProcess gameProcess = (GameProcess) processHolderService
				.findProcessById(GameProcess.class, gameName);
		if (gameProcess == null)
			return;

		ElementProcess elementProcess = gameProcess.getElementProcess(userName);

		if (elementProcess == null)
			return;

		elementProcess.addMessageListener(listener);
		elementProcess.addMessageListener(messagesCentralService);
		elementProcess.initElements();
	}

	@Override
	public void suscribeTradeListener(TradeListener listener, String gameName) {
		TradeProcess tradeProcess = getTrader(gameName);

		if (tradeProcess == null)
			return;

		tradeProcess.addTradeListener(listener);
		tradeProcess.addMessageListener(listener);
		tradeProcess.addMessageListener(messagesCentralService);
	}

	@Override
	public void suscribeUnexpectedEventListener(
			UnexpectedEventListener listener, String gameName) {

		GameProcess gameProcess = (GameProcess) processHolderService
				.findProcessById(GameProcess.class, gameName);

		if (gameProcess == null)
			return;

		EventProcess eventProcess = gameProcess.getEventProcess();
		eventProcess.addUnexpectedEventListener(listener);
		eventProcess.addMessageListener(messagesCentralService);
	}

	@Override
	public void suscribeUserSessionListener(
			UserSessionListener userSessionListener) {
		userSessionListeners.add(userSessionListener);
	}

	@Override
	public void unsubscribeMessageListener(MessageListener messageListener,
			String gameName, String userName) {
		GameProcess gameProcess = (GameProcess) processHolderService
				.findProcessById(GameProcess.class, gameName);

		if (gameProcess == null) {
			logger.info("Game process null");
			return;
		}

		ElementProcess elementProcess = gameProcess.getElementProcess(userName);

		if (elementProcess == null) {
			logger.info("Element process null");
			return;
		}

		elementProcess.removeMessageListener(messageListener);
	}

	@Override
	public void unsubscribeTradeListener(TradeListener tradeListener,
			String gameName) {
		TradeProcess tradeProcess = getTrader(gameName);
		if (tradeProcess == null)
			return;

		tradeProcess.removeTradeListener(tradeListener);
		tradeProcess.removeMessageListener(tradeListener);
	}

	@Override
	public void unsubscribeUnexpectedEventListener(
			UnexpectedEventListener listener, String gameName) {
		GameProcess gameProcess = (GameProcess) processHolderService
				.findProcessById(GameProcess.class, gameName);

		if (gameProcess == null)
			return;

		EventProcess eventProcess = gameProcess.getEventProcess();
		eventProcess.removeUnexpectedEventListener(listener);
	}

	@Override
	public void unsubscribeUserSessionListener(UserSessionListener listener) {
		userSessionListeners.remove(listener);
	}

	@Override
	public void logIn(User user) {
		EnumEventType eventType = EnumEventType.LOG_INTO_SYSTEM;
		UserSessionEvent event = new UserSessionEvent(this, eventType);
		event.setUser(user);

		for (UserSessionListener listener : userSessionListeners) {
			listener.handlingSessionEvent(event);
		}
	}

	@Override
	public void logOut(User user) {
		EnumEventType eventType = EnumEventType.LOG_OUT_SYSTEM;
		UserSessionEvent event = new UserSessionEvent(this, eventType);
		event.setUser(user);

		for (UserSessionListener listener : userSessionListeners) {
			listener.handlingSessionEvent(event);
		}
	}

	@Override
	public void executeGlobalProduction() {
	}

	public ServiceLocator getServiceLocator() {

		return (this.serviceLocator);
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public MessagesCentralService getMessagesCentralService() {

		return (this.messagesCentralService);
	}

	public void setMessagesCentralService(
			MessagesCentralService messagesCentralService) {
		this.messagesCentralService = messagesCentralService;
	}

	public ProcessHolderService getProcessHolderService() {

		return (this.processHolderService);
	}

	public void setProcessHolderService(
			ProcessHolderService processHolderService) {
		this.processHolderService = processHolderService;
	}

	@Override
	public void suscribeListeners(UserSessionBean userSession,
			List<LudensListener> ludensListeners) {
		Game game = this.gameService
				.findGameByName(userSession.getActualGame());
		this.suscribeListeners(game, userSession.getUser(), ludensListeners);
	}

	@Override
	public void unsubscribeListeners(UserSessionBean userSession,
			List<LudensListener> ludensListeners) {
		Game game = this.gameService
				.findGameByName(userSession.getActualGame());
		this.unsuscribeListeners(game, userSession.getUser(), ludensListeners);
	}
}
