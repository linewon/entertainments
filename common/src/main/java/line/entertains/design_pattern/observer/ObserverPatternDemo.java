package line.entertains.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

import line.entertains.design_pattern.observer.entity.observer.Observer;
import line.entertains.design_pattern.observer.entity.observer.impl.LogObserver;
import line.entertains.design_pattern.observer.entity.observer.impl.PhoneCallObserver;
import line.entertains.design_pattern.observer.entity.subject.Subject;

/**
 * 模拟一个最简单的 Listener
 * 
 * @author line
 */
public class ObserverPatternDemo {

	private static void test1() {
		// 初始化 observers
		List<Observer> obList = new ArrayList<>();
		obList.add(new LogObserver());
		obList.add(new PhoneCallObserver());
		
		// 初始化 state 为 0
		Subject subject = new Subject(obList, 0);
		subject.setState(1);
		subject.setState(2);
	}
	
	public static void main(String[] args) {
		test1();
	}
}
