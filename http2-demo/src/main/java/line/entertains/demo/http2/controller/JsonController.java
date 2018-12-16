package line.entertains.demo.http2.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import line.entertains.demo.http2.model.Student;

@RestController
public class JsonController {

	@PostMapping(value = "/json", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Student test(@RequestBody Student student) {
		System.out.println(student.toString());
		return student;
	}
}
