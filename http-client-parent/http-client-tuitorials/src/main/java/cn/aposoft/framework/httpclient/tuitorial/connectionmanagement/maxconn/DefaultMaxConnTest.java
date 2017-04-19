/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.framework.httpclient.tuitorial.connectionmanagement.maxconn;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 警告: 以下连接数设置会被设置
 * {@link #setConnectionManager( org.apache.http.conn.HttpClientConnectionManager)} 覆盖
 * 最大连接数设置: 1. MaxConnPerRoute : 每个host的最大连接数 2. MaxConnTotal : Client的总的最大连接数
 * 
 * @author LiuJian
 * @date 2016年8月28日
 * 
 */
public class DefaultMaxConnTest {
    final static private CloseableHttpClient httpClient = createHttpClient();

    private static CloseableHttpClient createHttpClient() {
        return HttpClients.custom().setMaxConnPerRoute(200).setMaxConnTotal(200).build();
    }

    final static private ExecutorService service = Executors.newCachedThreadPool();
    final static private AtomicInteger totalThreadCount = new AtomicInteger(0);

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 200; i++) {
                service.submit(new HttpCommand(i));
            }
        } finally {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            service.shutdown();
            HttpClientUtils.closeQuietly(httpClient);
        }
    }

    static class HttpCommand implements Runnable {

        private int currSeq;

        public HttpCommand(int seq) {
            currSeq = seq;
        }

        @Override
        public void run() {
            System.out.println("Seq " + currSeq + " begins at " + totalThreadCount.incrementAndGet());
            HttpGet getRequest = new HttpGet("http://localhost/SnifferServlet");
            try (CloseableHttpResponse resp = httpClient.execute(getRequest);) {

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Seq " + currSeq + " ends" + totalThreadCount.decrementAndGet());
        }

    }

}
