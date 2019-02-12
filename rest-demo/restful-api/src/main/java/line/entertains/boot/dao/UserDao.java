package line.entertains.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import line.entertains.boot.entity.UserInfo;

public interface UserDao extends JpaRepository<UserInfo, Integer>{

	public UserInfo findByUserName(String userName);
}
