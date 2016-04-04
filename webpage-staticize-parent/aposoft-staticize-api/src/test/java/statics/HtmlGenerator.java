package statics;
  

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.xml.ws.Response;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;  


public class HtmlGenerator  {  
	CloseableHttpClient httpClient =null;
    HttpGet getMethod =null; //GetMethod实例  
    BufferedWriter fw = null;  
    String page = null;  
    String webappname = null;  
    BufferedReader br = null;  
    InputStream in = null;  
    StringBuffer sb = null;  
    String line = null;   
    //构造方法  
    public HtmlGenerator(String webappname){  
        this.webappname = webappname;  
          
    }  
      
    /** 根据模版及参数产生静态页面 */  
    public boolean createHtmlPage(String url,String htmlFileName){  
        boolean status = false;   
        int statusCode = 0;  
        CloseableHttpResponse response = null;
        try{  
            //创建一个HttpClient实例充当模拟浏览器  
        	CloseableHttpClient httpClient = HttpClients.createDefault();
        	HttpGet httpGet=new HttpGet("http://www.baidu.com");//HTTP Get请求(POST雷同)
        	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
        	httpGet.setConfig(requestConfig);
        	response = httpClient.execute(httpGet);//执行请求
        	statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200){
            	System.out.println("静态页面引擎在解析"+url+"产生静态页面"+htmlFileName+"时出错!");
            }else{  
                //读取解析结果  
                sb = new StringBuffer();  
                HttpEntity entry = response.getEntity();
                String  Stringresponse = EntityUtils.toString(entry,"utf-8");
                EntityUtils.consume(entry);
                page = formatPage(Stringresponse);
                //将解析结果写入指定的静态HTML文件中，实现静态HTML生成  
                writeHtml(htmlFileName,page);  
                status = true;  
            }             
        }catch(Exception ex){  
            System.out.println("静态页面引擎在解析"+url+"产生静态页面"+htmlFileName+"时出错:"+ex.getMessage());           
        }finally{
        	try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return status;  
    }  
      
    //将解析结果写入指定的静态HTML文件中  
    private synchronized void writeHtml(String htmlFileName,String content) throws Exception{  
        fw = new BufferedWriter(new FileWriter(htmlFileName));  
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(htmlFileName),"UTF-8");  
        fw.write(page);   
        if(fw!=null)fw.close();       
    }  
      
    //将页面中的相对路径替换成绝对路径，以确保页面资源正常访问  
    private String formatPage(String page){       
        page = page.replaceAll("\\.\\./\\.\\./\\.\\./", webappname+"/");  
        page = page.replaceAll("\\.\\./\\.\\./", webappname+"/");  
        page = page.replaceAll("\\.\\./", webappname+"/");            
        return page;  
    }  
      
    //测试方法  
    public static void main(String[] args){  
        HtmlGenerator h = new HtmlGenerator("webappname");  
        h.createHtmlPage("http://www.baidu.com","d:/a.html");  
        System.out.println("静态页面已经生成到d:/a.html");  
          
    }  

}  