package line.entertains.resttemplate.zzzconfigs;

/**
 * restTemplate所有的bean初始化都弄到一个config类里去好了
 * 
 * RestTemplateConfig
 * 
 * @author line
 *
 */
//@Configuration
public class AsyncRestTemplateConfig {

//	@Bean
//	public AsyncRestTemplate asyncRestTemplate(ThreadPoolTaskExecutor executor, AsyncClientHttpRequestFactory factory) {
//		AsyncRestTemplate restTemplate = new AsyncRestTemplate(executor);
//		restTemplate.setAsyncRequestFactory(factory);
//		return restTemplate;
//	}
//	
//	@Bean
//	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(100);
//		executor.setMaxPoolSize(100);
//		executor.setQueueCapacity(100);
//		executor.initialize();
//		return executor;
//	}
	
	// 在别的地方已经定义过了 -> RestTemplateConfig
//	@Bean
//	public SimpleClientHttpRequestFactory clienthttpRequestFactory() {
//		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//		factory.setReadTimeout(8000);
//		factory.setConnectTimeout(3000);
//		return factory;
//	}
}
