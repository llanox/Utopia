package co.edu.udea.ludens.scheduling;

import org.springframework.scheduling.annotation.Scheduled;

public class UtopiaProcessor {
	
	private Worker worker;
	
	
    @Scheduled(fixedDelay = 1000)
    public void process() {
     
    	if(worker!=null){    		
   
    	
    		
    		if(worker.elapsedTime() >= worker.thresholdTime()){
    				worker.work();
       		}
    	}
       
    }

}
