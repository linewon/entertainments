package line.entertains.collection.list.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author line
 * @date 2019年2月1日 下午3:24:36
 */
public class ArrayListDemo {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add((i << 1) + 1);
		}
		for (Integer i : list) {
			System.out.println(i);
		}

		list.remove(Integer.valueOf(3)); // remove element 3
		list.remove(3); // remove index 3

		for (Integer i : list) {
			System.out.println(i);
		}
	}
}
