/**
 * 
 */
package cn.aposoft.framework.mail;

import java.util.Collection;

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
	 */
	void send(MailContact from, MailReceiver to, String subject, String content);

	/**
	 * 以UTF-8的格式发送明文邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容
	 */
	void send(MailContact from, MailReceiver to, MailMessage message);

	/**
	 * 以UTF-8的格式发送明文邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容
	 */
	void send(MailContact from, Collection<MailReceiver> tos, MailMessage message);

	/**
	 * 以UTF-8的格式发送明文邮件
	 * 
	 * @param from
	 *            发送人信息
	 * @param to
	 *            接收人信息
	 * @param message
	 *            邮件报文内容
	 */
	void send(MailContact from, Collection<MailReceiver> tos, Collection<MailReceiver> ccs, MailMessage message);

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
	 */
	void send(MailContact from, Collection<MailReceiver> tos, Collection<MailReceiver> ccs,
			Collection<MailReceiver> scs, MailMessage message);

}
