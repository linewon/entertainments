package line.entertains.boot;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import line.entertains.boot.dao.UserDao;
import line.entertains.boot.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class JpaTest {

	@Autowired
	UserDao userDao;
	
	@Test
	public void testBaseDao() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("line1983");
		userInfo.setNickName("LINEWON");
		userInfo.setSex("M");
		userInfo.setAge((byte) 18);
		log.info("NEW. userInfo: {}", JSON.toJSONString(userInfo));
		/*
		 * id字段是在save完成之后才更新的, 而不是在构造方法中赋值！
		 * 原因也很简单，因为id是自增的 ，需要知道表中当前的id，要经过数据库
		 */
		userDao.save(userInfo); 
		log.info("INSERT. userInfo: {}", JSON.toJSONString(userInfo));
		
		UserInfo thisUser = userDao.findOne(userInfo.getId());
		log.info("QUERY. userInfo: {}", JSON.toJSONString(thisUser));
		
		List<UserInfo> usrInfoList = userDao.findAll();
		log.info("QUERY. userInfoList: {}", JSON.toJSONString(usrInfoList));

		log.info("QUERY. count: {}", userDao.count());
	}
	
//	@Test
	public void testDeleteList() {
		
		List<UserInfo> userInfoList = userDao.findAll();
		log.info("before DELETE: {}", JSON.toJSONString(userInfoList));
		userDao.delete(userInfoList);
		log.info("after DELETE: {}", JSON.toJSONString(userInfoList));
	}
}
