/**
 * 
 */
package cn.aposoft.framework.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.util.ByteArrayDataSource;

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
	private Collection<DataSource> attachments = new ArrayList<DataSource>();

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
	public Collection<DataSource> getAttachments() {
		return attachments;
	}

	/**
	 * 邮件附件
	 */
	public void setAttachments(Collection<DataSource> attachments) {
		this.attachments = attachments;
	}

	/**
	 * 添加DataSource类型附件
	 * 
	 * @param attachment
	 *            待添加的附件
	 */
	public void addAttachments(DataSource attachment) {
		ensureAttachments();
		attachments.add(attachment);
	}

	/**
	 * 添加DataSource类型附件
	 * 
	 * @param attachment
	 *            待添加的附件
	 */
	public void addAttachments(File attachment) {
		if (attachment == null || !attachment.exists() || !attachment.isFile()) {
			throw new IllegalArgumentException("附件必须是有效的文件.");
		}
		ensureAttachments();
		attachments.add(new FileDataSource(attachment));
	}

	/**
	 * 添加DataSource类型附件
	 * 
	 * @param fileName
	 *            附件文件名
	 * @param type
	 *            附件mime类型
	 * @param attachment
	 *            待添加的附件
	 */
	public void addAttachments(String fileName, String type, byte[] attachment) {
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException("文件名不能为空.");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("mime type类型不能为空.");
		}
		if (attachment == null || attachment.length == 0) {
			throw new IllegalArgumentException("输入附件内容不能为空.");
		}
		ensureAttachments();
		ByteArrayDataSource dataSource = new ByteArrayDataSource(attachment, type);
		dataSource.setName(fileName);
		attachments.add(dataSource);
	}

	/**
	 * 添加DataSource类型附件
	 * 
	 * @param fileName
	 *            附件文件名
	 * @param type
	 *            附件mime类型
	 * @param attachment
	 *            待添加的附件
	 * @throws IOException
	 */
	public void addAttachments(String fileName, String type, InputStream attachment) throws IOException {
		if (fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException("文件名不能为空.");
		}
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("mime type类型不能为空.");
		}
		if (attachment == null) {
			throw new IllegalArgumentException("输入附件内容不能为空.");
		}
		ensureAttachments();
		ByteArrayDataSource dataSource = new ByteArrayDataSource(attachment, type);
		dataSource.setName(fileName);
		attachments.add(dataSource);
	}

	// 确保添加附件时,附件集合不为空
	private void ensureAttachments() {
		if (attachments == null) {
			attachments = new ArrayList<DataSource>();
		}
	}
}
