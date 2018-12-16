package line.entertains.concur.logger;

import java.text.NumberFormat;

/**
 * 使用线程池+阻塞队列 父类定义一个toString方法，子类去调用，观察在什么地方被调用
 * 重载JSON.toJSONString方法，打日志时调用，观察在什么地方被调用
 * 
 * 结果最后的实验结果，不是按照预想的来了。涉及到的东西有点超过预期了
 * @author line
 */
public class Main {

	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		MyLogger logger = new MyLogger();
		
		String msg = "aaaaa----";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumIntegerDigits(4);
		nf.setMinimumIntegerDigits(4);
		for (int i = 0; i < 100; i++) {
			String logMsg = msg + i;
			logger.record(logMsg);
		}
//		logger.shutDown();
	}
}
