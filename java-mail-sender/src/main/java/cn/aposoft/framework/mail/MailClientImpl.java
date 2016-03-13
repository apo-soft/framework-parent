package cn.aposoft.framework.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * MailClient 的默认实现类
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

	/**
	 * 以UTF-8 + html的形式发送邮件
	 * 
	 * @param from
	 *            发件人信息
	 * @param to
	 *            收件人信息
	 * @param subject
	 *            邮件标题
	 * @param content
	 *            邮件正文
	 */
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

	/**
	 * 以UTF-8 + html的形式发送邮件
	 * 
	 * @param from
	 *            发件人信息
	 * @param to
	 *            收件人信息
	 * @param subject
	 *            邮件标题
	 * @param content
	 *            邮件正文
	 * @param charset
	 *            邮件正文编码格式
	 */
	@Override
	public void send(MailContact from, MailRecipient to, String subject, String content, String charset)
			throws AddressException, UnsupportedEncodingException, MessagingException {
		Message msg = new MimeMessage(session);
		msg.setFrom(createAddress(from));
		msg.setRecipient(to.getType(), createAddress(to));
		msg.setSubject(subject);
		msg.setContent(content, "text/html;charset=" + charset);
		Transport.send(msg);
	}

	@Override
	public void send(MailContact from, MailRecipient to, MailMessage message)
			throws AddressException, UnsupportedEncodingException, MessagingException {
		ArrayList<MailRecipient> recipientList = new ArrayList<MailRecipient>();
		recipientList.add(to);
		this.send(from, recipientList, null, null, message);
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
		// 设置发送人
		msg.setFrom(createAddress(from));
		// 设置接收人
		if (tos != null) {
			for (MailRecipient recipient : tos) {
				msg.addRecipient(RecipientType.TO, createAddress(recipient));
			}
		}
		// 设置抄送人
		if (ccs != null) {
			for (MailRecipient recipient : ccs) {
				msg.addRecipient(RecipientType.CC, createAddress(recipient));
			}
		}
		// 设置 密送人
		if (bccs != null) {
			for (MailRecipient recipient : bccs) {
				msg.addRecipient(RecipientType.BCC, createAddress(recipient));
			}
		}

		// 设置邮件主题
		msg.setSubject(message.getSubject());

		if (message.getAttachments() == null || message.getAttachments().isEmpty()) {
			String type = createType(message);
			msg.setContent(message.getContent(), type);
		} else {
			Multipart multipart = createMultiPartContent(message);
			msg.setContent(multipart);
		}

		msg.setSentDate(new Date());
		msg.saveChanges();
		Transport.send(msg);
	}

	// 创建Mime的ContentType信息
	private String createType(MailMessage message) {
		String s = message.getContentType();
		if (message.getCharset() != null) {
			s += ";charset=" + message.getCharset().name();
		}
		return s;
	}

	/**
	 * 添加附件信息
	 * 
	 * @param message
	 *            邮件消息
	 */
	private Multipart createMultiPartContent(MailMessage message)
			throws MessagingException, UnsupportedEncodingException {
		Multipart multipart = new MimeMultipart();
		// 添加邮件正文
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setContent(message.getContent(), createType(message));
		multipart.addBodyPart(contentPart);
		// 添加附件的内容
		for (DataSource attachment : message.getAttachments()) {
			BodyPart attachmentBodyPart = new MimeBodyPart();
			attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
			attachmentBodyPart.setDataHandler(new DataHandler(attachment));
			multipart.addBodyPart(attachmentBodyPart);
		}
		return multipart;
	}

}
