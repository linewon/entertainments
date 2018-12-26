package line.entertains.concur.lock.synchronized0;

/**
 * 
 * 
 * @author line
 * @date 2018年12月24日 下午3:02:02
 */
public class UnsynchronizedDemo {
	private int count = 0;

	public void add() {
		count++;
	}

	public int getCount() {
		return count;
	}

	public static void main(String[] args) {
		//		test1();
		test2();
	}

	/**
	 * synchronized in main method
	 */
	private static void test2() {
		UnsynchronizedDemo demo = new UnsynchronizedDemo();

		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					synchronized (UnsynchronizedDemo.class) {
						System.out.println(Thread.currentThread().getName());
						System.out.println(demo.getCount());
						for (int i = 0; i < 1000; i++) {
							demo.add();
						}
						System.out.println(demo.getCount());
					}
				}
			}).start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(demo.getCount());
	}

	/**
	 * unsynchronized test
	 */
	private static void test1() {
		UnsynchronizedDemo demo = new UnsynchronizedDemo();
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 1000; i++) {
						demo.add();
					}
				}
			}).start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(demo.getCount());
	}
}
