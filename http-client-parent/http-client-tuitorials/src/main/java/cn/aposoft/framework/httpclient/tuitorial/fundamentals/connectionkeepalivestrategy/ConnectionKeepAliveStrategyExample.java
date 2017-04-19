/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.framework.httpclient.tuitorial.fundamentals.connectionkeepalivestrategy;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/**
 * 
 * 
 * @author LiuJian
 * @date 2016年8月27日
 * 
 */
public class ConnectionKeepAliveStrategyExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                if (keepAlive == -1) {
                    // Keep connections alive 5 seconds if a keep-alive value
                    // has not be explicitly set by the server
                    keepAlive = 5000;
                }
                return keepAlive;
            }
        };

        RequestConfig requestConfig = RequestConfig.custom()//
                .setSocketTimeout(1000)//
                .setConnectTimeout(1000).build();

        HttpContext context = new BasicHttpContext();
        try (CloseableHttpClient httpclient = HttpClients.custom().setKeepAliveStrategy(keepAliveStrat).build();) {
            HttpGet httpget1 = new HttpGet("http://localhost/1");
            httpget1.setConfig(requestConfig);

            CloseableHttpResponse response1 = httpclient.execute(httpget1, context);
            try {
                HttpEntity entity1 = response1.getEntity();
                try (InputStream input1 = entity1.getContent();) {

                }
            } finally {
                response1.close();
            }
            HttpGet httpget2 = new HttpGet("http://localhost/2");
            CloseableHttpResponse response2 = httpclient.execute(httpget2, context);
            try {
                HttpEntity entity2 = response2.getEntity();
                try (InputStream input = entity2.getContent();) {
                }
            } finally {
                response2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
