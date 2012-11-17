package co.edu.udea.ludens.web.restful.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.GameProcess;
import co.edu.udea.ludens.services.MessagesCentralService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.services.ProcessHolderService;
import co.edu.udea.ludens.util.LudensConstants;

@Controller
@RequestMapping(value = "/elements")
public class ElementController {

	Logger logger = Logger.getLogger(ElementController.class);

	@Autowired
	ElementService elementService;

	@Autowired
	PlayerService playerService;

	@Autowired
	MessagesCentralService messagesCentralService;

	@Autowired
	ProcessHolderService processHolderService;

	@RequestMapping(value = "/all/{login}", method = RequestMethod.GET)
	public @ResponseBody
	List<Element> getAllElements(@PathVariable String login) {
		logger.info("getAllElements " + login);

		Set<Element> elements = elementService.getAllElements(login);
		List<Element> listaElements = new ArrayList<Element>(elements);

		return listaElements;
	}

	@RequestMapping(value = "/factors/{login}", headers = "Accept=*/*", method = RequestMethod.GET)
	public @ResponseBody
	List<Element> getAllFactors(@PathVariable String login) {
		Set<Element> elements = elementService.getFactors(login);
		List<Element> listaElements = new ArrayList<Element>(elements);

		return listaElements;
	}

	@RequestMapping(value = "/materials/{login}", headers = "Accept=*/*", method = RequestMethod.GET)
	public @ResponseBody
	List<Element> getAllMaterials(@PathVariable String login) {
		Set<Element> elements = elementService.getMaterials(login);
		List<Element> listaElements = new ArrayList<Element>(elements);

		return listaElements;
	}

	@RequestMapping(value = "/population/{login}", headers = "Accept=*/*", method = RequestMethod.GET)
	public @ResponseBody
	List<Element> getPopulation(@PathVariable String login) {
		List<Element> listPopulation = new ArrayList<Element>();
		Element actualPopulation = null;

		actualPopulation = elementService.getPopulation(login);

		listPopulation.add(actualPopulation);

		return listPopulation;
	}

	@RequestMapping(value = "/notifications/{login}", headers = "Accept=*/*", method = RequestMethod.GET)
	public @ResponseBody
	List<MessageEvent> getNotifications(@PathVariable String login) {
		List<MessageEvent> messagesEvent = new ArrayList<MessageEvent>();
		Player player = playerService.findPlayer(login);

		if (player != null)
			messagesEvent = player.getEvents();

		return messagesEvent;
	}

	@RequestMapping(value = "/upLevel/{elementName}/{login}", headers = "Accept=*/*", method = RequestMethod.GET)
	public @ResponseBody
	MessageEvent upLevel(@PathVariable String elementName,
			@PathVariable String login) {
		MessageEvent event = new MessageEvent();

		try {
			elementName = URLDecoder.decode(elementName,
					LudensConstants.CHAR_SET);
		} catch (UnsupportedEncodingException e) {
			logger.error("Decoding error", e);

			return event;
		}

		Player player = playerService.findPlayer(login);
		GameProcess gameProcess = (GameProcess) processHolderService
				.findProcessById(GameProcess.class, player.getGame().getName());

		event = elementService.upLevel(login, elementName, gameProcess);

		return event;
	}

	public PlayerService getPlayerService() {

		return (this.playerService);
	}

	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}
}
