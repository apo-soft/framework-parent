/**
 * 
 */
package cn.aposoft.framework.mail;

/**
 * 邮件联系人信息
 * 
 * @author LiuJian
 *
 */
public interface MailContact {
	/**
	 * 发件人或收件人的称呼 默认为空
	 * 
	 * @return 人的称呼
	 */
	String getName();

	/**
	 * 邮件地址
	 * 
	 * @return 发送人或接收人的邮件地址
	 */
	String getEmailAddress();

}
