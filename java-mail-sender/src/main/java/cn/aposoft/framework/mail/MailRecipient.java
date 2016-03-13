/**
 * 
 */
package cn.aposoft.framework.mail;

import javax.mail.Message.RecipientType;

/**
 * 邮件接收人信息
 * 
 * @author LiuJian
 *
 */
public interface MailRecipient extends MailContact {
	/**
	 * 获取接收人的类型
	 * 
	 * @see RecipientType
	 * @return
	 */
	RecipientType getType();
}
