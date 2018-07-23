package line.entertains.design_pattern.filter.criteria.impl;

import java.util.List;

import line.entertains.design_pattern.filter.criteria.Criteria;
import line.entertains.design_pattern.filter.entity.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonSexCriteria implements Criteria<Person> {

	@Override
	public List<Person> meetCriteria(List<Person> list) {
		log.info("Filter starts !");
		for (Person p : list) {
			Integer sex = p.getSex();
			if (sex != null && sex == 0) {
				list.remove(p);
				log.info("remove 'person': {}", p);
			}
		}
		return list;
	}
}
