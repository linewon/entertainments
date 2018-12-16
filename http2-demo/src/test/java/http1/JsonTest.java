package http1;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;

import line.entertains.demo.http2.model.Student;

/**
 * http2.0强制https
 * 先测http1.1，再试2.0
 * 
 * @author line
 */
public class JsonTest {

	private static CloseableHttpClient client;
	
	@BeforeClass
	public static void buildClient() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, KeyManagementException {
		client= HttpClients.createDefault();
	}
	
	@Test
	public void jsonPost() throws ClientProtocolException, IOException {
		Student student = new Student();
		student.setName("line");
		student.setAge(18);
		student.setSex(true);
		student.setPhone("xxxxxxxxx");
		String json = JSON.toJSONString(student);
		
		HttpPost post = new HttpPost("http://localhost:8080/json");
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
}
