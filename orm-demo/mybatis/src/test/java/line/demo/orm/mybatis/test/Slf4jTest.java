package line.demo.orm.mybatis.test;

import line.demo.orm.entity.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jTest {

	public static void main(String[] args) {
		User user = new User();
		
		log.info(user.toString());
	}
}
