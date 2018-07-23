package line.entertains.design_pattern.filter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import line.entertains.design_pattern.filter.criteria.Criteria;
import line.entertains.design_pattern.filter.criteria.FilterChain;
import line.entertains.design_pattern.filter.criteria.impl.PersonNameCriteria;
import line.entertains.design_pattern.filter.criteria.impl.PersonSexCriteria;
import line.entertains.design_pattern.filter.entity.Person;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟springMVC里的过滤链
 * 
 * @author line
 */
@Slf4j
public class FilterPatternDemo {

	/**
	 * 手动执行每个过滤器
	 */
	public static void test1() {
		
		Person p1 = new Person("line", 1, "110"),
				p2 = new Person("won", 0, "110"),
				p3 = new Person("Wong", 1, "110");
		
		List<Person> list = new LinkedList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		Criteria<Person> c1 = new PersonNameCriteria();
		Criteria<Person> c2 = new PersonSexCriteria();

		log.info("begin:");
		for (Person person : list) {
			log.info("{}", person);
		}
		
		list = c1.meetCriteria(list);
		list = c2.meetCriteria(list);
		
		log.info("finally:");
		for (Person person : list) {
			log.info("{}", person);
		}
	}
	
	/**
	 * 初始化每个过滤器，然后加到过滤链中，然后运行过滤链
	 */
	public static void test2() {
		Person p1 = new Person("line", 1, "110"),
				p2 = new Person("won", 0, "110"),
				p3 = new Person("Wong", 1, "110");
		
		List<Person> list = new LinkedList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		Criteria<Person> c1 = new PersonNameCriteria();
		Criteria<Person> c2 = new PersonSexCriteria();
		List<Criteria<Person>> filters = new ArrayList<>();
		filters.add(c1);
		filters.add(c2);
		

		log.info("begin:");
		for (Person person : list) {
			log.info("{}", person);
		}
		new FilterChain<Person>(filters, list).start();

		log.info("finally:");
		for (Person person : list) {
			log.info("{}", person);
		}
	}
	
	public static void main(String[] args) {
		test2();
	}
}