package line.entertains.concur.lock.reentrant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 
 * @author line
 * @date 2019年1月2日 下午2:58:02
 */
public class StringBuilderDemo {

	private static final int NUM_T = 100;
	
	public static void main(String[] args) {
//		notLock();
//		lock();
		buffer();
	}
	public static void lock() {
		StringBuilder sb2 = new StringBuilder();
		ReentrantLock lock = new ReentrantLock();
		CountDownLatch latch2 = new CountDownLatch(NUM_T);
		for (int i = 0; i < NUM_T; i ++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					lock.lock();
					sb2.append("11111++++++++++");
					lock.unlock();
					latch2.countDown();
				}
			}).start();
		}
		try {
			latch2.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String rslt2 = sb2.toString();
		System.out.println(rslt2.length());
	}
	/**
	 	Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException
				at java.lang.System.arraycopy(Native Method)
				at java.lang.String.getChars(String.java:826)
				at java.lang.AbstractStringBuilder.append(AbstractStringBuilder.java:449)
				at java.lang.StringBuilder.append(StringBuilder.java:136)
				at line.entertains.concur.lock.reentrant.StringBuilderDemo$2.run(StringBuilderDemo.java:50)
				at java.lang.Thread.run(Thread.java:748)
	 */
	public static void notLock() {
		CountDownLatch latch = new CountDownLatch(NUM_T);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < NUM_T; i ++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					sb.append("11111++++++++++");
					latch.countDown();
				}
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String rslt = sb.toString();
		System.out.println(rslt.length());
	}
	
	public static void buffer() {
		CountDownLatch latch = new CountDownLatch(NUM_T);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < NUM_T; i ++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					sb.append("11111++++++++++");
					latch.countDown();
				}
			}).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String rslt = sb.toString();
		System.out.println(rslt.length());
	}
}
