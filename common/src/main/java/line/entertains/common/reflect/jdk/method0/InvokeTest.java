package line.entertains.common.reflect.jdk.method0;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import line.entertains.common.reflect.DemoClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvokeTest {

	private DemoClass demo = new DemoClass();

	@Test
	public void testInvoke() {
		Class<?> clz = demo.getClass();
		String methodName = "findByNameAndAge";

		JSONObject json = new JSONObject();
		json.put("name", "line");
		json.put("age", 18);

		String[] argsName = new String[] { "name", "age" };

		for (Method method : clz.getDeclaredMethods()) {
			if (method.getName().equals(methodName)) {
				log.info("find the method: {}, paramCount: {}", methodName, method.getParameterCount());
				Object result = null;
				Object[] args = getMethodArgs(argsName, json);
				log.info("method args: {}", json);
				try {
					result = method.invoke(demo, args);
//					method.invoke(demo, json.get("name"), json.get("age"));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				log.info("result: {}", result);
			}
		}
	}

	private Object[] getMethodArgs(String[] argsName, JSONObject json) {
		Object[] args = new Object[json.size()];
		for (int i = 0; i < argsName.length; i++) {
			Object arg = json.get(argsName[i]);
			args[i] = arg;
		}
		return args;
	}
}
