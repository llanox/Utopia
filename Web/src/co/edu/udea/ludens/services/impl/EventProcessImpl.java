package co.edu.udea.ludens.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.UnexpectedEvent;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.services.EventProcess;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.util.MessageListener;
import co.edu.udea.ludens.util.UnexpectedEventListener;
import co.edu.udea.ludens.util.UtopiaUtil;

public class EventProcessImpl implements EventProcess {

	private Game game;
	private static final String EVENT_PROCESS = "eventProcess";

	@Autowired
	PlayerService playerService;

	private List<UnexpectedEventListener> listeners = new ArrayList<UnexpectedEventListener>();
	private List<MessageListener> messageListener = new ArrayList<MessageListener>();
	Logger logger = Logger.getLogger(EventProcessImpl.class);

	public void eventHappening() {
		if (game == null)
			return;

		List<UnexpectedEvent> unexpectedEvents = game.getUnexpectedEvents();
		int length = unexpectedEvents.size();
		Random rand = new Random();
		int selector = rand.nextInt(length);
		boolean factor = false;

		UnexpectedEvent uevt = unexpectedEvents.get(selector);
		if (uevt == null)
			return;

		List<Player> players = playerService.findAllPlayersByGameName(game
				.getName());

		for (Player player : players) {

			boolean meetReq = UtopiaUtil.meetReqToGettingStart(player);

			if (!meetReq) {
				logger.info("No cumple");
				continue;
			}

			HashMap<String, Element> factors = player.getDevelopmentFactors();
			HashMap<String, Element> materials = player.getMaterials();

			String affectedElement = uevt.getElementName();
			Integer changePortion = uevt.getQuantity();
			int result = 0;
			Element element = factors.get(affectedElement);
			factor = true;
			MessageEvent event = null;

			if (element == null) {
				element = materials.get(affectedElement);
				factor = false;
			}

			int elementQty = element.getQuantity();
			int change = (elementQty * changePortion) / 100;

			if (uevt.isGoodEvent()) {
				result = change + elementQty;
				element.setQuantity(result);
				element.getChangeEvents().add(change);

			} else {
				result = elementQty - change;
				if (result < 0)
					result = 0;

				element.setQuantity(result);
				element.getChangeEvents().add(-change);
			}

			if (uevt.isGoodEvent()) {
				event = new MessageEvent(this, UtopiaUtil.getId(),
						EnumMsgs.POSITIVE_UNEXPECTED_EVENT, uevt.getMessage(),
						elementQty, result);
			} else {
				event = new MessageEvent(this, UtopiaUtil.getId(),
						EnumMsgs.NEGATIVE_UNEXPECTED_EVENT, uevt.getMessage(),
						elementQty, result);
			}
			event.setAffectedPlayer(player);
			notifyUnexpectedEvent(event);
		}
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

	public void notifyUnexpectedEvent(MessageEvent event) {
		for (UnexpectedEventListener listener : listeners) {
			listener.eventHappenning(event);
		}
	}

	public void removeUnexpectedEventListener(UnexpectedEventListener listener) {
		listeners.remove(listener);
	}

	public void addUnexpectedEventListener(UnexpectedEventListener listener) {
		listeners.add(listener);

	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}

	public PlayerService getPlayerService() {

		return (this.playerService);
	}

	@Override
	public void addMessageListener(MessageListener listener) {
		messageListener.add(listener);

	}

	@Override
	public void removeMessageListener(MessageListener listener) {
		messageListener.remove(listener);

	}

	public String getId() {

		return (EVENT_PROCESS + "_" + game.getName());
	}
}
