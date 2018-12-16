package line.entertains.collection.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class SimpleListSort {

	private static StringBuilder sb = new StringBuilder();

	
	@Test
	public void arrayListSort() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		list.add(7);
		list.add(4);
		list.add(32);
		list.add(26);
		list.add(5);
		list.add(23);
		list.add(11);
		list.add(61);
		list.add(36);
		list.add(77);
		list.add(15);
		list.add(42);

		printList(list);
		
		Collections.reverse(list);
		printList(list);
		
		Collections.sort(list);
		printList(list);
	}
	
	
	private void printList(List<Integer> list) {

		list.forEach((num) -> {
			sb.append(num).append(", ");
		});

		System.out.println(sb.substring(0, sb.length() - 2));
	}
}
