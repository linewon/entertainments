package line.entertains.mq.rabbit.demo.exchange.direct;

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
 * RabbitMQ demo: direct exchange
 * 
 * exchange "direct-routing" 3 queues:
 * 		1> qName=queue-direct-divided3, rKey=divided3;
 * 		2> qName=queue-direct-else, rKey=else;
 * 		3> qName=queue-direct-both, rKey=divided3 & else.
 * 
 * @author line
 *
 */
public class DirectReceiver {

	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";

	private static final String EXCH_NAME = "direct-routing";

//	 private static final String QUEUE_NAME = "queue-direct-divided3";
//	 private static final String QUEUE_NAME = "queue-direct-else";
	private static final String QUEUE_NAME = "queue-direct-both";

	private static final String ROUT_KEY_DIVI = "divided3";
	private static final String ROUT_KEY_ELSE = "else";
	private static final String ROUT_KEY_SHIT = "shit"; // 可以为已存在的队列新增routing key

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory cFactory = new ConnectionFactory();
		cFactory.setHost(HOST);
		cFactory.setPort(PORT);
		cFactory.setUsername(USER_NAME);
		cFactory.setPassword(PASSWD);

		Connection connection = cFactory.newConnection();

		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCH_NAME, "direct");
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCH_NAME, ROUT_KEY_DIVI);
		channel.queueBind(QUEUE_NAME, EXCH_NAME, ROUT_KEY_ELSE);
//		channel.queueBind(QUEUE_NAME, EXCH_NAME, ROUT_KEY_SHIT);
		channel.basicQos(1);
		System.out.println("************  binding ************\nqueueName: " + QUEUE_NAME
							+ "\nexchangeName: " + EXCH_NAME
							+ "\nroutingKey: " + ROUT_KEY_DIVI
//							+ "\nroutingKey: " + ROUT_KEY_ELSE
							+ ", " + ROUT_KEY_ELSE
							);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.print("receive task: " + message);
				System.out.println(" --routing key: " + envelope.getRoutingKey());

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					channel.basicAck(envelope.getDeliveryTag(), false);
					System.out.println("done task: " + message);
				}
			}
		};
		
		channel.basicConsume(QUEUE_NAME, false, consumer);
	}
}
