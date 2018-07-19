package line.entertains.resttemplate.exception;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionProcessor {

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Map<String, String>> handlerSocketTimeoutException(SocketTimeoutException ex) {
		
		Map<String, String> resp = new HashMap<>();
		resp.put("respCode", "5666");
		resp.put("respInfo", "time-out");
		
		ResponseEntity<Map<String, String>> entity = new ResponseEntity<Map<String,String>>(resp, HttpStatus.GATEWAY_TIMEOUT);
		
		return entity;
	}
}
