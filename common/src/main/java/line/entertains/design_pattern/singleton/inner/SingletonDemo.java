package line.entertains.design_pattern.singleton.inner;


/**
 * 
 * 看
 * @author line
 * @date 2019年1月16日 上午11:38:24
 */
public class SingletonDemo {
	
	static {
		System.out.println("OUTTER class");
	}
	private SingletonDemo() {
	}
	public static SingletonDemo getInstance() {
		return Holder.singleton;
	}
	
	// 私有的静态内部类,只有这个类内才能访问
	private static class Holder {
		static {
			System.out.println("INNER class");
		}
		// 该对象不会在外部类加载时便创建,避免受外部类其它静态方法影响
		private static SingletonDemo singleton = new SingletonDemo(); // 利用java类加载，来保证线程安全
	}
}
