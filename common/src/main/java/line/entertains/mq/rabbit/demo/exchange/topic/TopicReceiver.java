package line.entertains.mq.rabbit.demo.exchange.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class TopicReceiver {
	private static final String HOST = "192.168.85.130";
	private static final int PORT = 5672;
	private static final String USER_NAME = "line";
	private static final String PASSWD = "123123";

	private static final String EXCH_NAME = "topic-logs";
	private static final String ROUT_RULE = "topic";

//	private static final String QUEUE_NAME = "topic-logs-error-warn";
//	 private static final String QUEUE_NAME = "topic-logs-kernel";
	 private static final String QUEUE_NAME = "topic-logs-fanout";

	private static final String ROUT_KEY_ERROR = "*.error";
	private static final String ROUT_KEY_WARN = "*.warn";
	private static final String ROUT_KEY_KERNEL = "kernel.*";
	private static final String ROUT_KEY_FANOUT = "#";
	// private static final String ROUT_KEY_SHIT = "shit"; // 可以为已存在的队列新增routing key

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory cFactory = new ConnectionFactory();
		cFactory.setHost(HOST);
		cFactory.setPort(PORT);
		cFactory.setUsername(USER_NAME);
		cFactory.setPassword(PASSWD);

		Connection connection = cFactory.newConnection();

		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCH_NAME, ROUT_RULE);
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//		channel.queueBind(QUEUE_NAME, EXCH_NAME, ROUT_KEY_ERROR);
//		channel.queueBind(QUEUE_NAME, EXCH_NAME, ROUT_KEY_WARN);
//		 channel.queueBind(QUEUE_NAME, EXCH_NAME, ROUT_KEY_KERNEL);
		 channel.queueBind(QUEUE_NAME, EXCH_NAME, ROUT_KEY_FANOUT);
		channel.basicQos(1);
		System.out.println("************  binding ************\nqueueName: " + QUEUE_NAME
								+ "\nexchangeName: " + EXCH_NAME);

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
