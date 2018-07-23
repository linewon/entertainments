package line.entertains.design_pattern.observer.entity.subject;

import java.util.List;

import line.entertains.design_pattern.observer.entity.observer.Observer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class Subject {

	private List<Observer> observers;
	
	@Getter
	private Integer state;
	public void setState(Integer state) {
		this.state = state;
		updateObservers();
	}
	private void updateObservers() {
		log.info("开始调用 updateObservers() 方法!");
		for (Observer ob : observers) {
			ob.update();
		}
	}
}
