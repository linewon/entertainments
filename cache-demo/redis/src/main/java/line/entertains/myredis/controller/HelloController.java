package line.entertains.myredis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import line.entertains.myredis.config.MyJedis;
import line.entertains.myredis.dao.UserIntroDao;
import line.entertains.myredis.entity.UserIntro;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * 
 * 
 * @author line
 * @date 2019年1月28日 下午8:49:47
 */
@Slf4j
@RestController
@RequestMapping(value = "/myRedis")	
public class HelloController {
	
	@Autowired		
	MyJedis myJedis;
	@Autowired
	UserIntroDao userIntroDao;

	/**
	 * 根据userName返回相应的userIntro
	 * 先从redis里找，
	 * 没有就去数据库里找，
	 * 并缓存到redis
	 */
	@GetMapping("/userName/{userName}")
	public String userIntro(@PathVariable("userName") String userName) {
		Jedis jedis = myJedis.getJedis();
		log.info("userName: {}", userName);
		String intro = jedis.get(userName);
		log.info("get from redis: {}", intro);
		if (intro == null || !StringUtils.hasText(intro)) { // 缓存里没有
			UserIntro userIntro = userIntroDao.findByUserName(userName);
			intro = userIntro.getUserIntro();
			log.info("get from mysql: {}", intro);
			
			jedis.setnx(userName, intro);
			log.info("set into redis: {}-{}", userName, intro);
		}
		if (intro == null || !StringUtils.hasText(intro)) { // 还是没有！
			intro = "NOT FOUND!";
		}
		jedis.close();
		return intro;
	}
}
