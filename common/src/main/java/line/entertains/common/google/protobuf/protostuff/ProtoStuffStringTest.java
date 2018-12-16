package line.entertains.common.google.protobuf.protostuff;

import java.util.Arrays;

import org.junit.Test;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

public class ProtoStuffStringTest {
	
private static RuntimeSchema<String> schema = RuntimeSchema.createFrom(String.class);
	
	private static byte[] encode(String str) {
		return ProtostuffIOUtil.toByteArray(str, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
	}
	
	private static String decode(byte[] data) {
		/*
		 * 这个地方，通过Student类的Construct.newInstance构造对象，
		 * 其实和Student stu = new Student();没有区别。
		 */
		String str = schema.newMessage();
		ProtostuffIOUtil.mergeFrom(data, str, schema);
		return str;
	}
	
	@Test
	public void test() {
		String string = "123123123";
		byte[] data = encode(string);
		String string2 = decode(data);
		
		System.out.println(Arrays.toString(data));
		System.out.println(string2);
	}
}
