package line.entertains.boot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

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
	public String updateUser() {
		return "UPDATE USER!";
	}
}
