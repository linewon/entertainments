package line.entertains.common.callback.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerFE implements Worker {

	@Override
	public void work(String job, Dispatcher dispatcher) {
		log.info("fe: receive a job --{} from --{}", job, dispatcher);
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			log.error("", e);
		}
		String result = "FE*FE*FE";
		log.info("fe finish job. get the result :{}", result);

		dispatcher.reportJob(result);
	}

	@Override
	public String toString() {
		return "WorkerFE";
	}
}
