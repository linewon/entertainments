package connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * https://www.cnblogs.com/benwu/articles/8616141.html
 * https://blog.csdn.net/10km/article/details/77852075
 * 
 * 通过JedisPool，生成Jedis客户端，并操作Redis
 * 
 * @author line
 * @date 2019年1月28日 下午8:12:03
 */
@Slf4j
public class PooledRedis {

	private static JedisPool jedisPool;
	
	private static Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	@BeforeClass
	public static void init() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		jedisPool = new JedisPool(poolConfig, "192.168.85.131", 6379, 2000, "LineRedis1983..");
	}
	
	@Test
	public void aPing() {
		Jedis jedis = getJedis();
		String pong = jedis.ping();
		log.info("ping : {}", pong);
		jedis.close();
	}
	
	@AfterClass
	public static void close() {
		jedisPool.close();
	}
}
