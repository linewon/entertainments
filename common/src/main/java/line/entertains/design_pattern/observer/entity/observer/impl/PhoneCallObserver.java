package line.entertains.design_pattern.observer.entity.observer.impl;

import line.entertains.design_pattern.observer.entity.observer.Observer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneCallObserver extends Observer {

	@Override
	public void update() {
		log.info("observe subject state change. make a phone call!");
	}

}
