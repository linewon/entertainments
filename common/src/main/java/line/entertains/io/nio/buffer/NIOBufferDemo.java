package line.entertains.io.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;

/**
 * 
 * 
 * @author line
 * @date 2019年1月10日 下午2:51:19
 */
public class NIOBufferDemo {

	public static final String NEW = "NEW";
	public static final String WRITE = "WRITE";
	public static final String READ = "READ";
	public static final String FLIP = "FLIP";
	public static final String CLEAR = "CLEAR";
	
	@Test
	public void boundCheck() {
		String srcStr = "0123456789-=";
		System.out.println("length of string: " + srcStr.length());
		
		ByteBuffer buf = ByteBuffer.allocate(10);
		showBuffer(NEW, buf);
		
		/*
		 * java.nio.BufferOverflowException
		 */
//		buf.put(srcStr.getBytes());
//		showBuffer(WRITE, buf);
		
		
	}

	@Test
	public void basicTest() {
		// 分配buf空间
		ByteBuffer buf = ByteBuffer.allocate(100);
		showBuffer(NEW, buf);

		// 向buf中写入数据
		for (int i = 0; i < buf.capacity() >>> 1; i++) {
			buf.put((byte) i);
		}
		showBuffer(WRITE, buf);

		// flip to set position and limit
		buf.flip();
		showBuffer(FLIP, buf);
		
		// 从buf中读取数据。  buf.get(idx)
		List<Integer> list = new ArrayList<>();
		for (int i = buf.position(); i < buf.limit(); i++) {
			byte b = buf.get(i);
			list.add((int) b);
		}
		System.out.println(Joiner.on(' ').join(list));
		showBuffer(READ, buf);

		// 从buf中读取数据。  buf.get()
		List<Integer> list2 = new ArrayList<>();
		for (;buf.hasRemaining();) {
			byte b = buf.get(); // method get() will move position to limit, while get(i) not.
			list2.add((int) b);
		}
		System.out.println(Joiner.on(' ').join(list2));
		showBuffer(READ, buf);
		
		// 读完了接着写
		buf.limit(buf.capacity()); // 让buf可以接着写
		for (int i = buf.capacity() >>> 1; i < buf.capacity(); i++) {
			buf.put((byte) i);
		}
		showBuffer(WRITE, buf);
		
		// clr
		buf.clear();
		showBuffer(CLEAR, buf);
		
		// put(string)
		buf.put("hello world!".getBytes());
		showBuffer(WRITE, buf);
		
		buf.flip(); // 写完了之后要flip()，才能接着读，否则
		showBuffer(FLIP, buf);
		System.out.println(new String(buf.array(), buf.position(), buf.limit()));
	}
	
	
	
	

	public static void showBuffer(Buffer buf) {
		int pos = buf.position();
//		int cap = buf.capacity();
		int lmt = buf.limit();

		JSONObject json = new JSONObject();
//		json.put("cap", cap);
		json.put("pos", pos);
		json.put("lmt", lmt);
		System.out.println(json.toJSONString());
		System.out.println(Arrays.toString((byte[])buf.array())); // buf.array() 返回整个allocate的字节数组
		System.out.println();
	}

	public static void showBuffer(String op, Buffer buf) {
		System.out.print("After " + op + ": \t");
		showBuffer(buf);
	}
}
