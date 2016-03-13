/**
 * 
 */
package cn.aposoft.framework.mail;

import java.util.Properties;

/**
 * 邮件服务器的会话Session的配置信息
 * 
 * @author LiuJian
 *
 */
public class SessionConfig {
	// debug 信息
	private boolean debug = false;
	// 是否使用SSL连接
	private boolean useSsl = false;
	// 邮件服务器地址
	private String mailSmtpHost;

	// // 端口号配置设置
	// 邮件服务器端口号 默认为 25,如果为SSL,默认端口应为465
	private int mailSmtpPort = 25;

	// 是否需要授权验证,默认为true
	private boolean mailSmptAuth = true;
	// 验证用户名
	private String authUserName;
	// 验证密码
	private String authPassword;

	/**
	 * 是否打印调试信息
	 * 
	 * @return
	 */
	public boolean isDebug() {
		return debug;
	}

	/**
	 * 设置是否打印调试信息
	 * 
	 * @param debug
	 */
	void setDebug(boolean debug) {
		this.debug = debug;
	}

	/**
	 * 是否使用ssl加密连接
	 * 
	 * @return true/false
	 */
	public boolean isUseSsl() {
		return useSsl;
	}

	/**
	 * 设置是否使用ssl加密连接
	 * 
	 * @param useSsl
	 *            true/false
	 */
	void setUseSsl(boolean useSsl) {
		this.useSsl = useSsl;
	}

	/**
	 * 邮件发送服务器地址
	 * 
	 * @return 服务器地址字符串,可以是IP,也可以是主机域名
	 */
	public String getMailSmtpHost() {
		return mailSmtpHost;
	}

	/**
	 * 设置邮件发送服务器地址
	 * 
	 * @param mailSmtpHost
	 *            邮件服务器域名或者IP信息
	 */
	void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	/**
	 * 发送邮件服务器接收端口
	 * 
	 * @return 端口号
	 */
	public int getMailSmtpPort() {
		return mailSmtpPort;
	}

	/**
	 * 设置邮件服务器端口号 1~~65535
	 * 
	 * @param mailSmtpPort
	 *            邮件发送服务器接收端口号
	 */
	void setMailSmtpPort(int mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
	}

	/**
	 * 是否使用授权验证 默认为是
	 * 
	 * @return true/false
	 */
	public boolean isMailSmptAuth() {
		return mailSmptAuth;
	}

	/**
	 * 设置是否使用授权验证
	 * 
	 * @param mailSmptAuth
	 *            true/false
	 */
	void setMailSmptAuth(boolean mailSmptAuth) {
		this.mailSmptAuth = mailSmptAuth;
	}

	/**
	 * 登录邮件服务器用户的用户名根据服务器的不同,可能规则有所不同,要根据具体的服务器来判断如何使用
	 * <p>
	 * e.g.
	 * 
	 * <pre>
	 * 网易163服务器smtp.163.com可以使用如下验证方式
	 * apotestmail@163.com  或 apotestmail
	 * </pre>
	 * 
	 * @return 发送给邮件服务器的验证用户名
	 */
	public String getAuthUserName() {
		return authUserName;
	}

	/**
	 * 设置验证用户名
	 * 
	 * @param authUserName
	 *            e.g. 网易163服务器smtp.163.com可以使用如下验证方式<br/>
	 *            apotestmail@163.com 或 apotestmail
	 * 
	 */
	void setAuthUserName(String authUserName) {
		this.authUserName = authUserName;
	}

	/**
	 * 发送服务器登录验证密码
	 * 
	 * @return 密码信息
	 */
	public String getAuthPassword() {
		return authPassword;
	}

	/**
	 * 设置发送服务器的登录密码
	 * 
	 * @param authPassword
	 *            发送服务器的登录密码
	 */
	void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	/**
	 * 返回 config的Properties表示形式
	 * 
	 * @return SessionConfig的 Properties的表示形式
	 */
	public Properties getProperties() {
		Properties props = new Properties();
		props.setProperty("mail.debug", String.valueOf(debug));
		props.setProperty("mail.smtp.host", mailSmtpHost);
		props.setProperty("mail.smtp.port", String.valueOf(mailSmtpPort));
		props.setProperty("mail.smtp.auth", String.valueOf(mailSmptAuth));
		// 当使用ssl验证时,需要独立设置socketFactory的端口号
		if (useSsl) {
			props.setProperty("mail.smtp.socketFactory.port", String.valueOf(mailSmtpPort));
			
		}
		return props;

	}
}
