package line.entertains.myredis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import line.entertains.myredis.entity.UserIntro;

/**
 * 
 * 
 * @author line
 * @date 2019年1月28日 下午9:02:37
 */
public interface UserIntroDao extends JpaRepository<UserIntro, Integer> {

	UserIntro findByUserName(String userName);
}
