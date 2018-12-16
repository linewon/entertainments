package line.demo.orm.entity.test;

import line.demo.orm.entity.User;

public class BaseEntityTest {

	public static void main(String[] args) {
		User userInfo = new User();
		userInfo.setAge((byte)18);
		userInfo.setNickName("line");
		
		System.out.println(userInfo);
	}
}
