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

/**
 * 同步请求 & 异步请求 の比较
 * 同时比较 DeferredResult & Callable の异步请求。
 * 
 * 所以到底有什么不同？
 * 
 * @author line
 * @date 2019年1月23日 上午10:45:45
 */
@RestController
@Slf4j
public class CommonController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	AsyncRestTemplate asyncRestTemplate;

	private static final String TEST_URL = "http://localhost:7081/test";

	/**
	 * 发送同步请求
	 */
	@GetMapping("/sync")
	public String sendInSync() {
		log.info("/sync. receive request");
		String resp = restTemplate.getForEntity(TEST_URL, String.class).getBody();
		log.info("/sync. receive sync response");
		return resp;
	}

	/**
	 * 发送异步请求，并直接返回成功
	 * 请求响应打印在日志中。
	 */
	@GetMapping("/async/send")
	public String sendInAsync() {
		log.info("/async/send. receive request");
		ListenableFuture<ResponseEntity<String>> forEntity = asyncRestTemplate.getForEntity(TEST_URL, String.class);

		DeferredResult<String> deferredResult = new DeferredResult<>();
		forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			@Override
			public void onFailure(Throwable ex) {
				log.error("/async/send. catch EX");
				deferredResult.setErrorResult(ex);
			}

			@Override
			public void onSuccess(ResponseEntity<String> result) {
				log.info("/async/send. receive async response: {}", result.getBody());
				deferredResult.setResult(result.getBody());
			}
		});
		log.info("/async/send. return succ");
		return "ASYNC SENT!";
	}

	/**
	 * 发送异步请求，
	 * 用DeferredResult接收响应并返回
	 */
	@GetMapping("/async/result")
	public DeferredResult<String> sendInAsync2() {
		log.info("/async/result. receive request");
		ListenableFuture<ResponseEntity<String>> forEntity = asyncRestTemplate.getForEntity(TEST_URL, String.class);

		DeferredResult<String> deferredResult = new DeferredResult<>();
		forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
			@Override
			public void onFailure(Throwable ex) {
				log.error("/async/result. catch EX");
				deferredResult.setErrorResult(ex);
			}

			@Override
			public void onSuccess(ResponseEntity<String> result) {
				log.info("/async/result. receive async response: {}", result.getBody());
				deferredResult.setResult(result.getBody());
			}
		});
		log.info("/async/result. try to return");
		return deferredResult;
	}
	
	/**
	 * 发送异步请求，
	 * 用Callable接收响应并返回
	 */
}
