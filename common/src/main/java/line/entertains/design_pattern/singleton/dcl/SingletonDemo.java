package line.entertains.design_pattern.singleton.dcl;

/**
 * 
 * 
 * @author line
 * @date 2019年1月16日 上午11:38:42
 */
public class SingletonDemo {
	
	private volatile static SingletonDemo singleton = null; // volatile关键字保证了任何线程在任意时刻，都能获取到最新的singleton变量。
	
	public static SingletonDemo getInstance() {
		if (singleton == null) { // 如果没有volatile关键字的话，只有这里的if判断会生效，下一个if判断，由于jvm判断内存中的变量没有被改变过，会优化省去。
			synchronized (SingletonDemo.class) { // synchronized关键字保证了任意时刻，最多都只有一个线程在对singleton变量进行赋值
				if (singleton == null) { // 获取到锁之后再进行一次判断
					singleton = new SingletonDemo();
				}
			}
		}
		return singleton;
	}
	private SingletonDemo() {
	}
	
	public synchronized void show() {
		System.out.println("show what?");
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					SingletonDemo s = SingletonDemo.getInstance();
					s.show();
				}
			}).start();
		}
	}
}
