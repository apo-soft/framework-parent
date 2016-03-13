/**
 * 
 */
package cn.aposoft.framework.mail.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Session;

/**
 * @author LiuJian
 *
 */
public class MailClient {
	/**
	 * Session类代表JavaMail中的一个邮件会话。
	 * 每一个基于JavaMail的应用程序至少有一个Session（可以有任意多的Session）。
	 * 
	 * JavaMail需要Properties来创建一个session对象。 寻找"mail.smtp.host" 属性值就是发送邮件的主机
	 * 寻找"mail.smtp.auth" 身份验证，目前免费邮件服务器都需要这一项
	 */
	private Session session;
	/**
	 * MailClient的配置信息
	 */
	private MailClientConfig config;

	private static Properties getProperties(String path) throws IOException {
		InputStream in = MailClient.class.getClassLoader().getResourceAsStream(path);
		Properties properties = new Properties();
		properties.load(in);
		return properties;
	}

	/**
	 * 初始化邮件发送客户端
	 * 
	 * @param path
	 *            配置属性路径相对于classpath
	 * @throws IOException
	 */
	public MailClient(String path) throws IOException {
		this(getProperties(path));
	}

	/**
	 * 
	 * @param properties
	 * @throws IOException
	 */
	public MailClient(Properties properties) throws IOException {
		MailClientConfig config = createMailClientConfig(properties);
		this.setConfig(config);
		setSession(Session.getInstance(properties));
	}

	private static MailClientConfig createMailClientConfig(Properties properties) {
		MailClientConfig config = new MailClientConfig();

		config.setMailHost(properties.getProperty("mail.smtp.host"));
		config.setPort(properties.getProperty("mail.smtp.port"));
		config.setSenderUsername(properties.getProperty("mail.sender.username"));
		config.setSenderPassword(properties.getProperty("mail.sender.password"));

		config.setDebug("true".equals(properties.getProperty("mail.debug")) ? true : false);
		return config;
	}

	public MailClient(MailClientConfig config) {
	}

	public MailClientConfig getConfig() {
		return config;
	}

	public void setConfig(MailClientConfig config) {
		this.config = config;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
