package line.entertains.concur.atomic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicIntegerArray;

import com.google.common.base.Joiner;

/**
 * 
 * @author line
 * @date 2019年1月2日 下午2:54:06
 */
public class AtomicIntegerArrayDemo implements Runnable {

	private AtomicIntegerArray intArray = new AtomicIntegerArray(new int[]{50});
	
	@Override
	public void run() {
		Boolean bool = ThreadLocalRandom.current().nextBoolean();
		int i;
		if (bool) { // read
			i = read(0);
		} else { // write
			i = write(0);
		}
		System.out.println(Joiner.on(',').join(Thread.currentThread().getName(), bool, i));
	}
	
	private int read(int idx) {
		return intArray.get(idx);
	}
	private int write(int idx) {
		int i = intArray.get(idx);
		if (i > 0) {
			return intArray.decrementAndGet(idx);
		}
		return i;
	}
	public static void main(String[] args) {
		final int NUM_T = 100;
		AtomicIntegerArrayDemo demo = new AtomicIntegerArrayDemo();
		for (int i = 0; i < NUM_T; i++) {
			new Thread(demo).start();
		}
	}
}
