package line.entertains.common.callback.common;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultDispatcher implements Dispatcher {

	protected List<Worker> wokers;
	
	public DefaultDispatcher() {
		wokers = new ArrayList<>();
		wokers.add(new WorkerRD());
		wokers.add(new WorkerFE());
	}
	
	@Override
	public void reportJob(String result) {
		log.info("report to BOSS : {}", result);		
	}
}
