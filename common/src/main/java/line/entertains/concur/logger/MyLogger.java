package line.entertains.concur.logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 日志组件管理模块
 * 
 * @author line
 *
 */
public class MyLogger {

	/**
	 * 日志消息阻塞队列
	 */
	private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	/**
	 * 日志消费者线程池
	 */
	private ExecutorService executors = Executors.newFixedThreadPool(POOL_SIZE);

	private static final int POOL_SIZE = 1;
	
	/**
	 * 现在，是3个线程在while监听。 可不可以搞一个主线程，去监听queue，有动静了之后去调用工作线程进行日志操作。
	 */
	public MyLogger() {
		for (int i = 0; i < POOL_SIZE; i++) {
			executors.submit(new Handler(queue));
		}
	}

	public void record(String msg) {

		try {
			queue.put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个地方应该怎么样去关闭啊！！
	 */
	public void shutDown() {
		executors.shutdown();
	}
}
