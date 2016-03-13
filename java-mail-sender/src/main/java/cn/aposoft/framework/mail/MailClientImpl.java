package cn.aposoft.framework.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

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
	private static final String DEFAULT_TYPE = "text/html";
	private static final String MAIL_ADDRESS_REGEX = "^[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*"
			+ "@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?$";
	private Session session;

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
		// 验证邮箱地址合法性
		if (!Pattern.matches(MAIL_ADDRESS_REGEX, contact.getEmailAddress())) {
			throw new IllegalArgumentException("email address is not legal.");
		}
		if (contact.getName() == null || contact.getName().isEmpty()) {
			return new InternetAddress(contact.getEmailAddress());
		} else {
			return new InternetAddress(
					MimeUtility.encodeWord(contact.getName()) + " <" + contact.getEmailAddress() + ">");
		}
	}

	// 创建Mime的ContentType信息
	private String createType(MailMessage message) {
		String s = message.getContentType() == null ? DEFAULT_TYPE : message.getContentType();
		if (message.getCharset() != null) {
			s += ";charset=" + message.getCharset().name();
		} else {
			s += ";charset=UTF-8";
		}
		return s;
	}

	/**
	 * 创建MultiPart类型的报文正文信息
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

	public MailClientImpl(Session session) {
		this.session = session;
	}

	/**
	 * 以UTF-8编码发送html格式邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param content
	 *            邮件正文
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * @throws AddressException
	 */
	@Override
	public void send(MailContact from, MailRecipient to, String subject, String content)
			throws AddressException, UnsupportedEncodingException, MessagingException {
		send(from, to, subject, content, "UTF-8");
	}

	/**
	 * 发送html格式邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param content
	 *            邮件正文
	 * @param charset
	 *            邮件正文编码格式
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * @throws AddressException
	 */
	@Override
	public void send(MailContact from, MailRecipient to, String subject, String content, String charset)
			throws AddressException, UnsupportedEncodingException, MessagingException {
		Message msg = new MimeMessage(session);
		msg.setFrom(createAddress(from));
		msg.setRecipient(to.getType(), createAddress(to));
		msg.setSubject(subject);
		msg.setContent(content, "text/html;charset=" + charset);
		msg.saveChanges();
		Transport.send(msg);
	}

	/**
	 * 简单发送邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容 {@link MailMessage}
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 * @throws AddressException
	 */
	@Override
	public void send(MailContact from, MailRecipient to, MailMessage message)
			throws AddressException, UnsupportedEncodingException, MessagingException {
		ArrayList<MailRecipient> recipientList = new ArrayList<MailRecipient>();
		recipientList.add(to);
		this.send(from, recipientList, null, null, message);
	}

	/**
	 * 简单发送邮件,可以设置多个收件人TO
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容 {@link MailMessage}
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public void send(MailContact from, Collection<MailRecipient> tos, MailMessage message)
			throws UnsupportedEncodingException, MessagingException {
		send(from, tos, null, message);
	}

	/**
	 * 简单发送邮件,可以设置多个收件人TO,多个抄送人CC
	 * 
	 * @param from
	 *            发送人信息
	 * @param tos
	 *            接收人信息
	 * @param ccs
	 *            抄送人信息
	 * @param message
	 *            邮件报文内容 {@link MailMessage}
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public void send(MailContact from, Collection<MailRecipient> tos, Collection<MailRecipient> ccs,
			MailMessage message) throws UnsupportedEncodingException, MessagingException {
		send(from, tos, ccs, null, message);
	}

	/**
	 * 简单发送邮件,可以设置多个收件人TO,多个抄送人CC,多个密送人BCC
	 * 
	 * @param from
	 *            发送人信息
	 * @param tos
	 *            接收人信息
	 * @param ccs
	 *            抄送人信息
	 * @param scs
	 *            密送人信息
	 * @param message
	 *            邮件报文内容 {@link MailMessage}
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
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

}
