package line.entertains.design_pattern.filter.criteria;

import java.util.List;

public interface Criteria <T> {

	List<T> meetCriteria(List<T> list);
}
