package line.entertains.hazelcast.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HazelController {

	@Autowired
	HazelcastInstance hazelcastInstance;
	
	private static final String MAP_NAME = "my-map";
	
	@PutMapping("/write")
	public String write(String key, String value) {
		log.info("WRITE... key: {}, value: {}", key, value);
		Map<String, String> map = hazelcastInstance.getMap(MAP_NAME);
		map.put(key, value);
		return "DONE";
	}
	
	@GetMapping("/read")
	public String read(String key) {
		log.info("READ... key: {}", key);
		Map<String, String> map = hazelcastInstance.getMap(MAP_NAME);
		return map.get(key);
	}
}
