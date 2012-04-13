package co.edu.udea.ludens.scheduling;

import org.springframework.scheduling.annotation.Async;

public interface UtopiaTask {
	public static final String TASK_NAME="utopiaTask";
	@Async
	public void doWork();
	public String getId();
}
