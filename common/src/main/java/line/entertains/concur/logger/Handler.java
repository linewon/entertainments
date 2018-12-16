package line.entertains.concur.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * 日志组件核心模块
 * 
 * @author line
 */
public class Handler implements Runnable {

	/**
	 * log-msg queue
	 */
	private BlockingQueue<String> queue = null;
	/**
	 * datetime
	 */
	private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static final String SPLIT = "=====";
	
	private volatile boolean stop = false;

	public Handler(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (!stop) {
			String msg = null;
			try {
				// take msg from the queue
				msg = queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			printMsg(msg);
		}
	}

	private static synchronized void printMsg(String msg) {

		StringBuilder sb = new StringBuilder();
		sb.append(Thread.currentThread().getName()).append(SPLIT);
		sb.append(DF.format(new Date())).append(SPLIT);
		sb.append(msg);

		System.out.println(sb.toString());
	}
	
	public void stop() {
		this.stop = true;
	}
}
