package co.edu.udea.ludens.services.production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.domain.Player;

import co.edu.udea.ludens.enums.EnumElementType;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.ElementProcess;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.util.UtopiaUtil;
import co.edu.udea.ludens.web.ElementBean;

public class ProductProducerFactory {

	// No se que problemas hayan con ese "static"
	@Autowired
	private static PlayerService playerService;

	private static Logger logger = Logger
			.getLogger(ProductProducerFactory.class);

	public int UPGRADING_LIVEL_DURATION = 30;// seconds

	public static List<ElementBean> initBeans(
			HashMap<String, Element> elements, ElementProcess process) {
		List<ElementBean> elementBeans = new ArrayList<ElementBean>();

		for (Object key : elements.keySet()) {
			Element incre = null;
			Integer production = 0;
			incre = elements.get(key);
			ElementBean bean = new ElementBean();
			bean.setElement(incre);
			bean.setProduction(production);
			bean.setProcess(process);
			elementBeans.add(bean);
		}

		return elementBeans;
	}

	public static List<ElementBean> initPopulation(Element population,
			ElementProcess process) {
		List<ElementBean> elementBeans = new ArrayList<ElementBean>();
		Element incre = null;
		Integer production = 0;
		incre = population;
		ElementBean bean = new ElementBean();
		bean.setElement(incre);
		bean.setProduction(production);
		bean.setProcess(process);
		elementBeans.add(bean);

		return elementBeans;
	}

	public static void upLevel(Element element, Player player)
			throws LudensException {
		boolean error = false;
		Integer actualLevel = element.getLevel();
		Integer newLevel = actualLevel + 1;

		/*
		 * List<IncrementableConstraint> ctrs =
		 * element.getLevelConstraints().get( newLevel + "");
		 */
		List<IncrementableConstraint> ctrs = playerService
				.getIncrementableConstraintByLevel(
						element.getLevelConstraints(), newLevel);
		// if didn't find resources constraints for this level then throw an
		// exception
		if (ctrs == null) {
			throw new LudensException(EnumMsgs.CANT_UP_LEVEL, element
					.getIncrementable().getName(), newLevel);
		}

		// we check out resources in order to know if we have enough to up the
		// level
		UtopiaUtil.checkOutResources(ctrs, element, player);
		// Here, we have decremented each resource consumed to up to the next
		// level
		for (IncrementableConstraint pk : ctrs) {
			Integer neededQuantity = pk.getQuantity();
			String resourceName = pk.getElementName();
			Element resource = playerService.getElementsByName(
					player.getMaterials(), resourceName).get(0);
			Integer quantity = resource.getQuantity() - neededQuantity;
			resource.setQuantity(quantity);
		}

		// start to track time in order to calculate population increase
		UtopiaUtil.addStartTimePlayer(player);

		try {
			logger.info("Esperando " + element.getActualUpgradingTime()
					+ " segundos para subir nivel");
			;
			player.setProducing(true);
			Thread.sleep(element.getActualUpgradingTime() * 1000);
			logger.info("Listo subir nivel de "
					+ element.getIncrementable().getName());

		} catch (InterruptedException e) {
			logger.info("Error esperando para subir nivel", e);
			error = true;
		}

		if (!error) {
			element.setLevel(newLevel);
			UtopiaUtil.updateUpgradingDelay(element);
			player.setProducing(false);
		}
	}

	public static ProducerStrategy createProducer(EnumElementType productType) {
		if (EnumElementType.FACTOR == productType) {
			return new FactorProducerWithGrowthRate();
		}

		if (EnumElementType.MATERIAL == productType) {
			return new MaterialProducerWithGrowthRate();
		}

		if (EnumElementType.POPULATION == productType) {
			return new PopulationProducerWithGrowthRate();
		}

		return null;
	}

	public static Double calculateExpGrowth(int p0, double lambda, int n) {
		logger.info("Calculating exp ..");

		double lambdaD = lambda / 100;

		logger.info("LambdaD .." + lambdaD);

		Double result = p0 * Math.exp(lambdaD * n);

		logger.info("result .." + result);

		return result;
	}
}
