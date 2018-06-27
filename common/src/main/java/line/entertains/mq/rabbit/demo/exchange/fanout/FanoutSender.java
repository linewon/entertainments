package line.entertains.mq.rabbit.demo.exchange.fanout;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ demo: fanout exchange
 * 
 * exchange "logs" with 3 queues:
 * 		1> qName=log-queue;
 * 		2> qName=log-queue-another;
 * 		3> qName=log-queue-another-another,
 * 
 * the fanout exchange will deliver every MESSAGE it recieved to all the queues binding to.
 * 但相同队列后面跑的multi-program之间，是通过Round-robin规则来分配消息的
 * 
 * @author line
 *
 */
public class FanoutSender {
	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";

	private static final String EXCH_NAME = "logs";
	private static final String MESSAGE = "Hello Rabbit! ";
		
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory cFactory = new ConnectionFactory();
		cFactory.setHost(HOST);
		cFactory.setPort(PORT);
		cFactory.setUsername(USER_NAME);
		cFactory.setPassword(PASSWD);
		
		Connection connection = cFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCH_NAME, "fanout");
		
		StringBuilder msg = new StringBuilder(MESSAGE);
		for (int i = 0; i < 10; i++) {
			
			msg.append('。');
			String curMsg = msg.toString() + i;
			
			channel.basicPublish(EXCH_NAME, "", null, curMsg.getBytes());
			System.out.println("SENDER. send msg : " + curMsg);
		}
		
		channel.close();
		connection.close();
	}
}
