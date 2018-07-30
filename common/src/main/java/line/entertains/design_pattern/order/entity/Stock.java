package line.entertains.design_pattern.order.entity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Stock extends Item {

	public Stock(String name, Integer quantity) {
		super(name, quantity);
	}
	
	@Override
	public void buyIn() {
		log.info("{} * {}, buy in", name, quantity);
	}
	
	@Override
	public void sellOut() {
		log.info("{} * {}, sell out", name, quantity);
	}
}
