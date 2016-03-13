package cn.aposoft.framework.mail;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MailClientImpl implements MailClient {

	private InternetAddress createAddress(MailContact contact) throws AddressException, UnsupportedEncodingException {
		return new InternetAddress(MimeUtility.encodeWord(contact.getName()) + " <" + contact.getEmailAddress() + ">");
	}

	private Session session;

	public MailClientImpl(Session session) {
		this.session = session;
	}

	@Override
	public void send(MailContact from, MailRecipient to, String subject, String content)
			throws AddressException, UnsupportedEncodingException, MessagingException {
		Message msg = new MimeMessage(session);
		msg.setFrom(createAddress(from));
		msg.setRecipient(to.getType(), createAddress(to));
		msg.setSubject(subject);
		msg.setContent(content, "text/html;charset=UTF-8");
		Transport.send(msg);
	}

	@Override
	public void send(MailContact from, MailRecipient to, MailMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, Collection<MailRecipient> tos, MailMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, Collection<MailRecipient> tos, Collection<MailRecipient> ccs,
			MailMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, Collection<MailRecipient> tos, Collection<MailRecipient> ccs,
			Collection<MailRecipient> scs, MailMessage message) {
		// TODO Auto-generated method stub

	}

}
