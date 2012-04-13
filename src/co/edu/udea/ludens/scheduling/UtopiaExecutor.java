package co.edu.udea.ludens.scheduling;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class UtopiaExecutor extends QuartzJobBean {
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	protected void executeInternal(JobExecutionContext ctx)throws JobExecutionException {
		
	
		
	}
	
		

}
