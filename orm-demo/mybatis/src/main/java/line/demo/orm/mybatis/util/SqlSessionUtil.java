package line.demo.orm.mybatis.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SqlSessionUtil {

	private static SqlSessionFactory sessionFactory;
	
	static {
		init();
	}
	
	/**
	 * read the mybatis-*.xml and build the sessionFactory
	 */
	private static void init() {
		String path = "mybatis-config.xml";
		
		try {
			InputStream is = Resources.getResourceAsStream(path);
			sessionFactory = new SqlSessionFactoryBuilder().build(is);
//			Reader reader = Resources.getResourceAsReader(path);
//			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			log.error("Failed to init mybatis.", e);
		}
	}
	
	public static SqlSession createSqlSession() {
		return sessionFactory.openSession();
	}
	public static SqlSession createSqlSession(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit);
	}
}
