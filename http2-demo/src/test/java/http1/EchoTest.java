package http1;

import java.io.IOException;

import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/**
 * http1.1 client with okhttp
 * 
 * https://www.jianshu.com/p/da4a806e599b
 * 
 * @author line
 */
public class EchoTest {

	private String url = "http://localhost:8080/echo";
	// private String url = "https://localhost:8080/echo"; // try https

	@Test
	public void echo() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request request = new Builder().url(url).build();
		Response response = client.newCall(request).execute();

		String rslt = response.body().string();
		String protocol = response.protocol().name();
		
		System.out.println(rslt);
		System.out.println(protocol);
	}
}
