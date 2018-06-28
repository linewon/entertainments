package line.entertains.mq.rabbit.demo.exchange.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TopicSender {

	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";

	private static final String EXCH_NAME = "topic-logs";
	private static final String ROUT_RULE = "topic";
	
	private static final String[] ROUTING_KEY_FACI = {"kernel", "auth"};
	private static final String[] ROUTING_KEY_SERV = {"debug", "info", "warn", "error"};
	
	
	private static final String MESSAGE = "Hello Rabbit! ";

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory cFactory = new ConnectionFactory();
		cFactory.setHost(HOST);
		cFactory.setPort(PORT);
		cFactory.setUsername(USER_NAME);
		cFactory.setPassword(PASSWD);

		Connection connection = cFactory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCH_NAME, ROUT_RULE);
		
//		massProduct(channel);
		personalize(channel);

		channel.close();
		connection.close();
	}
	
	private static void massProduct(Channel channel) throws IOException {
		StringBuilder msg = new StringBuilder(MESSAGE);
		for (int i = 0; i < 3; i++) {
			for (String facility : ROUTING_KEY_FACI) {
				for (String severity : ROUTING_KEY_SERV) {
					String routingKey = facility + "." + severity;
					msg.append('。');
					String curMsg = msg.toString() + " --" + routingKey;

					channel.basicPublish(EXCH_NAME, routingKey, null, curMsg.getBytes());
					
					System.out.println("send msg: " + curMsg);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	private static void personalize(Channel channel) throws IOException {
		
		String routingKey = "kernel.error.omg";
		String message = "this is my best wish!";
		
		channel.basicPublish(EXCH_NAME, routingKey, null, message.getBytes());
		
		System.out.println("send msg: " + message + " --routingKey=" + routingKey);
	}
}
