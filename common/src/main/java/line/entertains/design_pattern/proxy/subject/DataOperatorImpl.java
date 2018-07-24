package line.entertains.design_pattern.proxy.subject;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试动态代理类，代理不通主题
 * @author line
 *
 */
@Slf4j
public class DataOperatorImpl implements DataOperator {

	@Override
	public void queryData() {
		log.info("--- QUERY DATA ---");
	}

	@Override
	public void updateData() {
		log.info("*** UPDATE DATA ***");
	}
	
	public void otherOperation() {
		log.info("=== OTHER OPERATION ===");
	}
}
