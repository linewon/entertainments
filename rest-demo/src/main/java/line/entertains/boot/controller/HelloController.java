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

@RestController
public class HelloController {

	@Autowired
	UserService userService;
	
	@PutMapping("/user")
	public String newUser() {
		return "NEW USER!";
	}

	@GetMapping("/user")
	public String queryUser() {
		return "QUERY USER!";
	}
	
	@DeleteMapping("/user")
	public String dropUser() {
		return "DROP USER!";
	}
	
	@PostMapping("/user")
	public String updateUser(@RequestBody UserInfo userInfo) throws Exception {
		return userService.modifyUserInfo(userInfo);
	}
}
