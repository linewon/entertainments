package https;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
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
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;

import line.entertains.demo.http2.model.Student;

public class JsonTest {


	private static String url = "https://localhost:8080/json";
	
	private static CloseableHttpClient client;
	@Test
	public void json() throws ClientProtocolException, IOException {
		Student student = new Student();
		student.setName("line");
		student.setAge(18);
		student.setSex(true);
		student.setPhone("xxxxxxxxx");
		String json = JSON.toJSONString(student);
		
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(json);
		post.setEntity(entity);
		post.addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		post.addHeader("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		CloseableHttpResponse resp = client.execute(post);
		StatusLine sl = resp.getStatusLine();
		int status = sl.getStatusCode();
		System.out.println(status);
		if (200 == status) {
			HttpEntity respEntity = resp.getEntity();
			String rslt = EntityUtils.toString(respEntity);
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
