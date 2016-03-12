/**
 * 
 */
package cn.aposoft.framework.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import java.io.UnsupportedEncodingException;
import java.security.Security;

/**
 * @author LiuJian
 *
 */
public class SSLJavaMailSender {

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

		final String username = "******";

		final String password = "**********";

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		// -- Create a new message --

		Message msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --

		msg.setFrom(new InternetAddress(username + "@163.com"));

		InternetAddress[] paramArrayOfAddress = InternetAddress.parse(MimeUtility.encodeWord("刘健")
				+ "<pleasantboy@163.com>," + MimeUtility.encodeWord("刘亚") + "<pleasantboy@qq.com>");
		msg.setRecipients(Message.RecipientType.TO, paramArrayOfAddress);

		msg.setSubject("Hello");

		msg.setText("How are you");

		msg.setSentDate(new Date());

		Transport.send(msg);

		System.out.println("Message sent.");
	}

}
