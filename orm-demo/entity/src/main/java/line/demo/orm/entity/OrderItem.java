package line.demo.orm.entity;

import line.demo.orm.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem extends BaseEntity {

	private Integer id;
//	private Order order; // 所属订单
	private Product product; // 对应商品
	private Integer prodCount;
}
