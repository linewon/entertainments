package line.entertains.algo.sort;

import line.entertains.algo.sort.base.TestUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author line
 * @date 2018年12月20日 下午3:16:15
 */
@Slf4j
public class ShellSort {

	public static void shellSort(Integer[] array) {
		int inc = array.length;
		do {
			inc = inc / 3 + 1;
			/*
			 * 从下标i=0开始，将i,i+inc,i+2*inc..视为一个逻辑数组，进行一次插入排序
			 * i=1，将i,i+inc,i+2*inc..视为一个逻辑数组，进行一次插入排序 i++ ...
			 */
			for (int i = 0; i < inc; i++) {
				insertSort(array, i, inc);
			}
		} while (inc > 1);
	}

	/**
	 * @param begin
	 *            希尔排序中，当前逻辑数组的起始下标
	 * @param inc
	 *            希尔排序增量increment
	 */
	public static void insertSort(Integer[] array, int begin, int inc) {
		// 逻辑数组的第2个数，物理下标应该是begin+inc
		for (int i = begin + inc; i < array.length; i += inc) {
			int cur = array[i];
			// 从array[begin]开始比较
			for (int j = begin; j < i; j += inc) { // 在当前逻辑数组中，找到第一个比cur大的数。这里可以用折半查找，并且不要求稳定。因为希尔排序就是一种不稳定排序。
				if (cur < array[j]) {
					// 数组从j到i-1，向后移一位
					backward(array, j, i, inc);
					array[j] = cur;
					break; /**/
				}
			}
		}
	}

	
	/**
	 * 将[begin, end)整体右移
	 * 
	 * ((begin + end) mod inc) == 0
	 * end < array.length - 1
	 */
	private static void backward(Integer[] array, int begin, int end, int inc) {
		for (int i = end; i > begin; i -= inc) {
			array[i] = array[i - inc];
		}
	}

	public static void main(String[] args) {

		Integer[] array = TestUtils.generate(99999, 1);
//		log.info("START SORT: {}", Arrays.toString(array));

		long pf = System.currentTimeMillis();
		shellSort(array);
		pf = System.currentTimeMillis() - pf;
//		log.info("AFTER SORT: {}", Arrays.toString(array));
		log.info("CHECK: {}, RESULT: {}", TestUtils.isAscending(array), pf);
		
//		List<Integer> list = new ArrayList<Integer>();
//		list.add(4);
//		list.add(1);
//		list.add(7);
//		list.add(6);
//		list.add(9);
//		list.add(2);
//		list.add(8);
//		list.add(0);
//		list.add(3);
//		list.add(5);
//		Integer[] array = list.toArray(new Integer[list.size()]);

	}
}
