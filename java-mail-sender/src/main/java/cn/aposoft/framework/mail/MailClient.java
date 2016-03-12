/**
 * 
 */
package cn.aposoft.framework.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

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
	private String mailHost;
	private String senderUsername;
	private String senderPassword;

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public String getSenderUsername() {
		return senderUsername;
	}

	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}

	public String getSenderPassword() {
		return senderPassword;
	}

	public void setSenderPassword(String senderPassword) {
		this.senderPassword = senderPassword;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	private boolean debug = false;

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

	public MailClient(Properties properties) {
		this.mailHost = properties.getProperty("mail.smtp.host");
		this.senderUsername = properties.getProperty("mail.sender.username");
		this.senderPassword = properties.getProperty("mail.sender.password");
		this.debug = "true".equals(properties.getProperty("mail.debug")) ? true : false;
		session = Session.getInstance(properties);
		session.setDebug(debug);// 开启后有调试信息
	}

}