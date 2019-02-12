package line.entertains.design_pattern.singleton;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * 
 * 
 * @author line
 * @date 2019年1月16日 下午12:52:31
 */
public class SingletonTest {

	private static final int N = 5;
	private static List<Thread> list = new ArrayList<>(N);
	
	@Test
	public void dcl() {
		for (int i = 0; i < N; i++) {
			list.add(new Thread(new Runnable() {
				@Override
				public void run() {
					line.entertains.design_pattern.singleton.dcl.SingletonDemo singleton = line.entertains.design_pattern.singleton.dcl.SingletonDemo.getInstance();
					singleton.show();
				}
			}));
		}
	}
	
	@Test
	public void inner() {
		for (int i = 0; i < N; i++) {
			list.add(new Thread(new Runnable() {
				@Override
				public void run() {
					line.entertains.design_pattern.singleton.inner.SingletonDemo.getInstance();
				}
			}));
		}
	}
	
	@AfterClass
	public static void start() {
		for (Thread thread : list) {
			thread.start();
		}
	}
}
