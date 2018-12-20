package line.entertains.concur.forkjoin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

import lombok.AllArgsConstructor;

/**
 * print with multi-threads:
 * 			fork-join framework.
 * 
 * @author line
 * @date 2018年12月16日 上午11:47:12
 */
@AllArgsConstructor
public class PrintAction extends RecursiveAction {

	private static final long serialVersionUID = 1L;

	private int start;
	private int end;

	private static final int MAX = 21;

	/*
	 * 逼死强迫症。。。。。
	 * 不过文件本身是没有问题的。
	 */
	@Override
	protected void compute() {
		if ((end - start) <= MAX) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
			StringBuilder sb = new StringBuilder();
			sb.append(Thread.currentThread().getName()).append('\n')
				.append("__start=").append(start).append("__end=").append(end).append('\n')
				.append(date).append('\n');
			System.out.println(sb.toString());
		} else {
			int middle = (start + end) >> 1;
			PrintAction left = new PrintAction(start, middle);
			PrintAction right = new PrintAction(middle + 1, end);
			
			
			/*
			 * A >= D > C >>> B
			 */
			invokeAll(left, right); // A
			
//			left.invoke(); // B
//			right.invoke();
			
//			left.fork(); // C
//			right.fork();
//			left.join();
//			right.join();

//			left.fork(); //D
//			right.fork();
//			right.join();
//			left.join();
		}
	}

	
	/*
	 * test3 > test1 2
	 * https://blog.csdn.net/hotdust/article/details/71480762
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int i = 0;
		final int m = 1;
		while (i < m) {
			
			test1();
//			test2();
//			test3();
			i++;
		}
		long end = System.currentTimeMillis();
		
		System.out.println("TOTAL = " + (end - start) / i / 1000);
	}
	
	public static void test1() {
		PrintAction action = new PrintAction(1, 173);
		action.invoke();
	}
	
	public static void test2() {
		PrintAction action = new PrintAction(1, 1273);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(action);
		pool.shutdown();
	}
	public static void test3() {
		PrintAction action = new PrintAction(1, 173);
		ForkJoinPool pool = new ForkJoinPool();
		Future<Void> f = pool.submit(action);
		try {
			f.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
