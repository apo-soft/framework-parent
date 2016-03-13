/**
 * 
 */
package cn.aposoft.framework.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
		to.setEmailAddress("5889856@qq.com");
		return to;
	}

	private final MailRecipient createAnotherMailRecipient() {
		MailRecipientImpl to = new MailRecipientImpl();
		to.setName("高俊龙");
		to.setEmailAddress("1293485767@qq.com");
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

	@Test
	public void testSendTwoPersonSimpleMessageMail() {

		MailMessage message = new MailMessage();
		message.setSubject("双接收人,测试邮件");
		message.setContent("<div>简单的测试邮件内容 <br/>今天是星期日,请注意明日上班.</div>");
		List<MailRecipient> recipients = new ArrayList<MailRecipient>(2);
		recipients.add(createMailRecipient());
		recipients.add(createAnotherMailRecipient());
		try {
			client.send(createMailFrom(), recipients, message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * 可以连续发送两个附件的样例
	 */
	@Test
	public void testSendAttachmentMessageMail() {

		MailMessage message = new MailMessage();
		message.setSubject("附件测试邮件");
		message.setContent(
				"<!DOCTYPE HTML><html><head><meta charset=\"utf-8\"><title>带附件的测试邮件</title></head><body><div>内容详见附件.简单的测试邮件内容 <br/>今天是星期日,请注意明日上班.</div></body></html>");
		byte[] bytes;
		try {
			bytes = "<!DOCTYPE HTML><html><head><meta charset=\"utf-8\"><title>测试附件正文</title></head><body><div>InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件InputStream测试附件测试邮件简单的测试邮件内容 <br/>今天是星期日,请注意明日上班.</div></body></html>"
					.getBytes("UTF-8");
			message.addAttachments("notice.html", "text/html;charset=UTF-8", bytes);
			message.addAttachments("notice.html", "text/html;charset=UTF-8", bytes);
			client.send(createMailFrom(), createMailRecipient(), message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * 可以连续发送InputStream附件的样例
	 */
	@Test
	public void testSendInputStreamAttachmentMessageMail() {

		MailMessage message = new MailMessage();
		message.setSubject("InputStream附件测试邮件");
		message.setContent(
				"<!DOCTYPE HTML><html><head><meta charset=\"utf-8\"><title>InputStream附件的测试邮件</title></head><body><div>内容详见附件.简单的测试邮件内容 <br/>今天是星期日,请注意明日上班.</div></body></html>");
		byte[] bytes;
		try {
			bytes = "<!DOCTYPE HTML><html><head><meta charset=\"utf-8\"><title>InputStream测试附件测试邮件</title></head><body><div>简单的测试邮件内容 <br/>今天是星期日,请注意明日上班.</div></body></html>"
					.getBytes("UTF-8");
			InputStream inputStream = new ByteArrayInputStream(bytes);
			message.addAttachments("noticeInputStream.html", "text/html;charset=UTF-8", inputStream);
			client.send(createMailFrom(), createMailRecipient(), message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
			Assert.fail();
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
