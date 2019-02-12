package junit;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * 测试怎么让Junit的test方法按预想的顺序执行！
 * 目前看来，只能让方法名按字母顺序，可以保证执行上可预见的书序。
 * 
 * @author line
 * @date 2019年1月28日 下午3:42:05
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunitMethodOrderTest {

	@Test
	public void cccc() {
		System.out.println(1);
	}
	@Test
	public void aa() {
		System.out.println(2);
	}
	@Test
	public void xcvbwrt() {
		System.out.println(3);
	}
}
