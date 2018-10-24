package line.entertains.common.schedule.quartz;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		System.out.printf("Task: %s, Current time: %s\n", "MYJOB", LocalDateTime.now());
		
		log.info("JOB EXECUTION. START!");
		log.info("current time print: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		log.info("JOB EXECUTION. END!");
	}
}
