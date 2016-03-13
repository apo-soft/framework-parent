/**
 * 
 */
package cn.aposoft.framework.mail;

import java.io.File;
import java.util.Collection;

/**
 * 邮件消息:包括邮件标题,正文,附件
 * 
 * @author LiuJian
 *
 */
public class MailMessage extends MailContent {
	// 邮件标题
	private String subject;
	// 邮件附件
	private Collection<File> attachments;

	/**
	 * 邮件标题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 邮件标题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 邮件附件
	 */
	public Collection<File> getAttachments() {
		return attachments;
	}

	/**
	 * 邮件附件
	 */
	public void setAttachments(Collection<File> attachments) {
		this.attachments = attachments;
	}

}
