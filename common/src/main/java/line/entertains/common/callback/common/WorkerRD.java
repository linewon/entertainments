package line.entertains.common.callback.common;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerRD implements Worker {

	@Override
	public void work(String job, Dispatcher dispatcher){
		log.info("rd: receive a job --{} from --{}", job, dispatcher);
		try {
			Thread.sleep(8 * 1000);
		} catch (InterruptedException e) {
			log.error("", e);
		}
		String result = "RD-RD-RD...";
		log.info("rd finish job. get the result :{}", result);

		dispatcher.reportJob(result);
	}

	@Override
	public String toString() {
		return "WorkerRD";
	}
}
