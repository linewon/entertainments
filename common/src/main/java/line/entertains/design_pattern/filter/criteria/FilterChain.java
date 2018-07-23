package line.entertains.design_pattern.filter.criteria;

import java.util.Iterator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 这里暂时不关注SpringMVC里的Filter Chain。
 * 那个地方直接
 * before();
 * chain.doFilter(request, response);
 * after();
 * 来实现过滤链中的前后处理，使用起来的确是比较灵活。
 * 
 * 如果在过滤器接口中，声明一个before()方法和after()方法
 * 虽然看起来可能比较符合规矩，但可能实际用起来并不是那么方便。
 * 
 * @author line
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class FilterChain <T>{

	private List<Criteria<T>> chain;
	private List<T> data;
	
	
	public void start() {
		log.info("start filter chain!");
		Iterator<Criteria<T>> itr = chain.iterator();
		while (itr.hasNext()) {
			Criteria<T> criteria = itr.next();
			criteria.meetCriteria(data);
		}
	}
}
