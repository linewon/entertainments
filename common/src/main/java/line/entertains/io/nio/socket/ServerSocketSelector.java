package line.entertains.io.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * https://blog.csdn.net/suifeng3051/article/details/48441629
 * http://www.cnblogs.com/xiaoxi/p/6604658.html
 * 
 * @author line
 *
 */
@Slf4j
public class ServerSocketSelector {

	private int[] ports;

	private ByteBuffer buffer = ByteBuffer.allocate(4 * 1024);
	
	public ServerSocketSelector(int[] ports) throws IOException {
		this.ports = ports;
		run();
	}

	private void run() throws IOException {

		Selector selector = Selector.open();
		for (int i = 0; i < ports.length; i++) {
			ServerSocketChannel ssChannel = ServerSocketChannel.open();
			ssChannel.configureBlocking(false);
			/*
			 * server-socket 绑定到 channel
			 */
			ServerSocket sSocket = ssChannel.socket();
			InetSocketAddress addr = new InetSocketAddress(ports[i]);
			sSocket.bind(addr);
			/*
			 * channel 绑定到 selector
			 */
			SelectionKey key = ssChannel.register(selector, SelectionKey.OP_ACCEPT);
			log.info("listening port: {}", ports[i]);
		}
		while (selector.select() > 0) {
			
			Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
			while (itr.hasNext()) {
				SelectionKey key = itr.next();
				if (key.isAcceptable()) {
					
				} else if (key.isReadable()) {
					
				}
				itr.remove();
			}
		}
	}
	public static void main(String args2[]) throws Exception {
	      String args[]={"9001","9002","9003"};
	      if (args.length <= 0) {
	           System.err.println("Usage: java MultiPortEcho port [port port ...]");
	           System.exit(1);
	      }
	      int ports[] = new int[args.length];
	      for (int i = 0; i < args.length; ++i) {
	           ports[i] = Integer.parseInt(args[i]);
	      }
	      new ServerSocketSelector(ports);
	 }
}
