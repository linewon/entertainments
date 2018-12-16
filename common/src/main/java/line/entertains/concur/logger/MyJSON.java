package line.entertains.concur.logger;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyJSON {

	public String toJSONString(Object obj) {
		String rslt = JSON.toJSONString(obj);
		log.info("parse json here");
		return rslt;
	}
}
