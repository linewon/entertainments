package line.demo.orm.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import line.demo.orm.entity.Order;
import line.demo.orm.mybatis.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComplexTest {

	SqlSession session = SqlSessionUtil.createSqlSession(true);
	
	@Test
	public void init() {
		
		String statement = "line.demo.orm.mybatis.dao.OrderMapper.findById";
		Integer id = 1;
		Order order = session.selectOne(statement, id);
		
		log.info("query order: {}", order);
	}
}
