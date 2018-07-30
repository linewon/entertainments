package line.entertains.design_pattern.order.subject;

import line.entertains.design_pattern.order.entity.Stock;
import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
//@Slf4j
public class StockBuyOrder implements Order {

	private Stock stock;
	
	@Override
	public void execute() {
//		log.info("order. try to execuse stock : {}", stock.getName());
		stock.buyIn();
	}
}
