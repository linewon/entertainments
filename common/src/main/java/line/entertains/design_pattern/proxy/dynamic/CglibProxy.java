package line.entertains.design_pattern.proxy.dynamic;

import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * CGLIB创建的动态代理对象比JDK创建的动态代理对象的性能更高，但是CGLIB创建代理对象时所花费的时间却比JDK多得多。
 * 所以对于单例的对象，因为无需频繁创建对象，用CGLIB合适，反之使用JDK方式要更为合适一些。
 * 同时由于CGLib由于是采用动态创建子类的方法，对于final修饰的方法无法进行代理。
 * 
 * @author line
 *
 */
@AllArgsConstructor
@Slf4j
public class CglibProxy<T> implements MethodInterceptor {

	private T target;

	public T getInstance() {
		Enhancer en = new Enhancer();
		en.setSuperclass(target.getClass());
		en.setCallback(this);
		return (T) en.create();
	}

	@Override
	public T intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

		log.info("proxy BEFORE by CGLIB-PROXY");
		T result = (T) method.invoke(target, args);
		log.info("proxy AFTER by CGLIB-PROXY");
		return result;
	}
}
