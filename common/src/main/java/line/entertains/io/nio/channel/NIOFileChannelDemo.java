package line.entertains.io.nio.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

import line.entertains.io.nio.buffer.NIOBufferDemo;

/**
 * 
 * 
 * @author line
 * @date 2019年1月10日 下午4:47:58
 */
public class NIOFileChannelDemo {
	public static final String PATH = "src/main/resources/text/json.txt";

	/**
	 * read from file
	 */
	@Test
	public void readFile() {

		// 初始化一个fileChannel
		FileChannel fc = null;
		try {
			fc = FileChannel.open(Paths.get(PATH), StandardOpenOption.READ);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 初始化一个ByteBuffer
		ByteBuffer buf = ByteBuffer.allocate(10);

		// 通过fc将文件内容写入ByteBuffer
		StringBuilder sb = new StringBuilder();
		try {
			while (fc.read(buf) != -1) { // 读不到东西了
				NIOBufferDemo.showBuffer(NIOBufferDemo.WRITE, buf);
				buf.flip(); // limit = position; position = 0;
				NIOBufferDemo.showBuffer(NIOBufferDemo.FLIP, buf);
				String tmp = new String(buf.array(), buf.position(), buf.limit());
				NIOBufferDemo.showBuffer(NIOBufferDemo.READ, buf);
				buf.clear(); // clr, 是否真的有必要在这里clr一下？
				NIOBufferDemo.showBuffer(NIOBufferDemo.CLEAR, buf);
				sb.append(tmp);
				System.out.println("-----------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(sb.toString());

		try {
			fc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * write into file
	 */
	@Test
	public void writeFile() {
		String txt = "1234567890-=";

		// 初始化一个fileChannel
		File file = new File("src/main/resources/text/write_test");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		FileChannel fc = fos.getChannel();

		// 初始化一个ByteBuffer
		ByteBuffer buf = ByteBuffer.allocate(10);

		// 循环向buffer里写入字节
		final int len = buf.capacity();
		/*
		 * 模拟大字符串写文件。
		 * 每次写一小部分。
		 */
		try {
			for (int head = 0, rear = len; head < txt.length(); head += len, rear += len) {
				if (rear > txt.length())
					rear = txt.length();
				String sub = txt.substring(head, rear);
				buf.put(sub.getBytes());
				NIOBufferDemo.showBuffer(NIOBufferDemo.WRITE, buf);
				buf.flip();
				NIOBufferDemo.showBuffer(NIOBufferDemo.FLIP, buf);
				fc.write(buf); // 将buffer里的字节写入文件
				NIOBufferDemo.showBuffer(NIOBufferDemo.READ, buf);
				buf.clear();
				NIOBufferDemo.showBuffer(NIOBufferDemo.CLEAR, buf);
				System.out.println("-----------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fc.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
