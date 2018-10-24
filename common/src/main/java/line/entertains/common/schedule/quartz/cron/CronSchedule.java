package line.entertains.common.schedule.quartz.cron;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import line.entertains.common.schedule.quartz.MyJob;

public class CronSchedule {

	public void jobStart() throws SchedulerException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		
		JobDetail detail = JobBuilder.newJob(MyJob.class).build();
		
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(
						CronScheduleBuilder.cronSchedule("30 * * * * ?")
						).build();
		
		scheduler.scheduleJob(detail, trigger);
		scheduler.start();
	}
}
