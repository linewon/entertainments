package line.entertains.concur.forkjoin;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import line.entertains.algo.sort.ShellSort;
import line.entertains.algo.sort.base.TestUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Shell sort based on fork-join framework.
 * 
 * @author line
 * @date 2018年12月16日 上午11:47:12
 */
@AllArgsConstructor
@Slf4j
public class ConcurrentShellSort implements Runnable {

	// 内部线程池
	private static ExecutorService pool = Executors.newFixedThreadPool(10);

	private Integer[] array;
	private int begin;
	
	/** increment */
	private int inc;
	
	private CountDownLatch latch;

	public ConcurrentShellSort(Integer[] array) {
		this.array = array;
		this.begin = 0;
		this.inc = array.length / 3 + 1;
	}

	public void sort() {
		while (inc > 1) { // inc >= 2的时候，利用多线程，对不同的逻辑数组同时操作。
			latch = new CountDownLatch(inc);
			
			for (int i = 0; i < inc; i++) {
				// 性能瓶颈应该在这个地方。
				pool.submit(new ConcurrentShellSort(array, i, inc, latch));
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			inc = inc / 3 + 1;
		}
		insertSort(array, 0, 1);
        pool.shutdown();
	}

	/**
	 * @param begin 希尔排序中，当前逻辑数组的起始下标
	 * @param inc 希尔排序增量increment
	 */
	public void insertSort(Integer[] array, int begin, int inc) {
//		log.info("inserSort.begin array: {}, begin: {}, inc: {}", Arrays.toString(array), begin, inc);
		// 逻辑数组的第2个数，物理下标应该是begin+inc
		for (int i = begin + inc; i < array.length; i += inc) {
			int cur = array[i];
			// 从array[begin]开始比较
			for (int j = begin; j < i; j += inc) { // 在当前逻辑数组中，找到第一个比cur大的数。这里可以用折半查找
				if (cur < array[j]) {
					// 数组从j到i-1，向后移一位
					backward(array, j, i, inc);
					array[j] = cur;
					break; /**/
				}
			}
		}
//		log.info("inserSort.finish array: {}, begin: {}, inc: {}", Arrays.toString(array), begin, inc);
	}

	/**
	 * array[begin, end)向后移
	 */
	private void backward(Integer[] array, int begin, int end, int inc) {
		for (int i = end; i > begin; i -= inc) {
			array[i] = array[i - inc];
		}
	}

	/**
	 * 对传入的array数组，
	 * 进行一次起始下标为begin，
	 * 增量为inc，
	 * 的插入排序。
	 */
	@Override
	public void run() {
		insertSort(array, begin, inc);
		latch.countDown();
	}

	public static void main(String[] args) {
		Integer[] array = TestUtils.generate(999999, 2);
//		log.info("START SORT: {}", Arrays.toString(array));

		long pf = System.currentTimeMillis();
		new ConcurrentShellSort(array).sort();
		pf = System.currentTimeMillis() - pf;
		
//		log.info("AFTER SORT: {}", Arrays.toString(array));
		log.info("CHECK: {}, RESULT: {}", TestUtils.isAscending(array), pf);
		
		
//		log.info("START SORT: {}", Arrays.toString(array));
		array = TestUtils.generate(999999, 2);
		pf = System.currentTimeMillis();
		ShellSort.shellSort(array);
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
