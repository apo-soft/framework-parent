/**
 * 
 */
package cn.aposoft.framework.mail;

import java.nio.charset.Charset;

/**
 * 邮件报文正文
 * 
 * @author LiuJian
 *
 */
public class MailContent {
	/**
	 * 正文字符集编码
	 */
	private Charset charset;
	/**
	 * 正文格式 text/html text/plain mime类型
	 */
	private String contentType;
	/**
	 * 正文内容
	 */
	private String content;

	/**
	 * 正文字符集编码
	 */
	public Charset getCharset() {
		return charset;
	}

	/**
	 * 正文字符集编码
	 */
	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	/**
	 * 正文格式 text/html text/plain mime类型
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * 正文格式 text/html text/plain mime类型
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 正文内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 正文内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
