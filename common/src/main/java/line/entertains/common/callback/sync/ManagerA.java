package line.entertains.common.callback.sync;

import line.entertains.common.callback.common.DefaultDispatcher;
import line.entertains.common.callback.common.Manager;
import line.entertains.common.callback.common.Worker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagerA extends DefaultDispatcher implements Manager {

	@Override
	public void distributeJob(String job) {
		log.info("receive job from boss: {}", job);
		for (Worker w : wokers) {
			log.info("distribute [{}] to [{}]", job, w);
			w.work(job, ManagerA.this);
		}
	}
	
	@Override
	public String toString() {
		return "ManagerA";
	}
}
