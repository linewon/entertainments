package line.entertains.common.entitys;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Student {

	private String name;
	private Integer age;
	private Boolean sex;
	private String phone;
	
	public void sleep(Integer time) {
		log.info("student '{}' has sleep for '{}' hours", this.name, time);
	}
}
