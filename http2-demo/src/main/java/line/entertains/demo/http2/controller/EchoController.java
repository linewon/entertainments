package line.entertains.demo.http2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

	@GetMapping("/echo")
	public String echo() {
		System.out.println("ECHO");
		return "ECHO!!";
	}
}
