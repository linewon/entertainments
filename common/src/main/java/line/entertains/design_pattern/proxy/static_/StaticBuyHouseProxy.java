package line.entertains.design_pattern.proxy.static_;

import line.entertains.design_pattern.proxy.subject.BuyHouse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 通过内置一个 BuyHouse 对象,并实现BuyHouse接口中的buyHouse方法，来达到代理的目的。
 * 
 * 每次代理都需要new一个对象，很麻烦。
 * 
 * 实现的接口BuyHouse，是写死的，维护繁琐！
 * 
 * @author line
 */
@Slf4j
@AllArgsConstructor
public class StaticBuyHouseProxy implements BuyHouse {
	
	private BuyHouse buyHouse;

	/**
	 * 聚合式静态代理，
	 * 与之对应的是继承式静态代理
	 */
	@Override
	public void buyHouse() {
		log.info("proxy BEFORE by STATIC-PROXY");
		buyHouse.buyHouse();
		log.info("proxy AFTER by STATIC-PROXY");
	}
}
