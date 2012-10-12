package co.edu.udea.ludens.services.production;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.enums.EnumElementType;

public class LevelUpgraderFactory {

	public static LevelUpgraderStrategy createLevelUpgrader(Element el) {
		if (EnumElementType.FACTOR == el.getIncrementable().getType()) {

			return new FactorLevelUpgrader();
		} else if (EnumElementType.MATERIAL == el.getIncrementable().getType()) {

			return new MaterialLevelUpgrader();
		}

		return null;
	}
}
