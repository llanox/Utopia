package co.edu.udea.ludens.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udea.ludens.dao.ElementDAO;
import co.edu.udea.ludens.dao.GameDAO;
import co.edu.udea.ludens.dao.PlayerDAO;
import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Incrementable;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.domain.MessageEvent;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.GameProcess;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
public class ElementServiceImpl implements ElementService {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ElementDAO elementDao;

	@Autowired
	private PlayerDAO playerDao;

	@Autowired
	private GameDAO gameDao;

	public ElementServiceImpl() {
	}

	public ElementServiceImpl(GameDAO gameDao, PlayerDAO playerDao,
			ElementDAO elementDao) {
		this.gameDao = gameDao;
		this.playerDao = playerDao;
		this.elementDao = elementDao;
	}

	@Override
	public Set<Element> getAllElements(EnumElementType elementType) {
		Set<Element> elements = new HashSet<Element>();

		return elements;
	}

	@Override
	public Set<Element> getAllElements(String idUser) {
		Set<Element> elements = new HashSet<Element>();

		return elements;
	}

	@Override
	public Set<Element> getFactors(String login) {
		Set<Element> elements = new HashSet<Element>();

		return elements;
	}

	@Override
	public Set<Element> getMaterials(String login) {
		Set<Element> elements = new HashSet<Element>();

		return elements;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public MessageEvent upLevel(String login, String elementName,
			GameProcess gameProcess) {
		System.out.println("game process: " + gameProcess);

		ElementProcess elementProcess = gameProcess.getElementProcess(login);
		MessageEvent event = elementProcess.upLevel(elementName);

		return event;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void save(Element element) {
		logger.info("Saving element ..." + element.getIncrementable().getName());
		elementDao.saveOrUpdate(element);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
	public void delete(Element element) {
		elementDao.delete(element);
	}

	@Override
	public Element findElementByName(String elementName) {
		Element element = elementDao.findElementByName(elementName);

		return element;
	}

	/**
	 * @param elementDao
	 *            the elementDao to set
	 */
	public void setElementDao(ElementDAO elementDao) {
		this.elementDao = elementDao;
	}

	/**
	 * @return the elementDao
	 */
	public ElementDAO getElementDao() {

		return (this.elementDao);
	}

	@Override
	public Element getPopulation(String login) {
		Element population = null;
		List<Element> elements = elementDao.findElementByType(
				EnumElementType.POPULATION, login);

		if (elements != null && !elements.isEmpty())
			population = elements.get(0);

		return population;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void createElementsForEachPlayer(Game game) {
		List<Player> players = game.getPlayers();
		logger.debug("creating elements for : " + players.size());

		for (Player pa : players) {
			for (Incrementable inc : game.getDefaultIncrementables()) {
				Element element = new Element();

				if (EnumElementType.FACTOR == inc.getType()) {
					logger.info("Player " + pa + "Development Factors "
							+ pa.getDevelopmentFactors() + "factor : "
							+ element + " incrementable " + inc
							+ " incrementable name" + inc.getName());

					buildElement(pa, inc, element);
					elementDao.saveOrUpdate(element);

					if (pa.getDevelopmentFactors() == null) {
						/*
						 * HashMap<String, Element> developmentFactors = new
						 * HashMap<String, Element>();
						 * pa.setDevelopmentFactors(developmentFactors);
						 */
						List<Element> developmentFactors = new ArrayList<Element>();
						pa.setDevelopmentFactors(developmentFactors);
					}
					//pa.getDevelopmentFactors().put(inc.getName(), element);
					pa.getDevelopmentFactors().add(element);
					continue;
				}

				if (EnumElementType.MATERIAL == inc.getType()) {
					logger.info("Material : " + element);
					buildElement(pa, inc, element);
					element = (Element) elementDao.saveOrUpdate(element);

					if (pa.getMaterials() == null) {
						/*
						 * HashMap<String, Element> materials = new
						 * HashMap<String, Element>();
						 * pa.setMaterials(materials);
						 */
						List<Element> materials = new ArrayList<Element>();
						pa.setMaterials(materials);
					}
					//pa.getMaterials().put(inc.getName(), element);
					pa.getMaterials().add(element);
					continue;
				}

				if (EnumElementType.POPULATION == inc.getType()) {
					logger.info("population : " + element);
					buildElement(pa, inc, element);
					element = (Element) elementDao.saveOrUpdate(element);
					pa.setPopulation(element);
					continue;
				}
			}
			playerDao.saveOrUpdate(pa);
		}
	}

	private void buildElement(Player pa, Incrementable inc, Element element) {
		element.setIncrementable(inc);
		element.setLevel(0);
		element.setQuantity(inc.getInitialValue());
		element.setCalculatedValue(0);
		element.setActualUpgradingTime(inc.getInitialUpgradingTime());
		element.setPlayer(pa);
		element.setProductionIncrementRate(inc.getProductionIncrementRate());
	}

	public void setPlayerDao(PlayerDAO playerDao) {
		this.playerDao = playerDao;
	}

	public PlayerDAO getPlayerDao() {

		return (this.playerDao);
	}

	public void setGameDao(GameDAO gameDao) {
		this.gameDao = gameDao;
	}

	public GameDAO getGameDao() {

		return (this.gameDao);
	}

	@Override
	public Set<Element> getAllElementsByPlayer(EnumElementType type,
			String login) {
		Set<Element> elements = new HashSet<Element>();
		List<Element> elementsByType = elementDao
				.findElementByType(type, login);

		for (Element el : elementsByType) {
			elements.add(el);
		}

		return elements;
	}

	@Override
	public void checkOutResources(List<IncrementableConstraint> ctrs,
			Element element, Player player) {		
	}
}
