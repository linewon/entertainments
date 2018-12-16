package line.demo.orm.entity;

import java.util.Date;
import java.util.List;

import line.demo.orm.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends BaseEntity {

	private Integer id;
	private User user; // 订单所属用户
	private List<OrderItem> itemList; // 商品列表
	private Date create; // 创建日期
	private Integer status; // 订单状态
	private Date expire; // 支付过期时间
}
