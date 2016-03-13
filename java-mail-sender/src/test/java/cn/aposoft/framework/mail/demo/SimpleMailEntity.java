/**
 * 
 */
package cn.aposoft.framework.mail.demo;

/**
 * 简单邮件报文对象
 * 
 * @author LiuJian
 *
 */
public class SimpleMailEntity {
	// 邮件标题
	private String title;
	// 接收人姓名
	private String receiver;
	// 发送人姓名
	private String sender;
	// 发送日期
	private String date;
	// 邮件正文
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
