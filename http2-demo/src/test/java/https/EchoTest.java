package https;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 这里一开始以为配了client的https证书，就可以发送http2.0请求了
 * 实际上，用浏览器访问后台接口的时候，也确实是h2了，证明后台的http2已经开通了。
 * 但是最后发现这里的client发送的还是https based on http1.1，原来是HTTPClient只有5.0以上的beta版才支持http2
 * 
 * 现在尝试用okhttp client(3以上版本)来作为客户端发送请求。
 * @author line
 */
public class EchoTest {

	private static String url = "https://localhost:8080/echo";
	
	private static CloseableHttpClient client;

	@Test
	public void echo() throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse resp = client.execute(get);
		StatusLine sl = resp.getStatusLine();
		int status = sl.getStatusCode();
		System.out.println(status);
		if (200 == status) {
			String rslt = EntityUtils.toString(resp.getEntity());
			System.out.println(rslt);
		} else {
			String info = sl.getReasonPhrase();
			System.out.println(info);
		}
	}

	@BeforeClass
	public static void buildClient() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, KeyManagementException {
		Resource sslRsc = new ClassPathResource("secret/mytruststore.jks");

		SSLContext sslContext = SSLContexts.custom()
				.loadTrustMaterial(sslRsc.getFile(), "line1983".toCharArray(), new TrustSelfSignedStrategy()).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null,
				NoopHostnameVerifier.INSTANCE);

		HttpClientBuilder builder = HttpClients.custom().setSSLSocketFactory(sslsf);

		client = builder.build();
	}
	@AfterClass
	public static void closeClient() throws IOException {
		client.close();
	}
}
