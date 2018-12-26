package line.entertains.concur.lock.synchronized0;


/**
 * 
 * 
 * @author line
 * @date 2018年12月24日 下午3:19:21
 */
public class SynchronizedDemo {
	private int count = 0;
	public synchronized void add() {
		count++;
	}
	public int getCount() {
		return count;
	}
	
	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		SynchronizedDemo demo = new SynchronizedDemo();
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
		System.out.println(demo.getCount());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(demo.getCount());
	}
}
