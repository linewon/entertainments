package line.entertains.boot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 如果表中 primary key 是id，
 * 则每次新增一个对象都会直接插入。
 * 
 * @author line
 */
@Getter
@Setter
@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
    @GeneratedValue
	private Integer id;
	private String userName;
	private String nickName;
	private String sex;
	private Byte age;
}
