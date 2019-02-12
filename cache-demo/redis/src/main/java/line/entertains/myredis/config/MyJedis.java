package line.entertains.myredis.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisPool
 * 
 * 
 * @author line
 * @date 2019年1月28日 下午8:42:58
 */
@Component
public class MyJedis {
	
	/** 先这样写着吧，其实应该从配置文件里读 */
	private static final String HOST = "192.168.85.131";
	private static final int PORT = 6379;
	private static final String PWD = "LineRedis1983..";
	private static final int TIME_OUT = 2000; 

	private JedisPool pool;
	
	@PostConstruct
	public void initJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		pool = new JedisPool(config, HOST, PORT, TIME_OUT, PWD);
	}

	/**
	 * 方便取
	 */
	public Jedis getJedis() {
		return pool.getResource();
	}
}
