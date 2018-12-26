package line.entertains.concur.lock.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 
 * @author line
 * @date 2018年12月26日 上午10:30:08
 */
public class MutexDemo {
//	private volatile int counter = 0;
	private int counter = 0;

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		int num = 1000000;
		CountDownLatch latch = new CountDownLatch(num);
		Mutex mutex = new Mutex();
		MutexDemo demo = new MutexDemo();

		for (int i = 0; i < num; i++) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					mutex.lock();
					demo.counter++;
					mutex.unlock();
					latch.countDown();
				}
			});
		}
		// 让上面的线程有足够的时间跑完！
		//		try {
		//			Thread.sleep(1000);
		//		} catch (InterruptedException e) {
		//			e.printStackTrace();
		//		}
		// 或者直接改用countDownLatch
		try {
			latch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(demo.counter);
		
		pool.shutdown();
	}
}
