package line.entertains.common.callback.async;

import line.entertains.common.callback.common.DefaultDispatcher;
import line.entertains.common.callback.common.Manager;
import line.entertains.common.callback.common.Worker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagerB extends DefaultDispatcher implements Manager {

	@Override
	public void distributeJob(String job) {
		log.info("receive job from boss: {}", job);
		for (Worker w : wokers) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					w.work(job, ManagerB.this);
				}
			}).start();
			
			log.info("distribute [{}] to [{}]", job, w);
		}
	}

	@Override
	public String toString() {
		return "ManagerB";
	}
}
