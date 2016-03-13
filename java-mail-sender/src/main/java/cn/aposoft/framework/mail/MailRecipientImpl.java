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
public class MailRecipientImpl extends MailContactImpl implements MailRecipient {

	private RecipientType type = RecipientType.TO;

	public void setType(RecipientType type) {
		this.type = type;
	}

	@Override
	public RecipientType getType() {
		return type;
	}

}
