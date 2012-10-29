package co.edu.udea.ludens.services.production;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.util.UtopiaUtil;

public class FactorLevelUpgrader implements LevelUpgraderStrategy {
	public int UPGRADING_LEVEL_DURATION = 30;// seconds
	private static Logger logger = Logger.getLogger(FactorLevelUpgrader.class);

	@Autowired
	PlayerService playerService;

	@Override
	public void upLevel(Element element, Player player) throws LudensException {
		boolean error = false;
		Integer actualLevel = element.getLevel();
		Integer newLevel = actualLevel + 1;
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
		playerService.checkOutResources(ctrs, element, player);

		// Here, we decrement each resource consumed to up to the next level
		for (IncrementableConstraint pk : ctrs) {
			Integer neededQuantity = pk.getQuantity();
			String resourceName = pk.getElementName();
			Element resource = playerService.getElementPlayerByName(
					player.getElements(), resourceName);
			// Element resource = player.getMaterials().get(resourceName);
			Integer quantity = resource.getQuantity() - neededQuantity;
			resource.setQuantity(quantity);
		}
		UtopiaUtil.addStartTimePlayer(player);

		try {
			logger.info("Esperando " + element.getActualUpgradingTime()
					+ " segundos para subir nivel");

			player.setProducing(true);
			Thread.sleep(element.getActualUpgradingTime() * 1000);
			logger.info("Listo!! subir nivel de "
					+ element.getIncrementable().getName());
		} catch (InterruptedException e) {
			logger.info("Error subiendo nivel", e);
			error = true;
		}

		incrementCapacity(element);

		UtopiaUtil.calculateCoverage(element, player);

		if (!error) {
			element.setLevel(newLevel);
			UtopiaUtil.updateUpgradingDelay(element);
			player.setProducing(false);
		}
	}

	private void incrementCapacity(Element element) {
		Integer n = element.getLevel();
		Integer lambda = element.getProductionIncrementRate();
		Integer p0 = element.getIncrementable().getInitialValue();
		double increment = 0.0;

		logger.info("Incrementando capacidad factor " + n + " lambda " + lambda
				+ " p0 " + p0);

		increment = UtopiaUtil.calculateExpGrowth(p0, lambda, n);

		Integer result = (int) (element.getQuantity() + increment);

		logger.info("P0 " + p0 + " Increment " + increment + "  "
				+ element.getIncrementable().getName() + " result " + result);
		logger.info(element.getIncrementable().getName() + " Antes de eventos "
				+ result);
		for (Integer change : element.getChangeEvents()) {
			result = result + change;
		}

		logger.info(element.getIncrementable().getName()
				+ " despues de eventos " + result);

		element.setQuantity(result);
	}
}
