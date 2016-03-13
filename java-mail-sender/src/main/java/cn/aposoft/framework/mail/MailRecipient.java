/**
 * 
 */
package cn.aposoft.framework.mail;

import javax.mail.Message.RecipientType;

/**
 * @author LiuJian
 *
 */
public interface MailRecipient extends MailContact {
	RecipientType getType();
}
