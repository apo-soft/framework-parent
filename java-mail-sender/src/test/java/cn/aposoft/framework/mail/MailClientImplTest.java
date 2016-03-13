/**
 * 
 */
package cn.aposoft.framework.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.junit.Assert;
import org.junit.Test;

/**
 * 邮件客户端实现测试类
 * 
 * @author LiuJian
 *
 */
public class MailClientImplTest {
	private final String host = "smtp.163.com";
	private final String pass = "a654321b";
	private final String userName = "apotestmail@163.com";
	private final MailClient client = initClient();

	private final MailContact createMailFrom() {
		MailContactImpl from = new MailContactImpl();
		from.setName("刘健");
		from.setEmailAddress(userName);
		return from;
	}

	private final MailRecipient createMailRecipient() {
		MailRecipientImpl to = new MailRecipientImpl();
		to.setName("高俊龙");
		to.setEmailAddress("pleasantboy@qq.com");
		return to;
	}

	// 创建
	private MailClient initClient() {

		final boolean debug = true;
		final boolean useSsl = true;
		final SessionConfig config = SessionConfigBuilder.create(host).setAuthPassword(pass).setAuthUserName(userName)
				.setDebug(debug).setMailSmptAuth(true).setUseSsl(useSsl).build();
		Session session = MailSessionFactory.getSession(config);
		MailClient client = new MailClientImpl(session);
		return client;
	}

	@Test
	public void testSendSimpleMail() {

		MailMessage message = new MailMessage();
		message.setSubject("定制化测试邮件");
		message.setContent("<div>简单的测试邮件内容 <br/>今天是星期日,请注意明日上班.</div>");
		try {
			client.send(createMailFrom(), createMailRecipient(), "简单测试邮件",
					"<div>简单的测试邮件内容 <br/>今天是星期日,请注意明日上班. 另注意身体健康</div>");
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void testSendSimpleMessageMail() {

		MailMessage message = new MailMessage();
		message.setSubject("定制化测试邮件");
		message.setContent("<div>简单的测试邮件内容 <br/>今天是星期日,请注意明日上班.</div>");
		try {
			client.send(createMailFrom(), createMailRecipient(), message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
