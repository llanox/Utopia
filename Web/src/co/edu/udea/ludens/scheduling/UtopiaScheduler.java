package co.edu.udea.ludens.scheduling;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerBean;
import org.springframework.stereotype.Service;

import co.edu.udea.ludens.services.ServiceLocator;


@Service
public class UtopiaScheduler {

	@Autowired
	private SchedulerFactoryBean scheduleFactory;
	private Scheduler scheduler;	
	private SimpleTriggerBean simpleTrigger;
	private Logger logger = Logger.getLogger(getClass());
	
	
	
	@Autowired
	private JobDetailBean jobDetail;
	
	@Autowired
	private ServiceLocator serviceLocator;
	
	
		
	
	public void schedule(UtopiaTask task, long time) throws SchedulerException {       

		
		simpleTrigger = serviceLocator.getSimpleTriggerBean();
		jobDetail.getJobDataMap().put(UtopiaTask.TASK_NAME, task);
		jobDetail.setName(task.getId());
		simpleTrigger.setJobDetail(jobDetail);		
		simpleTrigger.setRepeatInterval(time);
		simpleTrigger.setName(task.getId());
		
		if(scheduler==null){
			scheduler = scheduleFactory.getScheduler();
			scheduler.start();
		}
	    scheduler.scheduleJob(jobDetail, simpleTrigger);
	    logger.info("Scheduler "+scheduler.getSchedulerName());
		
		
		
	}
	
	
	public SchedulerFactoryBean getScheduleFactory() {
		return scheduleFactory;
	}
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduleFactory(SchedulerFactoryBean scheduleFactory) {
		this.scheduleFactory = scheduleFactory;
	}
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	

	/**
	 * @param serviceLocator the serviceLocator to set
	 */
	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	/**
	 * @return the serviceLocator
	 */
	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}


	public SimpleTriggerBean getSimpleTrigger() {
		return simpleTrigger;
	}


	public JobDetailBean getJobDetail() {
		return jobDetail;
	}


	public void setSimpleTrigger(SimpleTriggerBean simpleTrigger) {
		this.simpleTrigger = simpleTrigger;
	}


	public void setJobDetail(JobDetailBean jobDetail) {
		this.jobDetail = jobDetail;
	}


	
	
	
}
