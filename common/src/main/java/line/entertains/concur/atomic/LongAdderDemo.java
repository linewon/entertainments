package line.entertains.concur.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Test;

/**
 * 
 * 
 * @author line
 * @date 2019年1月3日 上午9:34:17
 */
public class LongAdderDemo implements Runnable {
	
	/** num of  threads */
	private static final int NUM_T = 100000; 
	
	private LongAdder longAdder = new LongAdder();
	private CountDownLatch latch;
	
	@Override
	public void run() {
		longAdder.increment();
		latch.countDown();
	}
	
	@Test
	public void test() {
		LongAdderDemo demo = new LongAdderDemo();
		CountDownLatch latch = new CountDownLatch(NUM_T);
		demo.latch = latch;
		for (int i = 0; i < NUM_T; i++) {
			new Thread(demo).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(demo.longAdder.sum());
	}
}
