package line.entertains.myredis.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * @author line
 * @date 2019年1月28日 下午9:02:46
 */
@Getter
@Setter
@Entity
@Table(name = "user_intro")
public class UserIntro {

	@Id
	private String userName;
	private String userIntro;
}
