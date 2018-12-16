package line.entertains.collection.map.hashmap;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class HashMapTest {

	Map<String, String> map = new HashMap<>();

	@Test
	public void put() {

		map.put("1", "123123");
		map.put("2", "xxxxx");
		map.put("3", "aaaaaaaaaaaaa");
		map.put("1", "z21x33");

		System.out.println(JSON.toJSONString(map));
	}

	@Test
	public void java() {
		int a, b;

		a = ((b = 3) ^ (b >>> 16));
		System.out.println(b);
		System.out.println(a);

		int c = 5, d;
		d = c >>> 16;
		System.out.println(d);
		System.out.println(c);
		c = c ^ d;
		System.out.println(d);
		System.out.println(c);
	}
}
