/**
 * 
 */
package cn.aposoft.framework.mail;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author LiuJian
 *
 */
public interface MailClient {

	/**
	 * 以UTF-8的格式发送明文邮件
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

	void send(MailContact from, MailRecipient to, String subject, String content)
			throws AddressException, UnsupportedEncodingException, MessagingException;

	/**
	 * 以UTF-8的格式发送明文邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * @throws AddressException 
	 */
	void send(MailContact from, MailRecipient to, MailMessage message) throws AddressException, UnsupportedEncodingException, MessagingException;

	/**
	 * 以UTF-8的格式发送明文邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */

	void send(MailContact from, Collection<MailRecipient> tos, MailMessage message) throws UnsupportedEncodingException, MessagingException;

	/**
	 * 以UTF-8的格式发送明文邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */

	void send(MailContact from, Collection<MailRecipient> tos, Collection<MailRecipient> ccs, MailMessage message) throws UnsupportedEncodingException, MessagingException;

	/**
	 * 以UTF-8的格式发送明文邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param tos
	 *            接收人信息
	 * @param ccs
	 *            抄送人
	 * @param scs
	 *            密送人
	 * @param message
	 *            邮件报文内容
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	void send(MailContact from, Collection<MailRecipient> tos, Collection<MailRecipient> ccs,

			Collection<MailRecipient> scs, MailMessage message) throws MessagingException, UnsupportedEncodingException;

}
