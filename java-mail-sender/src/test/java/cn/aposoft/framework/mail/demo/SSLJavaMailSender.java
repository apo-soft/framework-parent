/**
 * 
 */
package cn.aposoft.framework.mail.demo;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import cn.aposoft.framework.mail.PasswordAuthenticator;

/**
 * @author LiuJian
 *
 */
public class SSLJavaMailSender {

	@SuppressWarnings("restriction")
	public static void main(String[] args) throws AddressException, MessagingException, UnsupportedEncodingException {
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		// Get a Properties object
		Properties props = System.getProperties();

		props.setProperty("mail.smtp.host", "smtp.163.com");

		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);

		props.setProperty("mail.smtp.socketFactory.fallback", "false");

		// 465 也是网易的ssl邮箱发布地址默认端口
		props.setProperty("mail.smtp.port", "465");

		props.setProperty("mail.smtp.socketFactory.port", "465");

		props.put("mail.smtp.auth", "true");
		final String username = "apotestmail@163.com";

		final String password = "a654321b";
		props.put("mail.sender.username", username);
		props.put("mail.sender.password", password);
		// 必须使用 password 提供 userName,password,默认的方法返回null
		Authenticator authenticator = new PasswordAuthenticator(username, password);
		Session session = Session.getDefaultInstance(props, authenticator);
		// Session session = Session.getDefaultInstance(props);

		// -- Create a new message --

		Message msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --

		msg.setFrom(new InternetAddress(MimeUtility.encodeWord("刘健") + "<" + username + ">"));

		InternetAddress[] paramArrayOfAddress = InternetAddress
				.parse(MimeUtility.encodeWord("刘亚") + "<pleasantboy@qq.com>");

		msg.setRecipients(Message.RecipientType.TO, paramArrayOfAddress);

		msg.setSubject("这是一封测试邮件");

		msg.setText("本邮件的内容仅用于测试使用");

		msg.setSentDate(new Date());

		Transport.send(msg);

		System.out.println("Message sent.");
	}

}
