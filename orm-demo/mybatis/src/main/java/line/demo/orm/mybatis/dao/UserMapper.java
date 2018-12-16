package line.demo.orm.mybatis.dao;

import line.demo.orm.entity.User;

public interface UserMapper {

	User findByUserName(String userName);
	void dropByUserName(String userName);
	void updateUser(User userInfo);
	void addUser(User userInfo);
	
}
