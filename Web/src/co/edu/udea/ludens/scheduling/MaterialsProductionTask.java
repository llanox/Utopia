package co.edu.udea.ludens.scheduling;

import java.util.Date;

import co.edu.udea.ludens.services.GameProcess;
import org.apache.log4j.Logger;

public class MaterialsProductionTask implements Runnable {

	GameProcess gameProcess;
	private Logger logger = Logger.getLogger(this.getClass());

	public MaterialsProductionTask(GameProcess gameProcess) {

		this.gameProcess = gameProcess;

	}

	@Override
	public void run() {
		logger.debug("Executing producction .." + new Date());
		gameProcess.produceElements();
	}
}
