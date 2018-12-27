package line.entertains.concur.lock.reentrant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

import line.entertains.concur.lock.Counter;

/**
 * 
 * 
 * @author line
 * @date 2018年12月26日 下午2:31:04
 */
public class ReentrantLockDemo {

	public static void main(String[] args) {
		final int NUM = 9;
		CountDownLatch latch = new CountDownLatch(NUM);
		ReentrantLock lock = new ReentrantLock();
		
		Counter counter = new Counter();
		/* 这里不用线程池了，直接用线程，调试起来看的清楚一点。 */
//		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < NUM; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					counter.increase();
					lock.unlock();
					latch.countDown();
				}
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter);
	}
}
