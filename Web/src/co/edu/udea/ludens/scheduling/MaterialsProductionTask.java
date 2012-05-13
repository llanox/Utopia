package co.edu.udea.ludens.scheduling;

import co.edu.udea.ludens.services.GameProcess;

public class MaterialsProductionTask implements Runnable {

	GameProcess gameProcess;
	
	
	public MaterialsProductionTask(GameProcess gameProcess){
		
		this.gameProcess = gameProcess;
		
	}
	
	
	@Override
	public void run() {		
		
		gameProcess.produceElements();
	}
	
	

}
