package http2;

import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.BeforeClass;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EchoTest {
	private static String url = "https://localhost:8080/echo";
	
	private static OkHttpClient client;

	@Test
	public void echoHttps() throws Exception {

		// build request
		Request request = new Request.Builder()
				.url(url)
//				.addHeader("Connection", "Upgrade, HTTP2-Settings")
//				.addHeader("Upgrade", "h2c")
				.build();
		// send request
		Response response = client.newCall(request).execute();
		// print message
		String rslt = response.body().string();
		String protocol = response.protocol().name();
		System.out.println(rslt); // 打印响应：ECHO
		System.out.println(protocol); // 打印http protocol
	}
	
	/**
	 * SSL单向、双向认证
	 * https://www.jianshu.com/p/b0b6b88fe9fe
	 * https://www.jianshu.com/p/119c4dbb7225
	 */
	@BeforeClass
	public static void initClient() throws Exception {
		// 导入公钥证书
//		KeyStore keyStore = KeyStore.getInstance("JKS"); // .jks格式  
//		Resource resource = new ClassPathResource("secret/mytruststore.jks");
//		keyStore.load(resource.getInputStream(), "line1983".toCharArray());
		// 初始化证书管理factory
//		TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//		factory.init(keyStore);
		// 获得X509TrustManager
//		TrustManager[] trustManagers = factory.getTrustManagers(); // 以上的代码为https双向认证所需的。吗？
		
		TrustManager[] trustManagers = new TrustManager[]{ // https单向认证
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[]{};
                    }
                }
        };
		X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
		
		// 初始化sslContext
		SSLContext sslContext = SSLContext.getInstance("TLSv1.2"); // 这里选择的是tls1.2版本
		sslContext.init(null, trustManagers, null);
		
		// 获得sslSocketFactory
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		// 初始化client builder
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
//		List<Protocol> protocols = new ArrayList<>();
//		protocols.add(Protocol.HTTP_1_1);
//		protocols.add(Protocol.HTTP_2); // 只指定h2的话会抛异常
		builder.sslSocketFactory(sslSocketFactory, trustManager)
//				.protocols(protocols)
				.hostnameVerifier(new HostnameVerifier() { // 放过host验证
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
		
		// build client
		client = builder.build();
	}
}