package line.entertains.mq.rabbit.demo.workers;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * http://www.rabbitmq.com/tutorials/tutorial-two-java.html
 * 
 * @author line
 *
 */
public class WorkerReceiver {

	private static final String QUEUE_NAME = "workers";

	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(PORT);
		factory.setUsername(USER_NAME);
		factory.setPassword(PASSWD);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		channel.basicQos(3); // 一次接受一个任务

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body);
				System.out.println("receive task: " + message);
				try {
					doWork(message);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("done task: " + message);
					channel.basicAck(envelope.getDeliveryTag(), false);
//					channel.basicAck(envelope.getDeliveryTag(), true);
				}
			}
		};

//		 channel.basicConsume(QUEUE_NAME, true, consumer);
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}

	private static void doWork(String work) throws InterruptedException {

		for (char ch : work.toCharArray())
			if (ch == '。')
				Thread.sleep(500);
	}
}
