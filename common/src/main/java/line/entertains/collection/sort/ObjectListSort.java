package line.entertains.collection.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObjectListSort {

	private List<Student> list;

	@Before
	public void buildList() {
		list = new ArrayList<>();
		list.add(new Student("line", 18));
		list.add(new Student("aaa", 1));
		list.add(new Student("bbb", 33));
		list.add(new Student("ccc", 22));
		list.add(new Student("ddd", 12));
		list.add(new Student("line", 18));
		list.add(new Student("aaa", 1));
		list.add(new Student("bbb", 33));
		list.add(new Student("ccc", 22));
		list.add(new Student("ddd", 12));
		list.add(new Student("line", 18));
		list.add(new Student("aaa", 1));
		list.add(new Student("bbb", 33));
		list.add(new Student("ccc", 22));
		list.add(new Student("ddd", 12));
		log.info("init student-list: {}", JSON.toJSONString(list));
	}

	@Test
	public void test() {
		Comparator<Student> stuCompare = new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return Integer.compare(s1.getAge(), s2.getAge());
			}
		};
//		Comparator<Student> stuCompare = (Student s1, Student s2) -> s1.getAge().compareTo(s2.getAge());
//		Comparator<Student> stuCompare = (s1, s2) -> s1.getAge().compareTo(s2.getAge());
		
		Collections.sort(list, stuCompare);
		
		log.info("after sort: {}", JSON.toJSONString(list));
	}
}
