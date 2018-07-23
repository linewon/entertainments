package line.entertains.design_pattern.filter.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

	private String name;
	/**
	 * 0: 保密
	 * 1: 男
	 * 2: 女
	 */
	private Integer sex;
	private String phone;
}
