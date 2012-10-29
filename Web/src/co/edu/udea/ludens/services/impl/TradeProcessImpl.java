package co.edu.udea.ludens.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Interchange;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumEventType;
import co.edu.udea.ludens.enums.EnumMsgType;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.TradeProcess;
import co.edu.udea.ludens.util.InterchangeEvent;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.InterchangeListener;
import co.edu.udea.ludens.util.UtopiaUtil;
import co.edu.udea.ludens.util.TradeListener;
import co.edu.udea.ludens.web.InterchangeBean;

public class TradeProcessImpl implements TradeProcess {

	private List<MessageListener> messagesListeners = new ArrayList<MessageListener>();
	private List<InterchangeListener> interchangeListeners = new ArrayList<InterchangeListener>();
	private Logger logger = Logger.getLogger(getClass());
	private List<Interchange> offers = new ArrayList<Interchange>();
	private Game game;
	private static int id_counter = 0;

	@Autowired
	PlayerService playerService;
	@Autowired
	ElementService elementService;

	@Override
	public void postOffer(String elFromSender, int quantityFromSender,
			String elFromReceiver, int quantityFromReceiver, String offerSender) {
		Player sender = UtopiaUtil.getPlayerByName(game.getPlayers(),
				offerSender);

		Interchange interchange = new Interchange();
		interchange.setSender(sender);

		/*
		 * Element elReceiver = sender.getMaterials().get(elFromReceiver);
		 * Element elSender = sender.getMaterials().get(elFromSender);
		 */
		Element elReceiver = playerService.getElementsByName(
				sender.getMaterials(), elFromReceiver).get(0);
		Element elSender = playerService.getElementsByName(
				sender.getMaterials(), elFromSender).get(0);

		interchange.setElFromReceiver(elReceiver);
		interchange.setElFromSender(elSender);

		interchange.setQuantityFromReceiver(quantityFromReceiver);
		interchange.setQuantityFromSender(quantityFromSender);

		postOffer(interchange);

		elementService.save(elReceiver);
		elementService.save(elSender);
	}

	public void postOffer(Interchange interchange) {
		Element elFromSender = interchange.getElFromSender();
		Integer inventory = elFromSender.getQuantity();
		Integer offeredQt = interchange.getQuantityFromSender();

		if (!verifyAvailability(elFromSender, offeredQt)) {
			return;
		}

		// Hold in temporal

		Integer result = inventory - offeredQt;
		elFromSender.setQuantity(result);

		offers.add(0, interchange);

		// Show offer to others
		notyfyInterchange(offers, EnumEventType.REQUEST_TRADE);

		logger.info("Counter" + id_counter);
		id_counter++;
		String sender = interchange.getSender().getUser().getLogin();
		MessageEvent event = new MessageEvent(this, UtopiaUtil.getId(),
				EnumMsgs.NEW_OFFER, sender);
		notifyMsg(event);

	}

	private boolean verifyAvailability(Element element, Integer quantityTosend) {
		boolean availability = true;
		Integer inventory = element.getQuantity();

		if (inventory.compareTo(quantityTosend) < 0) {
			MessageEvent event = new MessageEvent(this, UtopiaUtil.getId(),
					EnumMsgs.NO_AVAILABILITY, element.getIncrementable()
							.getName());
			notifyMsg(event);
			availability = false;
		}

		return availability;
	}

	public void acceptOffer(Interchange interchange, String playerUser) {
		Player player = UtopiaUtil.getPlayerByName(game.getPlayers(),
				playerUser);

		logger.info("Player:: " + player);

		acceptOffer(interchange, player);
	}

	public void acceptOffer(Interchange interchange, Player playerWhoAccept) {
		Player playerWhoOffer = interchange.getSender();
		interchange.setReceiver(playerWhoAccept);

		/*
		 * HashMap<String, Element> receiverElements = playerWhoAccept
		 * .getMaterials(); HashMap<String, Element> senderElements =
		 * playerWhoOffer.getMaterials();
		 */
		List<Element> receiverElements = playerWhoAccept.getMaterials();
		List<Element> senderElements = playerWhoOffer.getMaterials();

		String nameElGivenByReceiver = interchange.getElFromReceiver()
				.getIncrementable().getName();
		String nameElGivenBySender = interchange.getElFromSender()
				.getIncrementable().getName();

		// Element rdFromReceiver = receiverElements.get(nameElGivenByReceiver);
		Element rdFromReceiver = playerService.getElementPlayerByName(
				receiverElements, nameElGivenBySender);

		interchange.setElFromReceiver(rdFromReceiver);
		int quantityFromReceiver = interchange.getQuantityFromReceiver();
		int quantityFromSender = interchange.getQuantityFromSender();

		logger.info("lo q da el receptor " + quantityFromReceiver + " de "
				+ nameElGivenByReceiver);
		logger.info("lo q da el oferente " + quantityFromSender + " de "
				+ nameElGivenBySender);
		logger.info("Verificando disponibilidad");

		if (!verifyAvailability(rdFromReceiver, quantityFromReceiver)) {
			return;
		}

		logger.info("Entregando y recibiendo materiales");
		int avaibleElReceiver = interchange.getElFromReceiver().getQuantity();

		logger.info("disponible receptor  " + avaibleElReceiver);

		logger.info("Quitandole al receptor " + quantityFromReceiver);
		int result = avaibleElReceiver - quantityFromReceiver;

		logger.info("resultante " + result);
		interchange.getElFromReceiver().setQuantity(result);

		logger.info("lo que recibe  " + quantityFromSender);

		// Element rdFromSender = receiverElements.get(nameElGivenBySender);
		Element rdFromSender = playerService.getElementPlayerByName(
				receiverElements, nameElGivenBySender);
		avaibleElReceiver = rdFromSender.getQuantity();

		result = quantityFromSender + avaibleElReceiver;

		logger.info("Dandole al receptor  " + quantityFromSender);
		logger.info("resultante " + result);
		rdFromSender.setQuantity(result);

		logger.info("Dandole al emisor  " + quantityFromReceiver);
		// Element elGivenToSender = senderElements.get(nameElGivenByReceiver);
		Element elGivenToSender = playerService.getElementPlayerByName(
				senderElements, nameElGivenByReceiver);

		int senderQuantity = elGivenToSender.getQuantity();
		result = senderQuantity + quantityFromReceiver;
		elGivenToSender.setQuantity(result);

		String executorName = playerWhoOffer.getUser().getLogin();
		String element1Value = elGivenToSender.getQuantity() + "";
		String affectedElement1 = rdFromSender.getIncrementable().getName();

		String element2Value = rdFromSender.getQuantity() + "";
		String affectedElement2 = rdFromReceiver.getIncrementable().getName();
		String receiverName = playerWhoAccept.getUser().getLogin();

		MessageEvent event = new MessageEvent(this, UtopiaUtil.getId(),
				EnumMsgs.ACCEPTED_OFFER, playerWhoOffer.getUser().getLogin(),
				quantityFromSender, rdFromSender.getIncrementable().getName(),
				quantityFromReceiver, rdFromReceiver.getIncrementable()
						.getName(), playerWhoAccept.getUser().getLogin());

		event.setEventType(EnumEventType.INTERCHANGE);
		event.setAffectedElement1(affectedElement1);
		event.setElement1Value(element1Value);
		event.setExecutorName(executorName);

		event.setAffectedElement2(affectedElement2);
		event.setElement2Value(element2Value);
		event.setReceiverName(receiverName);

		event.setGameElapsedTime(UtopiaUtil.getElapsedTime(playerWhoOffer));
		Calendar cal = Calendar.getInstance();
		event.setExactDate(cal.getTime());

		notifyMsg(event);

		offers.remove(interchange);
		// Show offer to others
		notyfyInterchange(offers, EnumEventType.REQUEST_TRADE);

	}

	private void notyfyInterchange(List<Interchange> offers,
			EnumEventType eventType) {
		List<InterchangeBean> beans = new ArrayList<InterchangeBean>();
		InterchangeBean bean;

		InterchangeEvent event = new InterchangeEvent(this, offers);

		for (InterchangeListener listener : interchangeListeners) {
			listener.requestTrade(event);
		}
	}

	public void removeInterchangeListener(InterchangeListener listener) {
		interchangeListeners.remove(listener);
	}

	public void addInterchangeListener(InterchangeListener listener) {
		interchangeListeners.add(listener);
		// Show offer to others
		notyfyInterchange(offers, EnumEventType.REQUEST_TRADE);
	}

	public void removeInterchangeListener() {
		if (interchangeListeners != null) {
			interchangeListeners.clear();
		}
	}

	public void addMessageListener(MessageListener listener) {
		messagesListeners.add(listener);
	}

	public void removeAllMessageListeners() {
		if (messagesListeners != null) {
			messagesListeners.clear();
		}
	}

	public void removeMessageListener(MessageListener listener) {
		messagesListeners.remove(listener);
	}

	public void notifyMsg(MessageEvent event) {
		if (game != null)
			event.setGameName(game.getName());

		for (MessageListener listener : messagesListeners) {
			listener.notifyMsg(event);
		}
	}

	public List<Interchange> getOffers() {

		return offers;
	}

	public void setOffers(List<Interchange> offers) {
		this.offers = offers;
	}

	@Override
	public void addTradeListener(TradeListener listener) {
		addInterchangeListener(listener);
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public PlayerService getPlayerService() {

		return playerService;
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public Game getGame() {

		return game;
	}

	@Override
	public void cancelOffer(Interchange interchange) {
		Element elFromSender = interchange.getElFromSender();
		Integer inventory = elFromSender.getQuantity();
		Integer offeredQt = interchange.getQuantityFromSender();
		Integer result = inventory + offeredQt;
		elFromSender.setQuantity(result);

		offers.remove(interchange);

		// Show offer to others
		notyfyInterchange(offers, EnumEventType.REQUEST_TRADE);

		logger.info("Counter" + id_counter);
		id_counter++;
		String sender = interchange.getSender().getUser().getLogin();
		MessageEvent event = new MessageEvent(this, UtopiaUtil.getId(),
				EnumMsgs.CANCEL_OFFER, sender);
		notifyMsg(event);
	}

	@Override
	public void modifyOffer(Interchange interchange) {
	}

	@Override
	public void removeTradeListener(TradeListener listener) {
		this.removeInterchangeListener(listener);
	}
}
