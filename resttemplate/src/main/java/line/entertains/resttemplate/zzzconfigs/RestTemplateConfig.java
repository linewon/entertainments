package line.entertains.resttemplate.zzzconfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}
	
//	@Bean
//	public AsyncRestTemplate asyncRestTemplate(ThreadPoolTaskExecutor executor, AsyncClientHttpRequestFactory factory) {
//		AsyncRestTemplate restTemplate = new AsyncRestTemplate(executor);
//		restTemplate.setAsyncRequestFactory(factory);
//		return restTemplate;
//	}
	
	@Bean
	public AsyncRestTemplate asyncRestTemplate(AsyncClientHttpRequestFactory factory) {
		AsyncRestTemplate restTemplate = new AsyncRestTemplate(factory);
		return restTemplate;
	}
	
	@Bean
	public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		// 得想办法测一下这个超时设置的到底有没有用！
		factory.setReadTimeout(8000);
		factory.setConnectTimeout(3000);
		return factory;
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(100);
		executor.initialize();
		return executor;
	}
}
