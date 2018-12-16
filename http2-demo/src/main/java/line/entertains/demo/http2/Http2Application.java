package line.entertains.demo.http2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 压测比较:
 * 1. http1.1-http2.0
 * 2. json-protobuf
 * 3. tomcat-undertow
 * 
 * @author line
 */
@SpringBootApplication
public class Http2Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Http2Application.class, args);
	}
}
