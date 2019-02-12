package connection;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * http://www.redis.net.cn/order/
 * 
 * 测试redis的连接
 * 其他数据类型的相关操作，在别的案例中测试。
 * String, List, HMap, Set, ZSet(Sorted Set)
 * 
 * @author line
 * @date 2019年1月28日 下午3:08:14
 */
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HelloRedis {

	private static Jedis jedis;
	private static final String PWD = "LineRedis1983..";
	private static final int DB = 3;

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
	public void aCommon() {
		Set<String> allKeys = jedis.keys("*");
		log.info("keys: {}", JSON.toJSONString(allKeys));
		String keyName = "myString";
		boolean exists = jedis.exists(keyName);
		log.info("exists: {}", exists);
		String keyType = jedis.type(keyName);
		log.info("type: {}", keyType);
	}
	
	/**
	 * String类型变量相关操作
	 */
	@Test
	public void bString() {
		String key = "myString";
		/*
		 * setnx: set if not exist;
		 * setex: set with expire time (second);
		 * psetex: with exp time (mili-second).
		 */
		long statCode = jedis.setnx(key, "Hello Redis!");
		log.info("statCode: {}", statCode); // 1(0) if the key was( not) set
		String myString = jedis.get(key);
		log.info("myString: {}", myString);
		long len = jedis.strlen(key);
		log.info("length: {}", len);
		jedis.append(key, "FFFFFFFFFFFFk");
		String apdStr = jedis.get(key);
		log.info("append: {}", apdStr);
	}
	
	/**
	 * List
	 */
	@Test
	public void cList() {
		String key = "myList"; // the key of redis is CASE-SENSITIVE. it's my mistake of setting the wrong key!
		
		List<String> myList = jedis.lrange(key, 0, 99);
		log.info("class: {}", myList.getClass().getName());
		log.info("myList: {}", JSON.toJSONString(myList));
	}
	
	/**
	 * HMap
	 */
	@Test
	public void dHMap() {
		String key = "myhashmap";
		Map<String, String> myHashMap = jedis.hgetAll(key);
		log.info("class: {}", myHashMap.getClass().getName());
		log.info("myHashMap: {}", JSON.toJSONString(myHashMap));
	}
	
	/**
	 * Set
	 */
	@Test
	public void eSet() {
		String key = "myset-for-mq";
		Set<String> mySet = jedis.smembers(key);
		log.info("class: {}", mySet.getClass().getName());
		log.info("mySet: {}", JSON.toJSONString(mySet));
	}
	
	/**
	 * ZSet(Sorted Set)
	 */
	@Test
	public void fZSet() {
		String key = "my_sorted_set";
		Set<String> mySortedSet = jedis.zrange(key, 0, -1); // 显示整个有序集成员
		log.info("class: {}", mySortedSet.getClass().getName());
		log.info("mySortedSet by zrange: {}", JSON.toJSONString(mySortedSet));
		
		mySortedSet = jedis.zrangeByScore(key, "-inf", "+inf"); // 显示整个有序集成员
		log.info("class: {}", mySortedSet.getClass().getName());
		log.info("mySortedSet by zrange: {}", JSON.toJSONString(mySortedSet));
	}
	
	@AfterClass
	public static void close() {
		jedis.quit(); // quit
		jedis.close();
	}
}
