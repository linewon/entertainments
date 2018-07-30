package line.entertains.design_pattern.order;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import line.entertains.design_pattern.order.entity.Stock;
import line.entertains.design_pattern.order.subject.Broker;
import line.entertains.design_pattern.order.subject.Order;
import line.entertains.design_pattern.order.subject.StockBuyOrder;
import line.entertains.design_pattern.order.subject.StockSellOrder;
import line.entertains.design_pattern.proxy.dynamic.JDKProxy;

public class OrderPatternDemo {

	public static void main(String[] args) {

		Stock stock = new Stock("lien", 20 * 1000);
		Order orderBuy = new StockBuyOrder(stock);
		Order orderSell = new StockSellOrder(stock);
		
		/*
		 * 代理模式加些日志
		 */
		Order proxyBuy = (Order) Proxy.newProxyInstance(Order.class.getClassLoader(), new Class[] {Order.class}, new JDKProxy(orderBuy));
		Order proxySell = (Order) Proxy.newProxyInstance(Order.class.getClassLoader(), new Class[] {Order.class}, new JDKProxy(orderSell));

		List<Order> orderList = new ArrayList<>();
		orderList.add(proxyBuy);
		orderList.add(proxySell);
		
		Broker broker = new Broker(orderList);
		broker.handleOrders();
	}
}
