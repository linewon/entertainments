package line.entertains.design_pattern.filter.criteria.impl;

import java.util.List;

import line.entertains.design_pattern.filter.criteria.Criteria;
import line.entertains.design_pattern.filter.entity.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonNameCriteria implements Criteria<Person> {

	@Override
	public List<Person> meetCriteria(List<Person> list) {
		log.info("Filter starts !");
		for (Person person : list) {
			String name = person.getName();
			if ("Wong".equals(name)) {
				list.remove(person);
				log.info("remove 'person': {}", person);
			}
		}
		return list;
	}
}
