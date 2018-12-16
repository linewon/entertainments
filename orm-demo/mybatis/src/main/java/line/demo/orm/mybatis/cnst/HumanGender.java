package line.demo.orm.mybatis.cnst;

import lombok.Getter;

public enum HumanGender {
	Male("M"), Female("W");

	@Getter
	private String sex;

	private HumanGender(String sex) {
		this.sex = sex;
	}
}
