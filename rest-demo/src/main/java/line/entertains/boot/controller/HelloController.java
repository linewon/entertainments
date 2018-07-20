package line.entertains.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import line.entertains.boot.entity.UserInfo;
import line.entertains.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

	@Autowired
	UserService userService;

	@GetMapping("/user")
	public String queryUser(Integer slpTime) {

		log.info("sltTime: {}", slpTime);
		try {
			for (int i = 0; i < slpTime; i++) {
				log.info("sleep --- {}", i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			log.error("sleep exception", e);
		}
		return "QUERY USER!";
	}

	@PutMapping("/user")
	public String newUser() {
		return "NEW USER!";
	}

	@DeleteMapping("/user")
	public String dropUser() {
		return "DROP USER!";
	}

	@PostMapping("/user")
	public String updateUser(@RequestBody UserInfo userInfo) throws Exception {
		log.info("/user. request: {}", JSON.toJSONString(userInfo));
		return userService.modifyUserInfo(userInfo);
	}
}
