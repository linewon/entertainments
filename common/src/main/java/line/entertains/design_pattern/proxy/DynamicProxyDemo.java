package line.entertains.design_pattern.proxy;

import java.lang.reflect.Proxy;

import line.entertains.design_pattern.proxy.dynamic.CglibProxy;
import line.entertains.design_pattern.proxy.dynamic.JDKProxy;
import line.entertains.design_pattern.proxy.subject.BuyHouse;
import line.entertains.design_pattern.proxy.subject.BuyHouseImpl;
import line.entertains.design_pattern.proxy.subject.DataOperator;
import line.entertains.design_pattern.proxy.subject.DataOperatorImpl;

/**
 * 测试动态代理类代理不通类型的主题 https://www.cnblogs.com/ygj0930/p/6542259.html
 * 
 * proxy: jdk-proxy, cglib-proxy subject: BuyHouse, DataOperator
 * 
 * @author line
 */
public class DynamicProxyDemo {

	public static void jdkProxyTest() {
		// BuyHouse
		BuyHouse buyHouse = new BuyHouseImpl();
		BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(),
				new Class[] { BuyHouse.class }, new JDKProxy(buyHouse));
		proxyBuyHouse.buyHouse();

		// DataOperator
		DataOperator dataOperator = new DataOperatorImpl();
		DataOperator proxyDataOperator = (DataOperator) Proxy.newProxyInstance(DataOperator.class.getClassLoader(),
				new Class[] { DataOperator.class }, new JDKProxy(dataOperator));
		/*
		 * DataOperator 接口声明了两个方法， 如果使用静态代理的话，在编写的代理类里也需要定义这两个方法，而且不能通用
		 * 如果抽象主题再新增方法的话，每个代理类也需要重新修改代码，维护起来十分复杂
		 */
		proxyDataOperator.queryData();
		proxyDataOperator.updateData();
	}

	public static void cglibProxyTest() {
		// BuyHouse
		BuyHouseImpl buyHouse = new BuyHouseImpl();
		CglibProxy<BuyHouseImpl> cglib = new CglibProxy<BuyHouseImpl>(buyHouse);
		BuyHouse proxyBuyHouse = cglib.getInstance();
		proxyBuyHouse.buyHouse();

		// DataOperator
		DataOperatorImpl dataOperator = new DataOperatorImpl();
		CglibProxy<DataOperatorImpl> cglib2 = new CglibProxy<DataOperatorImpl>(dataOperator);
		
		DataOperatorImpl proxyDataOperator = cglib2.getInstance();
		proxyDataOperator.queryData();
		proxyDataOperator.updateData();
		// 通过cglib进行动态代理，可以调用抽象接口中没有声明的方法！
		proxyDataOperator.otherOperation();
	}

	public static void main(String[] args) {
		jdkProxyTest();
		cglibProxyTest();
	}
}
