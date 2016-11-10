/**
 *   Copyright  :  www.aposoft.cn
 */
package cn.aposoft.framework.httpclient.tuitorial.fundamentals.httprequestinterceptor;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * @author LiuJian
 * @date 2016年8月28日
 * 
 */
public class HttpRequestInterceptorExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.custom().addInterceptorLast(new HttpRequestInterceptor() {
            public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
                AtomicInteger count = (AtomicInteger) context.getAttribute("count");
                request.addHeader("Count", Integer.toString(count.getAndIncrement()));
            }
        }).build();
        AtomicInteger count = new AtomicInteger(1);
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAttribute("count", count);
        HttpGet httpget = new HttpGet("http://www.aposoft.cn/");
        for (int i = 0; i < 10; i++) {
            try (CloseableHttpResponse response = httpclient.execute(httpget, localContext);) {
                HttpEntity entity = response.getEntity();
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
