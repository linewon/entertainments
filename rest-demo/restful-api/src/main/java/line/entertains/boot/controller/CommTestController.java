package line.entertains.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import line.entertains.boot.service.PostContructService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/test")
@Slf4j
public class CommTestController {

	
	@Autowired
	PostContructService postConstructService;
	
	@GetMapping("/post-construct")
	public String test() {
		PostContructService constants = PostContructService.postConstructService;
		log.info("get constants !");
		constants.show();
		return constants.getPort();
	}
	
	
	public String jiadeMethod() {
		return new String();
	}
}
