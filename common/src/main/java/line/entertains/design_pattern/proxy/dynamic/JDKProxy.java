package line.entertains.design_pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Proxy.newProxyInstance(ClassLoader loader,
                    Class<?>[] interfaces,
                    InvocationHandler h)
 * JDK proxy
 * 
 * @author line
 *
 */
@Slf4j
@AllArgsConstructor
public class JDKProxy implements InvocationHandler {

	private Object target;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		log.info("proxy BEFORE by JDK-PROXY");
		Object result = method.invoke(target, args);
		log.info("proxy AFTER by JDK-PROXY");
		
		return result;
	}
}
