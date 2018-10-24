package line.entertains.common.schedule.quartz.simple;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import line.entertains.common.schedule.quartz.MyJob;

public class MyScheduler {

	private Scheduler scheduler;
	
	public MyScheduler() throws SchedulerException {
			scheduler = new StdSchedulerFactory().getScheduler();
	}
	
	public void jobStart() throws SchedulerException {
		//创建任务
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).build();
        //创建触发器 每3秒钟执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                            .build();
        //将任务及其触发器放入调度器
        scheduler.scheduleJob(jobDetail, trigger);
        //调度器开始调度任务
        scheduler.start();
        
//        JobDetail detail = (JobDetail) JobBuilder.newJob(jobDetail.getJobClass());
	}
}
