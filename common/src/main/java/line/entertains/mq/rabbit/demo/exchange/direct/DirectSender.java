package line.entertains.mq.rabbit.demo.exchange.direct;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ demo: direct exchange
 * 
 * exchange "direct-routing" with 3 queues:
 * 		1> qName=queue-direct-divided3, rKey=divided3;
 * 		2> qName=queue-direct-else, rKey=else;
 * 		3> qName=queue-direct-both, rKey=divided3 & else.
 * 
 * @author line
 *
 */
public class DirectSender {

	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";

	private static final String EXCH_NAME = "direct-routing";
	private static final String MESSAGE = "Hello Rabbit! ";

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory cFactory = new ConnectionFactory();
		cFactory.setHost(HOST);
		cFactory.setPort(PORT);
		cFactory.setUsername(USER_NAME);
		cFactory.setPassword(PASSWD);

		Connection connection = cFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCH_NAME, "direct");

		String routing1 = "divided3";
		String routing2 = "else";
		StringBuilder msg = new StringBuilder(MESSAGE);
		for (int i = 0; i < 10; i++) {

			msg.append('。');
			String curMsg = msg.toString() + i;
			
			String routingKey = null;
			if (i % 3 == 0)
				routingKey = routing1;
			else
				routingKey = routing2;
			
			channel.basicPublish(EXCH_NAME, routingKey, null, curMsg.getBytes());
			System.out.print("SENDER. send msg : " + curMsg);
			System.out.println(" --routing-key: " + routingKey);
		}

		channel.close();
		connection.close();
	}
}
