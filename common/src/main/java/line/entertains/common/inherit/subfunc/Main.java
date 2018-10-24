package line.entertains.common.inherit.subfunc;

public class Main {

	/**
	 * 子对象，父引用，调用方法，结果为子对象的方法。
	 * 否则，抛出class cast异常
	 */
	public static void test1() {
		SubClass sub = new SubClass();
		sub.func();
		
		SuperClass sup = (SuperClass) sub;
		sup.func();
	}
	
	public static void main(String[] args) {
		test1();
	}
}
