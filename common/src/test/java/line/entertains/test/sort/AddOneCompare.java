package line.entertains.test.sort;

import org.junit.Test;

/**
 * seems to be same
 * 
 * @author line
 * @date 2018年12月20日 上午9:26:05
 */
public class AddOneCompare {

	@Test
	public void testPlusPlus() {
		int sum = 0;
		long start = System.currentTimeMillis();
		for (int j = Integer.MAX_VALUE; j > 0; j--) {
			sum = 0;
			for (int i = 100000; i > 0; i--) {
				sum++;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(sum);
		System.out.println("\n\n");
	}

	@Test
	public void testPlusOne() {
		int sum = 0;
		long start = System.currentTimeMillis();
		for (int j = Integer.MAX_VALUE; j > 0; j--) {
			sum = 0;
			for (int i = 100000; i > 0; i--) {
				sum += 5;
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(sum);
		System.out.println("\n\n");
	}
}
