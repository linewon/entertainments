package line.demo.orm.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import line.demo.orm.entity.User;
import line.demo.orm.mybatis.dao.UserMapper;
import line.demo.orm.mybatis.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MybatisInterfTest {

	private SqlSession session = SqlSessionUtil.createSqlSession(true);
	
	@Test
	public void query() {
		UserMapper userInfoMapper = session.getMapper(UserMapper.class);
		String userName = "line1983";
		User user = userInfoMapper.findByUserName(userName);
		log.info("find user : {}", user);
	}
}
