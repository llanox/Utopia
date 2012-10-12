package co.edu.udea.ludens.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.services.MessagesCentralService;
import co.edu.udea.ludens.services.PlayerService;

@Service("messagesCentralService")
@Scope("singleton")
public class MessagesCentralServiceImpl implements MessagesCentralService {

	private static Logger logger = Logger
			.getLogger(MessagesCentralServiceImpl.class);
	private static final int MAX_STORED_MESSAGES = 160000;

	@Autowired
	PlayerService playerService;

	@Override
	public void notifyMsg(MessageEvent event) {
		Player player = event.getAffectedPlayer();

		logger.info("menssage " + event.getMsg() + " messageType "
				+ event.getMsgType());

		if (player != null) {
			List<MessageEvent> messages = player.getEvents();

			if (messages == null) {
				messages = new ArrayList<MessageEvent>();
				player.setEvents(messages);
			}

			logger.info("Adding private message " + player.getUser().getLogin()
					+ " " + event.getMsg());
			messages.add(0, event);
			playerService.save(player);
		} else {
			List<Player> players = playerService.findAllPlayersByGameName(event
					.getGameName());

			for (Player pler : players) {
				List<MessageEvent> publicMessages = pler.getEvents();

				if (publicMessages == null)
					publicMessages = new ArrayList<MessageEvent>();

				publicMessages.add(0, event);
				pler.setEvents(publicMessages);
				playerService.save(pler);
			}
		}
	}
}
