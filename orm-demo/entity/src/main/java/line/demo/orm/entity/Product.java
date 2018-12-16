package line.demo.orm.entity;

import line.demo.orm.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseEntity {
	private Integer id;
	private String name;
	private Integer stock; // 库存，未来做并发实验用
	private String type; // 只是实验用的，不做特别的分类
}
