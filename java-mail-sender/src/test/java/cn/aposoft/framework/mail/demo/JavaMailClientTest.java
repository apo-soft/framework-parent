package cn.aposoft.framework.mail.demo;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;

import org.junit.Test;

import cn.aposoft.framework.mail.MailClient;
import cn.aposoft.framework.mail.MailClientImpl;
import cn.aposoft.framework.mail.MailContact;
import cn.aposoft.framework.mail.MailContactImpl;
import cn.aposoft.framework.mail.MailMessage;
import cn.aposoft.framework.mail.MailRecipient;
import cn.aposoft.framework.mail.MailRecipientImpl;
import cn.aposoft.framework.mail.MailSessionFactory;
import cn.aposoft.framework.mail.SessionConfig;

public class JavaMailClientTest {
	@Test
	public void JavaMailClientTest() throws AddressException, UnsupportedEncodingException, MessagingException{
		
		Session session = MailSessionFactory.getSession(new SessionConfig());
		MailClient client = new MailClientImpl(session);
		MailContactImpl from = new MailContactImpl();
		from.setEmailAddress("947422186@qq.com");
		from.setName("gao0");
		MailRecipientImpl tos = new MailRecipientImpl();
		tos.setEmailAddress("1293485767@qq.com");
		tos.setName("gao2");
		MailMessage msg = new MailMessage();
		msg.setContent("hello");
		client.send(from, tos, msg);
	}
}
