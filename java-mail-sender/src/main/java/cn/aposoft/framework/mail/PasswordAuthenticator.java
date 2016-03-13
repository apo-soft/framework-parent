/**
 * 
 */
package cn.aposoft.framework.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 继承自Authenticator,用于身份验证,用于使用userName + password 进行身份验证 用于SSL加密发送邮件时使用
 * 
 * @author LiuJian
 * @version 1.0
 */
public class PasswordAuthenticator extends Authenticator {

	private String userName;
	private String password;

	public PasswordAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

}
