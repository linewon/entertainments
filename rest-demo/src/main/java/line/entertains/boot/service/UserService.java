package line.entertains.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import line.entertains.boot.dao.UserDao;
import line.entertains.boot.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;


/**
 * @Transactional注解，默认只对RuntimeException（checked）有效，对Exception（unchecked）无效
 * 
 * 
 * @author line
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class UserService {
	
	@Autowired
	UserDao userDao;

	public String modifyUserInfo(UserInfo userInfo) throws Exception {
		 String userName = userInfo.getUserName();
		 
		 UserInfo old = userDao.findByUserName(userName);
		 old.setNickName(userInfo.getNickName());
		 
		 /*
		  * 这个getOne好像查不到东西。。。
		  * 
		  * 我次奥，debug的时候变量里看不到，日志打出来有东西。。
		  */
		 UserInfo another = userDao.getOne(new Integer(11));
		 log.info("another by getOne(ID) : {}", JSON.toJSONString(another));
		 
		 userDao.save(old);
		 
		 boolean tag = false;
		 if (tag)
			 throw new Exception();
		 
		 return "0000";
	}
}
