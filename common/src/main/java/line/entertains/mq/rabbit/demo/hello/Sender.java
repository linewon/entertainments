package line.entertains.mq.rabbit.demo.hello;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Hello! RabbitMQ
 * 
 * @author line
 *
 */
public class Sender {

	private static final String QUEUE_NAME = "hello";
	private static final String MESSAGE = "Hello Rabbit!";
	
	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";
		
	public static void main(String[] args) throws IOException, TimeoutException {
		
		ConnectionFactory cFactory = new ConnectionFactory();
		cFactory.setHost(HOST);
		cFactory.setPort(PORT);
		cFactory.setUsername(USER_NAME);
		cFactory.setPassword(PASSWD);
		
		Connection connection = cFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		channel.basicPublish("", QUEUE_NAME, null, MESSAGE.getBytes());
		
		channel.close();
		connection.close();
	}
}
