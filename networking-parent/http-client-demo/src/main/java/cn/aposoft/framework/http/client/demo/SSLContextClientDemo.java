/**
 * 
 */
package cn.aposoft.framework.http.client.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.ssl.SSLContexts;

/**
 * @author Jann Liu
 *
 */
@SuppressWarnings("deprecation")
public class SSLContextClientDemo {

	/**
	 * 
	 * 创建配置了双向证书认证的https请求客户端
	 * 
	 * @param config
	 *            带有商户id和证书位置的配置信息
	 * @return 添加微信支付商户安全认证信息的http请求客户端
	 * @throws Exception
	 *             配置种可能产生多种异常.
	 * @author Yujinshui
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @bugfix Jann Liu 2015/10/25 修改在client中被deprecated的方法,使用官方推荐的标准方法
	 */
	private static CloseableHttpClient getPkcs12SSLContextClient(String keyPath, String password)
			throws KeyStoreException, UnrecoverableKeyException, KeyManagementException, IOException,
			NoSuchAlgorithmException, CertificateException {

		try (BufferedInputStream instream = new BufferedInputStream(new FileInputStream(keyPath));) {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(instream, password.toCharArray());
			// Trust own CA and all self-signed certs
			// 私有key在微信中默认以mchId作为密钥
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
			CloseableHttpClient httpclient = HttpClients.custom().setMaxConnPerRoute(200).setMaxConnTotal(200)
					.setSSLSocketFactory(sslsf).build();
			return httpclient;
		} catch (KeyStoreException e) {
			e.printStackTrace();
			throw e;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw e;
		} catch (CertificateException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (KeyManagementException e) {
			e.printStackTrace();
			throw e;
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CloseableHttpClient client = getPkcs12SSLContextClient("", "12312");
			HttpParams params = client.getParams();
			if (params != null) {
				System.out.println(params.toString());
			}
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
