package line.entertains.design_pattern.proxy.subject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BuyHouseImpl implements BuyHouse {

	@Override
	public void buyHouse() {
		log.info("----- buy a house -----");
	}

}
