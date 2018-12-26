package line.entertains.concur.lock;

/**
 * 
 * 
 * @author line
 * @date 2018年12月26日 下午2:31:43
 */
public class Counter {

	private int counter = 0;

	public void increase() {
		counter++;
	}

	@Override
	public String toString() {
		return String.valueOf(counter);
	}
}
