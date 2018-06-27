package line.entertains.mq.rabbit.demo.exchange.fanout;

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
public class FanoutReceiver {
	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";

	private static final String EXCH_NAME = "logs";
	
//	private static final String QUEUE_NAME = "log-queue";
//	private static final String QUEUE_NAME = "log-queue-another";
	private static final String QUEUE_NAME = "log-queue-another-another";

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory cFactory = new ConnectionFactory();
		cFactory.setHost(HOST);
		cFactory.setPort(PORT);
		cFactory.setUsername(USER_NAME);
		cFactory.setPassword(PASSWD);

		Connection connection = cFactory.newConnection();
		
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCH_NAME, "fanout");
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCH_NAME, "");
		channel.basicQos(1);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("receive task: " + message);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					channel.basicAck(envelope.getDeliveryTag(), false);
					System.out.println("done task: " + message);
				}
			}
		};
//		channel.basicConsume(QUEUE_NAME, true, consumer);
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
