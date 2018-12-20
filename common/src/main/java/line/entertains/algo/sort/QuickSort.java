package line.entertains.algo.sort;

import line.entertains.algo.sort.base.LineSort;
import line.entertains.algo.sort.base.TestUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序算法：
 * 左边的数都比pivot小，右边的数都比pivot大
 * 
 * @author line
 * @date 2018年12月18日 下午3:17:20
 */
@Slf4j
public class QuickSort implements LineSort<Integer> {

	/**
	 * 
	 */
	@Override
	public void sort(Integer[] array) {
		qsort(array, 0, array.length - 1);
	}

	private void qsort(Integer[] array, int lo, int hi) {
		int pivot = partition(array, lo, hi); // 获取中轴的下标
		if (lo < pivot - 1)
			qsort(array, lo, pivot - 1); // 对左边进行一次partition
		if (pivot < hi - 1)
			qsort(array, pivot + 1, hi); // 对右边进行一次partition
	}

	/*
	 * 一次快速排序：
	 * 1. 选取第一个元素作为key，顺便将其存储起来
	 * 2. 从右往左，hi--，找到第一个比key小的数array[hi]，将其放到array[lo]（由于选择最左边的元素作为key，因此第一趟是从右往左。反之亦然）
	 * 3. 从左往右，lo++，找到第一个比key大的数array[lo]，将其放到array[hi]
	 * 4. 循环3.4. 直至lo >= hi
	 * 5. 将key放到array[lo]
	 * 6. 返回pivot下标
	 * 
	 * 一次左填坑、一次右填坑。
	 */
	private int partition(Integer[] array, int lo, int hi) {
		// 将array[lo]用作pivot比较，同事暂存起来。
		Integer key = array[lo]; // 取数组第一个数作为中轴值
//		log.info("lo={}, hi={}, key={}", lo, hi, key);
//		log.info("ori-array: {}", Arrays.toString(array));
		
		/*
		 * 记此时的hi值为H
		 * 进行<b>一次比对</b>，将最右侧的比key小的数放到lo，再把最左侧比key大的数放到hi
		 */
		while (lo < hi) { 
			while (lo < hi && array[hi] > key) { // 从右侧开始hi--，找到第一个比key小的数
				hi--;
			}
			if (lo < hi) { // 如果此时lo>hi，则说明[lo,H]这段数内的值都比key大，则直接把key放到当前的lo位置
//				log.info("array[{}] = array[{}]", lo, hi);
				array[lo++] = array[hi];
				// 记此时的lo值为L
				while (lo < hi && array[lo] < key) { // 从左侧开始lo++，找到第一个比key大的数
					lo++;
				}
				if (lo < hi) { // 若此时lo>hi，则说明[L,hi]这段数内的值都比key小，则直接把key值放到lo位置
//					log.info("array[{}] = array[{}]", hi, lo);
					array[hi] = array[lo];
				}
			}
		}
		array[lo] = key; ///// 这里为什么选择lo还有待考究！！！！！！
//		log.info("sort-array: {}", Arrays.toString(array));
//		log.info("pivot={}\n", lo);
		return lo; ///// 这里为什么选择lo还有待考究！！！！！！
	}

	public static void main(String[] args) {
//		List<Integer> list = new ArrayList<>();
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

		Integer[] array = TestUtils.generate(9999999, 2);
//		log.info("START SORT: {}", Arrays.toString(array));
		long pf = System.currentTimeMillis();
		new QuickSort().sort(array);
		pf = System.currentTimeMillis() - pf;
//		log.info("AFTER SORT: {}", Arrays.toString(array));
		log.info("CHECK: {}, RESULT: {}", TestUtils.isAscending(array), pf);
	}
}
