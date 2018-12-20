package line.entertains.algo.sort.base;

import java.util.Random;

/**
 * 暂时只用来产生整型数组
 * 
 * @author line
 * @date 2018年12月20日 下午3:02:38
 */
public class TestUtils {

	/**
	 * @param size
	 * @param max
	 * @param seed to fix the result array
	 */
	public static Integer[] generate(int size, int seed) {
		int bound = size + 1;
		Random random = new Random(seed);
		Integer[] array = new Integer[size];
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(bound);
		}
		return array;
	}

	public static boolean isAscending(Integer[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				return false;
			}
		}
		return true;
	}
}
