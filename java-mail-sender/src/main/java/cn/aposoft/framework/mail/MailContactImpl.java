/**
 * 
 */
package cn.aposoft.framework.mail;

/**
 * 邮件联系人实现类
 * 
 * @author LiuJian
 *
 */
public class MailContactImpl implements MailContact {
	// 称呼
	private String name;
	// 邮件地址
	private String emailAddress;

	/**
	 * 获取称呼
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置称呼
	 * 
	 * @param name
	 *            待设置的称呼
	 */
	public MailContactImpl setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 读取邮件地址
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 设置邮件地址
	 * 
	 * @param emailAddress
	 *            待设置的邮件地址
	 */
	public MailContactImpl setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}

}
