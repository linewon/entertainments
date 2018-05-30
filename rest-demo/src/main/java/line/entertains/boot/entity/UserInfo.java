package line.entertains.boot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
	private Integer id;
	private String userName;
	private String nickName;
	private String sex;
	private Byte age;
}
