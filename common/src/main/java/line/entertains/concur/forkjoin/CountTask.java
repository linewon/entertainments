package line.entertains.concur.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * i = start, j = end;
 * comput i + ... + j
 * https://www.cnblogs.com/dongguacai/p/6021859.html
 * 
 * @author line
 * @date 2018年12月16日 上午11:00:38
 */
public class CountTask extends RecursiveTask<Integer> {

	private static final long serialVersionUID = 1L;

	private int start;
	private int end;

	private static final int THRESHOLD = 2;

	@Override
	protected Integer compute() {
		int sum = 0;

		if ((end - start) < THRESHOLD) {
			sum = ((start + end) * (end - start + 1)) >> 1;
			System.out.println(Thread.currentThread().getName());
		} else {
			int middle = (start + end) >> 1;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);

			// https://www.liaoxuefeng.com/article/001493522711597674607c7f4f346628a76145477e2ff82000
			//			leftTask.fork();  
			//			rightTask.fork();
			invokeAll(leftTask, rightTask);

			int leftRslt = leftTask.join();
			int rightRslt = rightTask.join();

			// should = or += be used?
			// it's same because init-sum == 0
			sum = leftRslt + rightRslt;
		}

		return sum;
	}

	/**
	 * 
	 */
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public static void main(String[] args) {
		CountTask task = new CountTask(1, 6);
		System.out.println(task.invoke());
//		ForkJoinPool pool = new ForkJoinPool();
//		pool.invoke(task);
//		Future<Integer> rsltFuture = pool.submit(task);
//
//		try {
//			System.out.println(rsltFuture.get());
//		} catch (InterruptedException | ExecutionException e) {
//			e.printStackTrace();
//		}
	}
}
