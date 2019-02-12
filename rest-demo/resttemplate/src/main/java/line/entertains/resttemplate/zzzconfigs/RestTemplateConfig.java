package line.entertains.resttemplate.zzzconfigs;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * configs for RestTemplate & AsyncRestTemplate in Spring
 * 
 * @author line
 */
@Configuration
public class RestTemplateConfig {

	/**
	 * spring-bean for restTemplate
	 */
	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory,
			List<HttpMessageConverter<?>> messageConverters) {
		RestTemplate client = new RestTemplate(factory);
		client.setMessageConverters(messageConverters);
		return client;
	}

	/**
	 * spring-bean for asyncRestTemplate
	 */
	@Bean
	public AsyncRestTemplate asyncRestTemplate(AsyncClientHttpRequestFactory factory,
			List<HttpMessageConverter<?>> messageConverters) {
		AsyncRestTemplate client = new AsyncRestTemplate(factory);
		client.setMessageConverters(messageConverters);
		return client;
	}

	/**
	 * connection-config for restClient
	 */
	@Bean
	public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory(ThreadPoolTaskExecutor executor) {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(8000); // time-out for execution
		factory.setConnectTimeout(3000); // time-out for connection
		factory.setTaskExecutor(executor);
		return factory;
	}

	/**
	 * ThreadPool-config for restClient
	 */
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(100);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(100);
		executor.initialize();
		return executor;
	}

	/**
	 * MessageConverters for restClient
	 */
	@Bean
	public List<HttpMessageConverter<?>> messageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter()); // JSON
		converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8"))); // String
		return converters;
	}
}
