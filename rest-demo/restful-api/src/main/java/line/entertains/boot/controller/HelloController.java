package line.entertains.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import line.entertains.boot.entity.UserInfo;
import line.entertains.boot.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
//@Scope("prototype")
@Slf4j
public class HelloController {

	@Autowired
	UserService userService;

	@GetMapping("/user")
	public String queryUser(Integer slpTime) {

		log.info("queryUser.  sltTime: {}. CONTROLLER:{}", slpTime, this.toString());
		try {
			for (int i = 0; i < slpTime; i++) {
				log.info("sleep --- {}", i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			log.error("sleep exception", e);
		}

		log.info("queryUser.  response.");
		return "QUERY USER!";
	}

	@PutMapping("/user")
	public String newUser() {
		log.info("queryUser. CONTROLLER:{}", this.toString());
		try {
			for (int i = 0; i < 65; i++) {
				log.info("sleep --- {}", i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			log.error("sleep exception", e);
		}
		return "NEW USER!";
	}

	@DeleteMapping("/user")
	public String dropUser() {
		return "DROP USER!";
	}

	@PostMapping("/user")
	public String updateUser(@RequestBody UserInfo userInfo) throws Exception {
		log.info("/user. userInfo: {}", userInfo.toString());
		try {
			for (int i = 0; i < 65; i++) {
				log.info("sleep *** {}", i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			log.error("sleep exception", e);
		}

//		String resp = userService.modifyUserInfo(userInfo);
		log.info("UPDATE USER SUCCESSFULLY!");

		return "{\"a\":\"b\"}";
	}
}
