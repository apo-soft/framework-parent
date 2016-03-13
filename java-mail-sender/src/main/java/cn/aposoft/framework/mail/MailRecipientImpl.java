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
	/**
	 * 创建联系人实例
	 * 
	 * @param name
	 *            联系人名称
	 * @param emailAddress
	 *            联系人邮件地址
	 * @return 联系人实例
	 */
	public static MailRecipient create(String name, String emailAddress) {
		return new MailRecipientImpl(name, emailAddress);
	}

	/**
	 * 创建联系人实例
	 * 
	 * @param name
	 *            联系人名称
	 * @param emailAddress
	 *            联系人邮件地址
	 * @return 联系人实例
	 */
	public static MailRecipient create(String name, String emailAddress, RecipientType type) {
		return new MailRecipientImpl(name, emailAddress, type);
	}

	public MailRecipientImpl() {

	}

	public MailRecipientImpl(String name, String emailAddress) {
		super(name, emailAddress);
	}

	public MailRecipientImpl(String name, String emailAddress, RecipientType type) {
		super(name, emailAddress);
		this.type = type;
	}

	private RecipientType type = RecipientType.TO;

	/**
	 * 设置接收人类型
	 * 
	 * @param type
	 *            {@link RecipientType}
	 * 
	 */
	public MailRecipientImpl setType(RecipientType type) {
		this.type = type;
		return this;
	}

	/**
	 * 读取接收人类型
	 */
	@Override
	public RecipientType getType() {
		return type;
	}

}
