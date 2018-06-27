package line.entertains.mq.rabbit.demo.workers;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * http://www.rabbitmq.com/tutorials/tutorial-two-java.html
 * 
 * @author line
 *
 */
public class WorkerSender {

	private static final String QUEUE_NAME = "workers";

	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";
	

	private static final int CNT_MSG = 20;

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USER_NAME);
		factory.setPassword(PASSWD);
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		StringBuilder msg = new StringBuilder("hello message ");
		for (int i = 0; i < CNT_MSG; i++) {
			
			msg.append('。');
			String curMsg = msg.toString() + i;
			
			channel.basicPublish("", QUEUE_NAME, null, curMsg.getBytes());
			System.out.println("SENDER. send msg : " + curMsg);
		}
		
		channel.close();
		connection.close();
	}
}
