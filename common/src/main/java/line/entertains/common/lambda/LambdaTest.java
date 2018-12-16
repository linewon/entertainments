package line.entertains.common.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * https://www.cnblogs.com/franson-2016/p/5593080.html
 * https://blog.csdn.net/qq_26718271/article/details/70257049
 * 
 * @author line
 */
public class LambdaTest {

	/**
	 * for-each for list
	 */
	@Test
	public void list() {
		List<String> list = new ArrayList<>();
		list.add("asdf");
		list.add("123123");
		list.add("xxxxx");

		list.forEach((string) -> {
			System.out.println(string);
		});

		list.forEach((string) -> System.out.println(string));

	}

	/**
	 * for-each for map
	 */
	@Test
	public void map() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "asdf");
		map.put("2", "xxx");
		map.put("3", "11231231");

		map.forEach((k, v) -> {
			System.out.println(k + " -- " + v);
		});
		map.forEach((k, v) -> System.out.println(k + " -- " + v));
	}

	/**
	 * 实现匿名类
	 */
	@Test
	public void functional() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(12);
		list.add(26);

		// Collections.sort(list, (Integer x, Integer y) -> Integer.compare(x,y)); //
		// e.g.1
		Comparator<Integer> comp = (x, y) -> Integer.compare(x, y); // e.g.2
		Collections.sort(list, comp);

		list.forEach((n) -> System.out.println(n));
	}

	/**
	 * 函数式编程
	 */
	@Test
	public void functional2() {
		// 实现一个加法
		MathOperation add = (a, b) -> (a + b); // 单行语句不需要{}和return
		System.out.println(add.operation(1, 2));

		// 实现一个乘法
		MathOperation multi = (int a, int b) -> {
			a *= 10;
			b *= 10;
			return a * b; // 多行语句需要{}和return
		};
		System.out.println(multi.operation(3, 4));
	}

	/**
	 * 自定义的一个算术接口
	 * 各种实现类实现不同的算术方法
	 */
	public interface MathOperation {
		int operation(int a, int b);
	}
}
