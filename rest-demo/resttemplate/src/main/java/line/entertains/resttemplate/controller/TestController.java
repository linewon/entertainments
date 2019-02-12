package line.entertains.resttemplate.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import line.entertains.resttemplate.model.request.ReqParams;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author line
 * @date 2019年1月23日 上午10:12:21
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {
	
	private int sleepTime = 5 * 1000;

	private final String SUCC = "SUCCESS";
	
	@GetMapping("/req-params")
	public String get(ReqParams request) {
		log.info("GET for: {}", JSON.toJSONString(request));
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("GET resp SUCC");
		return SUCC;
	}
	
	@GetMapping
	public String get(@RequestParam Map<String, String> request) {
		log.info("GET for: {}", JSON.toJSONString(request));
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("GET resp SUCC");
		return SUCC;
	}
	
	@PostMapping
	public String post(@RequestBody Map<String, String> request) {
		log.info("POST for: {}", JSON.toJSONString(request));
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("POST resp SUCC");
		return SUCC;
	}
}
