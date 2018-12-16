package line.entertains.common.google.protobuf.protostuff;

import java.util.Arrays;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import line.entertains.common.entitys.Student;
import lombok.extern.slf4j.Slf4j;

/**
 * google.protobuf -> protostuff
 * 
 * @author line
 */
@Slf4j
public class ProtoStuffObjectTest {

	/**
	 * create的时候，会把Student类反射相关的信息存到schema里，用于之后的序列化操作
	 */
	private static RuntimeSchema<Student> schema = RuntimeSchema.createFrom(Student.class);
	
	private static byte[] encode(Student stu) {
		return ProtostuffIOUtil.toByteArray(stu, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
	}
	
	private static Student decode(byte[] data) {
		/*
		 * 这个地方，通过Student类的Construct.newInstance构造对象，
		 * 其实和Student stu = new Student();没有区别。
		 */
		Student stu = schema.newMessage();
		ProtostuffIOUtil.mergeFrom(data, stu, schema);
		return stu;
	}
	
	@Test
	public void test() {
		Student stu = new Student("line", 18, true, "xxxxxxxxx");
		byte[] data = encode(stu);
		Student newStu = decode(data);
		
		// conclusion print
		String rslt = JSON.toJSONString(stu);
		int length = rslt.getBytes().length;
		log.info("ori stu: {}, byte[] length: {}", rslt, length);
		rslt = Arrays.toString(data);
		String tmp = new String(data);
		length = data.length;
		log.info("ori stu: {}, byte[] length: {}, tmpData: {}", rslt, length, tmp);
		rslt = JSON.toJSONString(newStu);
		length = rslt.getBytes().length;
		log.info("ori stu: {}, byte[] length: {}", rslt, length);
	}
}
