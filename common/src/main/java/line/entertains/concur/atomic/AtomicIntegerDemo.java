package line.entertains.concur.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * 
 * 
 * @author line
 * @date 2019年1月2日 下午2:40:34
 */
public class AtomicIntegerDemo implements Runnable {

	/** num of  threads */
	private static final int NUM_T = 100000; 
	
	private Integer num = new Integer(0);
	private AtomicInteger atoNum = new AtomicInteger();
	private CountDownLatch latch;
	
	@Override
	public void run() {
		num++;
		atoNum.incrementAndGet();
		latch.countDown();
	}

	@Test
	public void test() {
		AtomicIntegerDemo demo = new AtomicIntegerDemo();
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
		System.out.println(demo.num);
		System.out.println(demo.atoNum.get());
	}
}
