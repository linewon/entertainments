package line.entertains.common.google.protobuf;

import java.util.Arrays;

import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;

import line.entertains.common.google.protobuf.PersonFactory.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProtoBufTest {

	@Test
	public void test() {
		Person.Builder person = Person.newBuilder();
		person.setAge(18).setName("line").setPhone("xxxxxxxxx").setSex(true);
//		String json = JSON.toJSONString(person);
//		log.info("person: {}, len: {}",json, json.getBytes().length); // 用json会报序列化异常
		log.info(person.toString());
		
		byte[] data = person.build().toByteArray();
		log.info("data: {}, len: {}", Arrays.toString(data), data.length);
		
		try {
			Person person2 = Person.parseFrom(data);
//			String json = JSON.toJSONString(person2);
//			log.info("person2: {}, len: {}", json, json.getBytes().length);
			log.info(person2.toString());
			
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		
	}
}
