/**
 * 
 */
package cn.aposoft.framework.mail;

/**
 * 会话配置构造器
 * 
 * @author LiuJian
 *
 */
public class SessionConfigBuilder implements Builder<SessionConfig> {
	// 加密的端口号
	private static final int SSL_SMTP_PORT = 465;
	// 常规的端口号
	private static final int NORMAL_SMTP_PORT = 25;
	// debug 信息
	private boolean debug = false;
	// 是否使用SSL连接
	private boolean useSsl = false;
	// 邮件服务器地址
	private String mailSmtpHost;
	// // 端口号配置设置
	private boolean portChanged = false;
	// 邮件服务器端口号 默认为 25,如果为SSL,默认端口应为465
	private int mailSmtpPort = 25;
	// 是否需要授权验证,默认为true
	private boolean mailSmptAuth = true;
	// 验证用户名
	private String authUserName;
	// 验证密码
	private String authPassword;

	/**
	 * 私有构造函数
	 * 
	 * @param mailSmtpHost
	 *            发送邮箱服务器地址
	 */
	private SessionConfigBuilder(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}

	public static final SessionConfigBuilder create(String mailSmtpHost) {
		SessionConfigBuilder builder = new SessionConfigBuilder(mailSmtpHost);
		return builder;
	}

	// 返回smtp协议的端口号: 非加密默认为 25, 加密默认为465
	private int getDefaultPort() {
		return useSsl ? SSL_SMTP_PORT : NORMAL_SMTP_PORT;
	}

	@Override
	public SessionConfig build() {
		SessionConfig config = new SessionConfig();
		config.setDebug(debug);
		config.setUseSsl(useSsl);
		config.setAuthPassword(authPassword);
		config.setAuthUserName(authUserName);
		config.setMailSmptAuth(mailSmptAuth);
		config.setMailSmtpHost(mailSmtpHost);
		config.setMailSmtpPort(portChanged ? mailSmtpPort : getDefaultPort());
		return config;
	}

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
	public SessionConfigBuilder setDebug(boolean debug) {
		this.debug = debug;
		return this;
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
	public SessionConfigBuilder setUseSsl(boolean useSsl) {
		this.useSsl = useSsl;
		return this;
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
	public SessionConfigBuilder setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
		return this;
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
	public SessionConfigBuilder setMailSmtpPort(int mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
		this.portChanged = true;
		return this;
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
	public SessionConfigBuilder setMailSmptAuth(boolean mailSmptAuth) {
		this.mailSmptAuth = mailSmptAuth;
		return this;
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
	public SessionConfigBuilder setAuthUserName(String authUserName) {
		this.authUserName = authUserName;
		return this;
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
	public SessionConfigBuilder setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
		return this;
	}
}
