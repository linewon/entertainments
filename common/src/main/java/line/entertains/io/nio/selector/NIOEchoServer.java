package line.entertains.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import line.entertains.io.nio.buffer.NIOBufferDemo;

/**
 * 写的有问题。
 * 在doRead死循环了，sc.read(buf) != -1 达不到这个条件。
 * 看看netty是怎么做的吧！
 * 
 * @author line
 * @date 2019年1月14日 上午10:30:12
 */
public class NIOEchoServer {

	private Selector sl = null;
	private ByteBuffer readBuf = ByteBuffer.allocate(8);
	private ByteBuffer writeBuf = ByteBuffer.allocate(8);

	public NIOEchoServer(int port) {
		config(port);
	}

	private void config(int port) {
		ServerSocketChannel ssc = null;
		try {
			sl = Selector.open();
			ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);
			ssc.socket().bind(new InetSocketAddress(port));
			ssc.register(sl, SelectionKey.OP_ACCEPT);
			System.out.println("服务器启动成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void service() {
		try {
			while (true) {
				int select = sl.select();
				if (select > 0) {
					Set<SelectionKey> keys = sl.selectedKeys();
					Iterator<SelectionKey> itr = keys.iterator();
					while (itr.hasNext()) {
						SelectionKey key = itr.next();
						itr.remove();
						if (key.isAcceptable()) {
							doAccept(key);
						} else if (key.isReadable()) {
							doRead(key);
						} else if (key.isWritable()) {
							doWrite(key);
						}
					}
					keys.clear();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doAccept(SelectionKey key) {
		System.out.println("监听到ACCEPT");
		ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
		try {
			SocketChannel sc = ssc.accept();
			sc.configureBlocking(false);
			sc.register(sl, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doRead(SelectionKey key) {
		System.out.println("监听到READ");
		SocketChannel sc = (SocketChannel) key.channel();
		readBuf.clear();
		StringBuilder sb = new StringBuilder();
		try {
			while (sc.read(readBuf) > 0) {
				NIOBufferDemo.showBuffer(NIOBufferDemo.WRITE, readBuf);
				readBuf.flip();
				NIOBufferDemo.showBuffer(NIOBufferDemo.FLIP, readBuf);
				String tmp = new String(readBuf.array(), readBuf.position(), readBuf.limit());
				readBuf.clear();
				NIOBufferDemo.showBuffer(NIOBufferDemo.CLEAR, readBuf);
				sb.append(tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = sb.toString();
		System.out.println(text);
		if ("quit".equals(text)) {
			try {
				key.channel().close();
				key.cancel();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		try {
			sc.register(sl, SelectionKey.OP_WRITE);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}
	}

	private void doWrite(SelectionKey key) {
		System.out.println("监听到WRITE");
		SocketChannel sc = (SocketChannel) key.channel();
		writeBuf.clear();
		String text = "hello";
		writeBuf.put(text.getBytes());
		writeBuf.flip();
		
		try {
			sc.write(writeBuf);
			sc.register(sl, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		NIOEchoServer server = new NIOEchoServer(18080);
		server.service();
	}
}
