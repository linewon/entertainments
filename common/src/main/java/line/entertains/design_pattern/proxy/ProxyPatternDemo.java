package line.entertains.design_pattern.proxy;

import java.lang.reflect.Proxy;

import line.entertains.design_pattern.proxy.dynamic.CglibProxy;
import line.entertains.design_pattern.proxy.dynamic.JDKProxy;
import line.entertains.design_pattern.proxy.static_.StaticBuyHouseProxy;
import line.entertains.design_pattern.proxy.subject.BuyHouse;
import line.entertains.design_pattern.proxy.subject.BuyHouseImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * https://www.cnblogs.com/daniels/p/8242592.html
 * https://blog.csdn.net/goskalrie/article/details/52458773
 * 
 * @author line
 *
 */
@Slf4j
public class ProxyPatternDemo {

	public static void staticProxyTest() {
		log.info("test for STATIC proxy");
		BuyHouse buyHouse = new BuyHouseImpl();
		BuyHouse statiProxyBuyHouse = new StaticBuyHouseProxy(buyHouse);

		statiProxyBuyHouse.buyHouse();
	}

	public static void jdkProxyTest() {
		log.info("test for JDK proxy");
		BuyHouse buyHouse = new BuyHouseImpl();
		BuyHouse proxy = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(),
				new Class[] { BuyHouse.class }, new JDKProxy(buyHouse));

		proxy.buyHouse();
	}

	public static void cglibProxyTest() {
		log.info("test for CGLIB proxy");
		BuyHouse buyHouse = new BuyHouseImpl();
		CglibProxy<BuyHouse> cglib = new CglibProxy<BuyHouse>(buyHouse);
		BuyHouse proxy = cglib.getInstance();
		proxy.buyHouse();
	}

	public static void main(String[] args) {
		staticProxyTest();
		jdkProxyTest();
		cglibProxyTest();
	}
}
