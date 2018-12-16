package line.demo.orm.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import line.demo.orm.entity.User;
import line.demo.orm.mybatis.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * mybatis 最基本的增删改查操作
 * 后面再研究一下mapper.xml的配置，
 * 最后研究一下mybatis的原理。
 * 
 * @author line
 */
@Slf4j
public class MybatisBaseTest {

	public static SqlSession session = SqlSessionUtil.createSqlSession();

	@Test
	public void testQuery() {

		String sql = "findByUserName";
		String userName = "qq327431213";
		User user = session.selectOne(sql, userName);

		log.info("query user by id successfully : {}", user);
	}

	@Test
	public void testDelete() {
		String sql = "line.demo.orm.mybatis.dao.UserMapper.dropByUserName";
		String userName = "qq327431213";
		session.delete(sql, userName);
		session.commit();
		log.info("delete user: {} successfully!", userName);
	}
	
	@Test
	public void testAdd() {
		String sql = "line.demo.orm.mybatis.dao.UserMapper.addUser";
		User user = new User();
		user.setAge((byte) 99);
		user.setNickName("NICK");
		user.setUserName("qq327431213");
		user.setSex("W");

		session.insert(sql, user);
		session.commit();
		log.info("insert user successfully : {}", user);
	}
	
	@Test
	public void testUpdate() {
		String sql = "line.demo.orm.mybatis.dao.UserMapper.updateUser";
		User user = new User();
		user.setAge((byte) 1);
		user.setNickName("NEW NICK");
		user.setUserName("qq327431213");
		user.setSex("M");
		
		String sqlQuery = "line.demo.orm.mybatis.dao.UserMapper.findByUserName";
		User former = session.selectOne(sqlQuery, user.getUserName());
		log.info("query the former user : {}", former);
		
		
		session.update(sql, user);
		session.commit();
		log.info("update user successfully : {}", user);
	}
}
