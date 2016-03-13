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

	/**
	 * 设置接收人类型
	 * 
	 * @param type
	 *            {@link RecipientType}
	 * 
	 */
	public void setType(RecipientType type) {
		this.type = type;
	}

	/**
	 * 读取接收人类型
	 */
	@Override
	public RecipientType getType() {
		return type;
	}

}
