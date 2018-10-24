package line.entertains.common.schedule.quartz;

import org.quartz.SchedulerException;

import line.entertains.common.schedule.quartz.cron.CronSchedule;
import line.entertains.common.schedule.quartz.simple.MyScheduler;

public class Main {
	public static void testSimple() throws SchedulerException {
		MyScheduler scheduler = new MyScheduler();
		scheduler.jobStart();
	}
	
	public static void testCron() throws SchedulerException {
		CronSchedule schedule = new CronSchedule();
		schedule.jobStart();
	}
	
	public static void main(String[] args) throws SchedulerException {
		testCron();
	}
}