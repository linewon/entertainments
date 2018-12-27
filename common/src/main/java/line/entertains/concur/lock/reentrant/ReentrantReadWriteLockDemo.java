package line.entertains.concur.lock.reentrant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.base.Joiner;

import line.entertains.concur.SleepUtils;

/**
 * 读写锁测试demo
 * 
 * @author line
 * @date 2018年12月27日 下午3:41:31
 */
public class ReentrantReadWriteLockDemo {

	public static void main(String[] args) {
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		StringBuilder sb = new StringBuilder("WELCOME!\n");
//		test1(sb, lock);
		test2(sb, lock);
	}
	/**
	 * 简单读写锁测试
	 * 1. 新建一个写线程，写
	 * 2. 新建几个读线程，读
	 * 为了debug写的
	 */
	public static void test1(StringBuilder sb, ReentrantReadWriteLock lock) {
		CountDownLatch latch = new CountDownLatch(2);
		MyWrite write = new MyWrite(sb, lock, latch);
		MyRead read = new MyRead(sb, lock, latch);

		new Thread(write).start();
		SleepUtils.sleep(500); // 为了让写操作能够先获得lock
		new Thread(read).start();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n********** Finally:\n" + sb.toString());
	}
	/**
	 * 随机生成读线程或写线程
	 */
	public static void test2(StringBuilder sb, ReentrantReadWriteLock lock) {
		Random ran = new Random();
		final int CNT = 5; // 小一点为了debug，大一点为了print
		CountDownLatch latch = new CountDownLatch(CNT);
		for (int i = 0; i < CNT; i++) {
			My my = null;
			boolean flag = ran.nextBoolean();
			if (flag) { // read
				my = new MyRead(sb, lock, latch);
			} else { // write
				my = new MyWrite(sb, lock, latch);
			}
			new Thread(my).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n********** Finally:\n" + sb.toString());
	}

	private static abstract class My implements Runnable {
		/** 模拟一个可编辑的文本 */
		protected StringBuilder sb;
		/** 这里直接提供一个ReentrantReadWriteLock，读、写锁让子类自己去弄吧 */
		protected ReentrantReadWriteLock lock;
		/** CountDownLatch */
		protected CountDownLatch latch;
		/** 是线程私有的 */
		private Random random = new Random(); // 不设随机种子的原因：1.线程私有，则每个线程的第一个数都一样； 2.若非线程私有，则需要考虑线程安全问题。
		/** 是线程私有的 */
		private DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS"); // 暂时不想考虑太多与demo无关的问题（指线程安全问题）

		public My(StringBuilder sb, ReentrantReadWriteLock lock, CountDownLatch latch) {
			this.sb = sb;
			this.lock = lock;
			this.latch = latch;
		}

		protected int randSec() {
			return random.nextInt(3) + 1;
		}

		protected String date() {
			return df.format(new Date()); // 每次都是new，是线程安全的
		}
	}

	/**
	 * 读线程，打印StringBuilder
	 */
	private static class MyRead extends My {
		public MyRead(StringBuilder sb, ReentrantReadWriteLock lock, CountDownLatch latch) {
			super(sb, lock, latch);
		}

		@Override
		public void run() {
			super.lock.readLock().lock();
			String date = super.date(); // 在这里记录获取读锁的时间
			int randSec = randSec();
			SleepUtils.sleep(randSec * 1000);
			/*
			 * 这个操作，是线程安全的。因为之前获取了读锁，StringBuilder不会发生改变。
			 */
			String readString = '\n' + super.sb.toString();
			String name = Thread.currentThread().getName();
			System.out.println(Joiner.on("  ").join("\nREAD", date, name, randSec, readString));
			super.lock.readLock().unlock(); // 这里，锁的释放时机。。finally什么什么的
			super.latch.countDown();
		}
	}

	/**
	 * 写线程，修改StringBuilder
	 */
	private static class MyWrite extends My {
		public MyWrite(StringBuilder sb, ReentrantReadWriteLock lock, CountDownLatch latch) {
			super(sb, lock, latch);
		}

		@Override
		public void run() {
			super.lock.writeLock().lock();
			String date = super.date(); // 在这里记录获取写锁的时间
			int randSec = randSec();
			SleepUtils.sleep(randSec * 1000);
			String name = Thread.currentThread().getName();
			String writing = Joiner.on('+').join(name, randSec);
			super.sb.append(writing).append('\n');
			System.out.println(Joiner.on("  ").join("\nWRITE", date, name, randSec, writing));
			super.lock.writeLock().unlock();
			super.latch.countDown();
		}

	}
}
