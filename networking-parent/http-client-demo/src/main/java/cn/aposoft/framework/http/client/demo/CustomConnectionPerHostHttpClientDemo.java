/**
 * 
 */
package cn.aposoft.framework.http.client.demo;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @author Jann Liu
 *
 */
public class CustomConnectionPerHostHttpClientDemo {

	private static CloseableHttpClient client = createHttpClient();

	private static CloseableHttpClient createHttpClient() {
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setMaxConnPerRoute(200);
		builder.setMaxConnTotal(200);
		CloseableHttpClient client = builder.build();
		return client;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		try {
			Task task = new Task();
			for (int i = 0; i < 40; i++) {
				service.execute(task);
			}
		} finally {
			service.shutdown();
		}
	}

	private static class Task implements Runnable {
		private HttpGet get0 = new HttpGet("http://localhost/payment/wechat");
		private HttpGet get1 = new HttpGet("http://localhost/payment/refund");
		private Random r = new Random();

		@Override
		public void run() {

			try {
				System.out.println(
						"begin: + " + System.currentTimeMillis() + ",Thread:" + Thread.currentThread().getId());
				HttpGet get = r.nextBoolean() ? get0 : get1;
				try (CloseableHttpResponse response = client.execute(get)) {
					String s = EntityUtils.toString(response.getEntity());
					System.out.println(s);
				}
				System.out
						.println("end: + " + System.currentTimeMillis() + ",Thread:" + Thread.currentThread().getId());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
