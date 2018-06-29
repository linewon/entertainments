package line.entertains.resttemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommonController {

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AsyncRestTemplate asyncRestTemplate;
	
	@GetMapping("/sync")
	public String sendInSync() {
		
		String resp = restTemplate.getForEntity("http://localhost:7080/user", String.class).getBody();
		
		return resp;
	}
	
	@GetMapping("/async")
	public String sendInAsync() {
		
		ListenableFuture<ResponseEntity<String>> forEntity = asyncRestTemplate.getForEntity("http://localhost:7080/user", String.class);
		
		String resp = "TEMP RESPONSE STRING!";
		DeferredResult<String> deferredResult = new DeferredResult<>();
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("=====rest response faliure======");
                deferredResult.setErrorResult(ex);
            }
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                log.info("--->async rest response success----, result = "+result.getBody());
                deferredResult.setResult(result.getBody());
            }
        });
        return resp;
	}
	
	@GetMapping("/async2")
	public DeferredResult<String> sendInAsync2() {
		
		ListenableFuture<ResponseEntity<String>> forEntity = asyncRestTemplate.getForEntity("http://localhost:7080/user", String.class);
		
		DeferredResult<String> deferredResult = new DeferredResult<>();
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("=====rest response faliure======");
                deferredResult.setErrorResult(ex);
            }
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                log.info("--->async rest response success----, result = "+result.getBody());
                deferredResult.setResult(result.getBody());
            }
        });
        return deferredResult;
	}
}
