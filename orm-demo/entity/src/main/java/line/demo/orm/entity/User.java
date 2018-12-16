package line.demo.orm.entity;

import line.demo.orm.entity.base.BaseEntity;
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
// 
public class User extends BaseEntity {

//	@Id
//    @GeneratedValue
	private Integer id;
	private String userName;
	private String nickName;
	private String sex;
	private Byte age;
}
