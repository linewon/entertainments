package line.entertains.concur;

/**
 * 
 * 
 * @author line
 * @date 2018年12月27日 下午5:02:06
 */
public class SleepUtils {

	public static void sleep(int msec) {
		try {
			Thread.sleep(msec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
