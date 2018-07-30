package line.entertains.design_pattern.order.subject;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class Broker {

	private List<Order> orders;
	
	public void handleOrders() {
		log.info("start to handle orders");
		for (Order order : orders) {
			order.execute();
		}
	}
}
