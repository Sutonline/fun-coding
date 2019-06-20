package cn.kevin.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yongkang.zhang
 * created at 2019-06-10
 */
public class HttpClientTest {

	@Test
	public void singleClient() throws IOException, InterruptedException {
		// Create an HttpClient with the ThreadSafeClientConnManager.
		// This connection manager must be used if more than one thread will
		// be using the HttpClient.
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(100);

		CloseableHttpClient httpclient1 = HttpClients.custom()
				.setConnectionManager(cm)
				.build();

		CloseableHttpClient httpclient2 = HttpClients.custom()
				.setConnectionManager(cm)
				.build();

		try {
			// create an array of URIs to perform GETs on
			String[] urisToGet = {
					"http://hc.apache.org/",
					"http://www.baidu.com",
					"http://www.baidu.com",
					"http://www.baidu.com",
					"http://www.so.com",
					"http://www.so.com",
					"http://www.so.com",
					"http://www.bing.com",
					"http://www.bing.com",
					"http://www.bing.com",
					"http://www.bing.com"
			};

			// create a thread for each URI
			GetThread[] threads = new GetThread[urisToGet.length];
			for (int i = 0; i < threads.length; i++) {
				HttpGet httpget = new HttpGet(urisToGet[i]);
				if (i % 2 == 0) {
					threads[i] = new GetThread(httpclient2, httpget, i + 1);
				} else {
					threads[i] = new GetThread(httpclient1, httpget, i + 1);
				}
			}

			// start the threads
			for (int j = 0; j < threads.length; j++) {
				threads[j].start();
			}

			// join the threads
			for (int j = 0; j < threads.length; j++) {
				threads[j].join();
			}

		} finally {
			httpclient1.close();
			httpclient2.close();
		}
	}

	/**
	 * A thread that performs a GET.
	 */
	static class GetThread extends Thread {

		private final CloseableHttpClient httpClient;
		private final HttpContext context;
		private final HttpGet httpget;
		private final int id;

		public GetThread(CloseableHttpClient httpClient, HttpGet httpget, int id) {
			this.httpClient = httpClient;
			this.context = new BasicHttpContext();
			this.httpget = httpget;
			this.id = id;
		}

		/**
		 * Executes the GetMethod and prints some status information.
		 */
		@Override
		public void run() {
			try {
				System.out.println(id + " - about to get something from " + httpget.getURI());
				CloseableHttpResponse response = httpClient.execute(httpget, context);
				try {
					System.out.println(id + " - get executed");
					// get the response body as an array of bytes
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						byte[] bytes = EntityUtils.toByteArray(entity);
						System.out.println(id + " - " + httpget.getURI());
					}
				} finally {
					response.close();
				}
			} catch (Exception e) {
				System.out.println(id + " - error: " + e);
			}
		}

	}
}
