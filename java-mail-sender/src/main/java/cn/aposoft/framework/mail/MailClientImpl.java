package cn.aposoft.framework.mail;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * 
 * @author LiuJian
 *
 */
public class MailClientImpl implements MailClient {
	/**
	 * 创建联系人(发送人,接收人Address的方法
	 * 
	 * @param contact
	 *            联系人信息
	 * @return 联系人邮件地址形式
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	private InternetAddress createAddress(MailContact contact) throws AddressException, UnsupportedEncodingException {
		if (contact == null) {
			throw new IllegalArgumentException("contact must not be null.");
		}
		if (contact.getEmailAddress() == null || contact.getEmailAddress().isEmpty()) {
			throw new IllegalArgumentException("email address must not be null.");
		}
		if (contact.getName() == null || contact.getName().isEmpty()) {
			return new InternetAddress(contact.getEmailAddress());
		} else {
			return new InternetAddress(
					MimeUtility.encodeWord(contact.getName()) + " <" + contact.getEmailAddress() + ">");
		}
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
	public void send(MailContact from, Collection<MailRecipient> tos, MailMessage message)
			throws UnsupportedEncodingException, MessagingException {
		send(from, tos, null, message);
	}

	@Override
	public void send(MailContact from, Collection<MailRecipient> tos, Collection<MailRecipient> ccs,
			MailMessage message) throws UnsupportedEncodingException, MessagingException {
		send(from, tos, ccs, null, message);
	}

	@Override
	public void send(MailContact from, Collection<MailRecipient> tos, Collection<MailRecipient> ccs,
			Collection<MailRecipient> bccs, MailMessage message)
			throws MessagingException, UnsupportedEncodingException {
		Message msg = new MimeMessage(session);
		msg.setFrom(createAddress(from));
		if (tos != null) {
			for (MailRecipient recipient : tos) {
				msg.addRecipient(RecipientType.TO, createAddress(recipient));
			}
		}
		if (ccs != null) {
			for (MailRecipient recipient : ccs) {
				msg.addRecipient(RecipientType.CC, createAddress(recipient));
			}
		}
		if (bccs != null) {
			for (MailRecipient recipient : bccs) {
				msg.addRecipient(RecipientType.BCC, createAddress(recipient));
			}
		}

		msg.setSubject(message.getSubject());
		String type = createType(message);
		msg.setContent(message.getContent(), type);
		Transport.send(msg);
	}

	private String createType(MailMessage message) {
		String s = message.getContentType();
		if (message.getCharset() != null) {
			s += ";charset=" + message.getCharset().name();
		}
		return s;
	}

}
