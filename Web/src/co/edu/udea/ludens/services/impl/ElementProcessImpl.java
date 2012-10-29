package co.edu.udea.ludens.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.enums.EnumEventType;
import co.edu.udea.ludens.enums.EnumMsgType;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.services.GameService;
import co.edu.udea.ludens.services.production.LevelUpgraderFactory;
import co.edu.udea.ludens.services.production.LevelUpgraderStrategy;
import co.edu.udea.ludens.services.production.ProducerFactory;
import co.edu.udea.ludens.services.production.ProducerStrategy;
import co.edu.udea.ludens.util.IncrementableEvent;
import co.edu.udea.ludens.util.IncrementableStuffListener;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.UtopiaUtil;
import co.edu.udea.ludens.web.ElementBean;

public class ElementProcessImpl implements ElementProcess {

	private static final String SYSTEM_ACTOR = "Sistema";
	private Player player;
	private Vector<IncrementableStuffListener> incrementListeners = new Vector<IncrementableStuffListener>();;
	private Vector<MessageListener> messagesListeners = new Vector<MessageListener>();
	private HashMap<String, Element> mapElements = new HashMap<String, Element>();
	//private List<Element> mapElements = new ArrayList<Element>();
	private static Logger logger = Logger.getLogger(ElementProcessImpl.class);
	private boolean initiated = false;
	@Autowired
	private GameService gameService;

	public ElementProcessImpl() {
		super();
	}

	public void initElements() {
		// Execute that method only one time
		if (!initiated) {
			initiated = true;
			//List<Element> factors = player.getDevelopmentFactors();
			HashMap<String, Element> factors = createHashMapFromListElement(player.getDevelopmentFactors());
			mapElements.putAll(factors);
			HashMap<String, Element> materials = createHashMapFromListElement(player.getMaterials());
			mapElements.putAll(materials);

			// Calculating initial factor coverage
			for (Object key : factors.keySet()) {
				Element el = factors.get(key);
				logger.info("factor .." + el);
				logger.info("key .." + key);
				UtopiaUtil.calculateCoverage(el, player);
			}
			notifyEvent(mapElements, EnumEventType.INITIAL_SETUP);
		}
	}

	// Hecho por mi...
	public HashMap<String, Element> createHashMapFromListElement(List<Element> list) {
		HashMap<String, Element> factors = new HashMap<String, Element>();
		for (Element e : list) {
			factors.put(e.getIncrementable().getName(), e);
		}
		
		return (factors);
	}
	
	private void notifyEvent(HashMap<String, Element> elements,
			EnumEventType type) {
		MessageEvent aEvent;
		Game game = gameService.findByUserLogin(player.getUser().getLogin());

		for (Object key : elements.keySet()) {
			Element ele = elements.get(key);
			logger.info("Incrementable ---> " + ele.getIncrementable());
			logger.info("Initial value ---> "
					+ ele.getIncrementable().getInitialValue());

			aEvent = new MessageEvent(this, UtopiaUtil.getId(),
					EnumMsgs.INITIAL_VALUE_ELEMENT, ele.getIncrementable()
							.getName(), ele.getIncrementable()
							.getInitialValue());
			aEvent.setGameName(game.getName());
			settingSystemEvent(aEvent, ele, type);
			notifyMsg(aEvent);
		}
	}

	@Override
	public void produceElements() {
		ProducerStrategy producer = null;
		List<ElementBean> elementsBeans = null;
		HashMap<String, Element> factors = createHashMapFromListElement(player.getDevelopmentFactors());
		mapElements.putAll(factors);
		HashMap<String, Element> materials = createHashMapFromListElement(player.getMaterials());
		mapElements.putAll(materials);
		Element population = player.getPopulation();

		producer = ProducerFactory.createProducer(EnumElementType.POPULATION);
		logger.info("population ---");
		elementsBeans = producer.produce(population, this);
		notifyEvent(elementsBeans, EnumEventType.POPULATION_PRODUCTION);

		producer = ProducerFactory.createProducer(EnumElementType.MATERIAL);
		logger.info("materials ---");
		elementsBeans = producer.produce(materials, this);
		notifyEvent(elementsBeans, EnumEventType.MATERIALS_PRODUCTION);

		producer = ProducerFactory.createProducer(EnumElementType.FACTOR);
		logger.info("factors ---");
		elementsBeans = producer.produce(factors, this);
		notifyEvent(elementsBeans, EnumEventType.FACTORS_PRODUCTION);

		notifyMsg(EnumMsgs.PRODUCTION_PROCESS_HAPPENING);
	}

	private void notifyEvent(List<ElementBean> elementsBeans,
			EnumEventType eventType) {
		MessageEvent aEvent;
		for (ElementBean aElement : elementsBeans) {
			aEvent = new MessageEvent(this, UtopiaUtil.getId(),
					EnumMsgs.PRODUCING_ELEMENT, aElement.getElement()
							.getIncrementable().getName(), aElement
							.getElement().getQuantity());
			Game game = gameService
					.findByUserLogin(player.getUser().getLogin());
			aEvent.setGameName(game.getName());
			settingSystemEvent(aEvent, aElement.getElement(), eventType);
			notifyMsg(aEvent);
		}

		IncrementableEvent event = new IncrementableEvent(this, eventType,
				elementsBeans);

		for (IncrementableStuffListener listener : incrementListeners) {
			listener.changeIncrementable(event);
		}
	}

	private void settingSystemEvent(MessageEvent aEvent, Element aElement,
			EnumEventType eventType) {
		aEvent.setAffectedElement1(aElement.getIncrementable().getName());
		aEvent.setElement1Value(aElement.getQuantity() + "");
		aEvent.setExecutorName(SYSTEM_ACTOR);
		aEvent.setReceiverName(player.getUser().getLogin());
		aEvent.setExactDate(new Date());
		aEvent.setGameElapsedTime(UtopiaUtil.getElapsedTime(player));
		aEvent.setEventType(eventType);
	}

	private void notifyMsg(EnumMsgs enumMsg) {
		MessageEvent event = new MessageEvent(this, enumMsg, UtopiaUtil.getId());
		Game game = gameService.findByUserLogin(player.getUser().getLogin());
		event.setGameName(game.getName());

		notifyMsg(event);
	}

	private void notifyMsg(MessageEvent event) {
		Game game = gameService.findByUserLogin(player.getUser().getLogin());

		event.setGameName(game.getName());
		for (MessageListener listener : messagesListeners) {
			listener.notifyMsg(event);
		}
	}

	@Override
	public void addIncrementableStuffListener(
			IncrementableStuffListener listener) {
		incrementListeners.add(listener);
	}

	public void addMessageListener(MessageListener listener) {
		messagesListeners.add(listener);
	}

	public void removeAllMessageListeners() {
		if (messagesListeners != null) {
			messagesListeners.clear();
		}
	}

	@Override
	public void removeAllIncrementableStuffListeners() {
		incrementListeners.clear();
	}

	public void removeMessageListener(MessageListener listener) {
		messagesListeners.remove(listener);
	}

	@Override
	public void removeIncrementableStuffListener(
			IncrementableStuffListener listener) {
		incrementListeners.remove(listener);
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;

	}

	@Override
	public Player getPlayer() {

		return (this.player);
	}

	@Async
	public MessageEvent upLevel(String elementName) {
		boolean error = false;
		MessageEvent event = null;
		Element el = mapElements.get(elementName);

		logger.info("upgrading level " + elementName);

		try {
			boolean producing = player.isProducing();

			logger.info("producing " + producing);

			if (producing) {
				event = new MessageEvent(this, UtopiaUtil.getId(),
						EnumMsgs.PRODUCING_ELEMENT_ERROR, el.getIncrementable()
								.getName());
				notifyMsg(event);

				return event;
			}

			logger.info("initial upgrading time " + el);
			int delay = 0;
			if (el.getActualUpgradingTime() == 0) {
				delay = el.getLevelIncrementDelayRate();
				el.setActualUpgradingTime(delay);
			}

			event = new MessageEvent(this, UtopiaUtil.getId(),
					EnumMsgs.DELAY_UPGRADING_LEVEL, el.getIncrementable()
							.getName(), el.getActualUpgradingTime(),
					el.getLevel(), el.getLevel() + 1);
			notifyMsg(event);

			LevelUpgraderStrategy upgrader = LevelUpgraderFactory
					.createLevelUpgrader(el);

			upgrader.upLevel(el, player);
		} catch (Exception e) {
			event = new MessageEvent(this, EnumMsgType.ERROR, e.getMessage(),
					UtopiaUtil.getId());
			notifyMsg(event);
			error = true;
		}

		if (!error) {
			Calendar cal = Calendar.getInstance();
			event = new MessageEvent(this, UtopiaUtil.getId(),
					EnumMsgs.SUCCESSFULY_UPGRADE, el.getIncrementable()
							.getName(), el.getLevel());
			event.setEventType(EnumEventType.UPGRADE_LEVEL);
			event.setAffectedElement1(el.getIncrementable().getName());
			event.setElement1Value(el.getLevel() + "");
			event.setExactDate(cal.getTime());
			event.setExecutorName(player.getUser().getLogin());
			event.setReceiverName(SYSTEM_ACTOR);
			event.setGameElapsedTime(UtopiaUtil.getElapsedTime(player));

			notifyMsg(event);
		}

		return event;
	}

	@Override
	public void upLevel(Element element) {
		upLevel(element.getIncrementable().getName());
	}

	/**
	 * @param gameService
	 *            the gameService to set
	 */
	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	/**
	 * @return the gameService
	 */
	public GameService getGameService() {

		return (this.gameService);
	}
}
