package line.entertains.common.reflect;

import line.entertains.common.entitys.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoClass {
	
	public String function1(String name, Integer age, Boolean gender, String phone) {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(age).append(gender).append(phone);
		return sb.toString();
	}
	
	public Student findByNameAndAge(String name, Integer age) {
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		student.setPhone("111111");
		student.setSex(false);
		return student;
	}
}
