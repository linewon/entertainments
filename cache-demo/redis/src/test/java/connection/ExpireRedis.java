package connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * 过期相关操作
 * 
 * @author line
 * @date 2019年1月28日 下午4:19:43
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class ExpireRedis {

	private static Jedis jedis;
	private static final String PWD = "LineRedis1983..";
	private static final int DB = 4;
	
	@BeforeClass
	public static void init() {
		jedis = new Jedis("192.168.85.131", 6379); // make connection
		log.info("Connect sucessfully"); // otherwise throw an exception
		jedis.auth(PWD); // auth
		log.info("Authorise sucessfully");
		String pong = jedis.ping(); // ping
		log.info("PING : {}\n", pong);
		jedis.select(DB); // select
	}
	
	@Test
	public void aQuery() {
		String key = "myStr";
		jedis.setex(key, 3, "xcvzxcvzxcv");
		String val = jedis.get(key);
		log.info("val: {}", val);
		try {
			Thread.sleep(3000); // waiting for expire
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String val2 = jedis.get(key);
		log.info("val2: {}", val2);
	}
	
	@AfterClass
	public static void close() {
		jedis.quit(); // quit
		jedis.close();
	}
}
