package co.edu.udea.ludens.services.production;

import co.edu.udea.ludens.domain.Element;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.exceptions.LudensException;

public interface LevelUpgraderStrategy {

	public  void upLevel(Element element, Player player) throws LudensException;
}
