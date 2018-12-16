package line.entertains.common.reflect.jdk.method0;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.Test;

import line.entertains.common.entitys.Student;
import lombok.extern.slf4j.Slf4j;


/**
 * 最简单直接的reflect method调用
 * 还可以试试带缓存的jdk reflect
 * @author line
 */
@Slf4j
public class ParamTest {

	@Test
	public void test0() throws ClassNotFoundException {
		Class<?> stuClz = Class.forName(Student.class.getName());
		log.info(stuClz.toString());

		for (Method method : stuClz.getDeclaredMethods()) {
			log.info("-------------------");

			log.info("method name: {}", method.getName());
			log.info("method param-count: {}", method.getParameterCount());
			log.info("method accessable :{}", method.isAccessible());
			for (Parameter param : method.getParameters()) {
				log.info("******");

				log.info("param: {}", param.getName());
				log.info("param class: {}", param.getClass().getName());
				log.info("param class2: {}", param.getType().getName());

				log.info("******");
			}

			for (Class<?> clz : method.getParameterTypes()) {
				log.info("######");

				log.info("class: {}", clz.getName());

				log.info("######");
			}

			log.info("-------------------");
		}
	}

	@Test
	public void testMethod() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> stuClz = Student.class;
		Method method = stuClz.getMethod("getABC", String.class);
		log.info("method accessable :{}", method.isAccessible());
		Object result = method.invoke(new Student(), new String("abcaaa"));
		Class<?> rsltClz = method.getReturnType();
		log.info("returnType: {}, getClass: {}", rsltClz.getName(), result.getClass().getName());
		log.info("result is: {}", result.toString());
	}

	@Test
	public void testAccessable() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> stuClz = Student.class;
		Method method = stuClz.getDeclaredMethod("getABC", String.class); // get a private method
		long startTime, endTime;
		int loops = 2000;

		log.info("method accessable :{}", method.isAccessible()); // if not set TRUE, exception will be thrown
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loops; i++)
			method.invoke(new Student(), new String("abcaaa"));
		endTime = System.currentTimeMillis();
		log.info("time cost: {}", endTime - startTime);
		
		log.info("method accessable :{}", method.isAccessible()); // if not set TRUE, exception will be thrown
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loops; i++)
			method.invoke(new Student(), new String("abcaaa"));
		endTime = System.currentTimeMillis();
		log.info("time cost: {}", endTime - startTime);

		log.info("method accessable :{}", method.isAccessible()); // if not set TRUE, exception will be thrown
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loops; i++)
			method.invoke(new Student(), new String("abcaaa"));
		endTime = System.currentTimeMillis();
		log.info("time cost: {}", endTime - startTime);

		method.setAccessible(true);
		log.info("method accessable :{}", method.isAccessible());
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loops; i++)
			method.invoke(new Student(), new String("abcaaa"));
		endTime = System.currentTimeMillis();
		log.info("time cost: {}", endTime - startTime);

		method.setAccessible(false);
		log.info("method accessable :{}", method.isAccessible());
		startTime = System.currentTimeMillis();
		for (int i = 0; i < loops; i++)
			method.invoke(new Student(), new String("abcaaa"));
		endTime = System.currentTimeMillis();
		log.info("time cost: {}", endTime - startTime);
		
		/*
		 * 实验证明，setAccessible对method运行速度没有影响。
		 */
	}
}
