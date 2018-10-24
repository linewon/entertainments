package line.entertains.boot.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 不添加@Serivce的话，
 * 被@PostConstruct注解标注的方法能运行吗？
 * 
 * 
 * 在看到这个注解的全限定名之后，感觉就是可以运行到哪里了：
 * javax.annotation.PostConstruct
 * 
 * @author line
 *
 */
@Slf4j
@Service
public class PostContructService {

	public static PostContructService postConstructService;
	
	@Value("${server.port}")
	@Getter
	private String port;
	
	@PostConstruct
	public void init() {
		PostContructService.postConstructService = this;
		log.info("init post-construct-service goood");
	}
	
	public void show() {
		log.info("TIME TO SHOW!");
	}
}
