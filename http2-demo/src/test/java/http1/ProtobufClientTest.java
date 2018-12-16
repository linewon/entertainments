package http1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;

import line.entertains.demo.http2.model.PersonFactory.Person;
import others.HttpGetWithEntity;


public class ProtobufClientTest {

	private CloseableHttpClient client = HttpClients.createDefault();
	
	/**
	 * http-get with requestBody
	 */
	@Test
	public void get() {
		
		Person request = Person.newBuilder().setName("line").setAge(18).setSex(true).setPhone("xxxxxxxxx").build();
		ByteArrayInputStream is = new ByteArrayInputStream(request.toByteArray());
		InputStreamEntity entity = new InputStreamEntity(is);
		
		HttpGetWithEntity get = new HttpGetWithEntity("http://localhost:8080/protobuf");
		get.addHeader("Content-Type", "application/x-protobuf");
		get.addHeader("Accept", "application/x-protobuf");
		get.setEntity(entity);
		
		CloseableHttpResponse response = null;
		try {
			response = client.execute(get);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int status = response.getStatusLine().getStatusCode();
		System.out.println(status);
		if (200 == status) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				response.getEntity().writeTo(os);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					response.close();
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			byte[] data = os.toByteArray();
			
			try {
				Person person = Person.parseFrom(data);
				System.out.println(person);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void post() {
		
		Person request = Person.newBuilder().setName("line").setAge(18).setSex(true).setPhone("xxxxxxxxx").build();
		ByteArrayInputStream is = new ByteArrayInputStream(request.toByteArray());
		InputStreamEntity entity = new InputStreamEntity(is);

		HttpPost post = new HttpPost("http://localhost:8080/protobuf");
		post.addHeader("Content-Type", "application/x-protobuf");
		post.addHeader("Accept", "application/x-protobuf");
		post.setEntity(entity);
		
		CloseableHttpResponse response = null;
		try {
			response = client.execute(post);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int status = response.getStatusLine().getStatusCode();
		System.out.println(status);
		if (200 == status) {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				response.getEntity().writeTo(os);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					response.close();
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			byte[] data = os.toByteArray();
			
			try {
				Person person = Person.parseFrom(data);
				System.out.println(person);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
		}
	}
}
