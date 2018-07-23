package line.entertains.io.net.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * https://blog.csdn.net/suifeng3051/article/details/48441629
 * http://www.cnblogs.com/xiaoxi/p/6604658.html
 * 
 * @author line
 *
 */
public class ServerSocketSelector {

	private int port;

	private Selector selector;

	// private ByteBuffer buffer = ByteBuffer.allocate(4 * 1024);

	public ServerSocketSelector(int port) throws IOException {
		this.port = port;
		init();
	}

	private void init() throws IOException {

		// 定义一个 selector
		selector = Selector.open();
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.configureBlocking(false);
		/*
		 * server-socket 绑定到 channel
		 */
		ServerSocket sSocket = ssChannel.socket();
		InetSocketAddress addr = new InetSocketAddress(port);
		sSocket.bind(addr);
		/*
		 * channel 绑定到 selector
		 */
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("listening port : " + port);
	}

	public void listen() throws IOException {

		while (selector.select() > 0) { // 这个循环的控制器到底应该怎么写？

			Iterator<SelectionKey> itr = selector.selectedKeys().iterator();
			while (itr.hasNext()) {
				SelectionKey key = itr.next();

				if (key.isAcceptable()) {
					doAccept(key);
				} else if (key.isReadable()) {
					doRead(key);
				} else if (key.isWritable()) {
					doWrite(key);
				}
				itr.remove();
			}
		}
	}

	private void doAccept(SelectionKey key) throws IOException {
		System.out.println("OP_ACCEPT...");

		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		SocketChannel sc = ssc.accept();
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ);
	}

	private void doRead(SelectionKey key) throws IOException {
		System.out.println("OP_READ...");

		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(8);
		StringBuilder sb = new StringBuilder();
		while (true) {
			int length = sc.read(buffer);
			if (length <= 0)
				break;

			sb.append(new String(buffer.array(), 0, length));
			buffer.clear();
		}
		String result = sb.toString();
		System.out.println(result);

		key.attach(result);
		key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
		// sc.register(selector, SelectionKey.OP_WRITE, result);
	}

	private void doWrite(SelectionKey key) throws IOException {
		System.out.println("OP_WRITE...");

		String result = (String) key.attachment();
		ByteBuffer buffer = ByteBuffer.wrap(result.getBytes());

		SocketChannel sc = (SocketChannel) key.channel();
		sc.write(buffer);

		key.interestOps(key.interestOps() ^ SelectionKey.OP_WRITE); // 置为多次读写
		// sc.close(); // 直接关闭信道
	}

	public static void main(String args2[]) throws Exception {
		int port = 19002;
		new ServerSocketSelector(port).listen();
	}
}
