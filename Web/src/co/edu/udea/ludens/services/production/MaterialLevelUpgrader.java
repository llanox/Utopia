package co.edu.udea.ludens.services.production;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.IncrementableConstraint;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.enums.EnumMsgs;
import co.edu.udea.ludens.exceptions.LudensException;
import co.edu.udea.ludens.services.ElementService;
import co.edu.udea.ludens.services.PlayerService;
import co.edu.udea.ludens.util.UtopiaUtil;

public class MaterialLevelUpgrader implements LevelUpgraderStrategy {

	public int UPGRADING_LEVEL_DURATION = 30;// seconds
	private static Logger logger = Logger
			.getLogger(MaterialLevelUpgrader.class);

	@Autowired
	PlayerService playerService;

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
}
