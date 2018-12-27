package line.entertains.concur.lock.volatile0;

/**
 * volatile: 易变的
 * 结合JMM(Java Memory Model)更好理解
 * 
 * @author line
 * @date 2018年12月25日 上午10:09:41
 */
public class VolatileDemo {

//	private volatile boolean canRead = false;
	private boolean canRead = false;
	private int a = 0;

	/**
	 * happens-before:
	 * a = 1 should happen befor canRead = true.
	 * in case that other thread will read before a was modified
	 */
	public void write() {
		a = 1;
		canRead = true;
	}

	public void read() {
		int i = 0;
		for (;; i++) {
//		for (; i < 1000; i++) {
			/*
			 * 如果没有用volatile修饰，若此时threadA先进行read操作，
			 * 则threadB对canRead的修改，不会起作用。
			 */
			if (canRead) {
				int r = a; // read a
				System.out.println(r);
				break;
			}
			/*
			 * 如果这个地方加上sleep，
			 * 等线程苏醒的时候，好像就会主动刷新工作内存，
			 * 导致较最开始读到的canRead也更新了。
			 */
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		if (i == 1000) {
			System.out.println("BREAK BY FORCE!");
		}
	}

	public static void main(String[] args) {
		final VolatileDemo demo = new VolatileDemo();
		Thread write = new Thread(new Runnable() {
			@Override
			public void run() {
				demo.write();
			}
		});
		Thread read = new Thread(new Runnable() {
			@Override
			public void run() {
				demo.read();
			}
		});

		read.start();
		/*
		 * 这个地方，如果没有sleep操作的话，
		 * 即使不加volatile，程序也能退出循环。
		 * 个人理解，应该是jvm指令重排的结果。
		 */
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		write.start();
	}
}
