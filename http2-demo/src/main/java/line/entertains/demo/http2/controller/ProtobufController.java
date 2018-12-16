package line.entertains.demo.http2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import line.entertains.demo.http2.model.PersonFactory.Person;

/**
 * https://www.cnblogs.com/lishanlin/p/4470731.html
 * https://blog.csdn.net/u013219624/article/details/83152806
 * 
 * @author line
 */
@RestController
public class ProtobufController {

	@PostMapping(value = "/protobuf", consumes = "application/x-protobuf", produces = "application/x-protobuf")
	public Person test(@RequestBody Person person) {
		System.out.println(person.toString());
		return person;
	}
}
