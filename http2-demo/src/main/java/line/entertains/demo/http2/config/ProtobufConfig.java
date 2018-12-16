package line.entertains.demo.http2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;


@Configuration
public class ProtobufConfig {

	/*
	 * 好像想这样加入spring容器之后，初始化的时候会自动给弄进去的
	 */
	@Bean
	public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}
}
