package co.edu.udea.ludens.scheduling;

import org.springframework.stereotype.Component;


@Component("syncWorker")
public interface Worker extends Runnable {
	
	public void run();

	public void work();

	public long thresholdTime();


	public long elapsedTime();
	
	
	public String getId();

}
