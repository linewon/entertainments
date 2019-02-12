package line.entertains.concur.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Joiner;

/**
 * 
 * 
 * @author line
 * @date 2019年1月2日 下午6:21:10
 */
public class ConcurHashMapDemo {

	public static final int NUM_T = 100;
	
	public static void main(String[] args) {
		basic();
//		bad();
	}
	/**
		Exception in thread "main" java.util.ConcurrentModificationException
			at java.util.HashMap.forEach(HashMap.java:1292)
			at line.entertains.concur.collection.ConcurHashMapDemo.bad(ConcurHashMapDemo.java:33)
			at line.entertains.concur.collection.ConcurHashMapDemo.main(ConcurHashMapDemo.java:20)
	 */
	public static void bad() {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < NUM_T; i++) {
			Runnable runnable = () -> {
				String key = Thread.currentThread().getName();
				
				map.put(key, key);
			};
			new Thread(runnable).start();
		}
		map.forEach((k, v) -> System.out.println(Joiner.on(" - ").join(k, v)));
	}

	public static void basic() {
		Map<String, String> map = new ConcurrentHashMap<>();
		for (int i = 0; i < NUM_T; i++) {
			if (i == NUM_T - 1) {
				System.out.println("pause");
			}
			Runnable runnable = () -> {
				String key = Thread.currentThread().getName();
				
				map.put(key, key);
			};
			new Thread(runnable).start();
		}
		map.size(); // conterCells <- Striped64
		((ConcurrentHashMap)map).mappingCount();
		map.forEach((k, v) -> System.out.println(Joiner.on(" - ").join(k, v)));
	}
}
