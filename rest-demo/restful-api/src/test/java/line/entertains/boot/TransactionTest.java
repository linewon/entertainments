package line.entertains.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import line.entertains.boot.dao.UserDao;
import line.entertains.boot.entity.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional(rollbackFor = Exception.class)
public class TransactionTest {

	@Autowired
	UserDao userDao;
	
	@Test
	public void testTransaction() {
		
		UserInfo userInfo = userDao.findByUserName("line1993");
		if (userInfo != null) {
			userInfo.setNickName("linewon11");
			userInfo.setAge((byte) 25);
		}
		
		userDao.save(userInfo);
	}
}
