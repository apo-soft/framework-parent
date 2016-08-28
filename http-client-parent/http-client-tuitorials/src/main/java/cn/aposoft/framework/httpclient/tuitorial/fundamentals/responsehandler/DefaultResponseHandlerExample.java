/**
 * 
 */
package cn.aposoft.framework.httpclient.tuitorial.fundamentals.responsehandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author LiuJian
 * @date 2016年8月27日 下午2:39:01
 */
public class DefaultResponseHandlerExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
            HttpGet httpget = new HttpGet("https://fen.gomemyf.com/fen-api/cities/event");
            ResponseHandler<MyJsonObject> rh = new ResponseHandler<MyJsonObject>() {
                @Override
                public MyJsonObject handleResponse(final HttpResponse response) throws IOException {
                    try {
                        StatusLine statusLine = response.getStatusLine();
                        HttpEntity entity = response.getEntity();
                        if (statusLine.getStatusCode() >= 300) {
                            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
                        }
                        if (entity == null) {
                            throw new ClientProtocolException("Response contains no content");
                        }
                        Gson gson = new GsonBuilder().create();
                        ContentType contentType = ContentType.getOrDefault(entity);
                        Charset charset = contentType.getCharset();
                        try (Reader reader = new InputStreamReader(entity.getContent(), charset);) {
                            return gson.fromJson(reader, MyJsonObject.class);
                        }
                    } finally {
                        if (response instanceof CloseableHttpResponse) {
                            ((CloseableHttpResponse) response).close();
                        }
                    }
                }
            };
            // try (CloseableHttpResponse rsp = httpclient.execute(httpget);) {
            // String result = EntityUtils.toString(rsp.getEntity(),
            // Charset.forName("UTF-8"));
            // System.out.println(result);
            // }
            
            MyJsonObject myjson = httpclient.execute(httpget, rh);
            System.out.println(myjson);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
