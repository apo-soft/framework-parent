/**
 * 
 */
package cn.aposoft.framework.mail;

import java.security.Security;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;

/**
 * 邮件会话工厂 提供SSL和明文的会话session的统一构建接口 <br />
 * 根据入参的userSsl返回安全会话报文或是明文会话报文
 * 
 * @author LiuJian
 *
 */
public class MailSessionFactory {
	/**
	 * 统一获取mailSession的方法,未加密Session,或SslSession都可以统一获取
	 * 
	 * @param config
	 *            用户获取Session的配置信息
	 * @return 可用于发送邮件会话的Session
	 */
	public static Session getSession(SessionConfig config) {
		checkConfig(config);
		SessionFactory factory = getSessionFactory(config.isUseSsl());
		return factory.getSession(config);
	}

	private static volatile SessionFactory sslFactory;
	private static volatile SessionFactory normalFactory;

	private static SessionFactory getSessionFactory(boolean useSsl) {
		return useSsl ? getSslFactory() : getNormalFactory();
	}

	/*
	 * 构建明文工厂(懒加载)
	 * 
	 * @return 会话工厂
	 */
	private static SessionFactory getNormalFactory() {
		if (normalFactory == null) {
			synchronized (MailSessionFactory.class) {
				if (normalFactory == null) {
					normalFactory = new NormalSessionFactory();
				}
			}
		}
		return normalFactory;
	}

	/*
	 * 构建SslFactory 懒加载
	 * 
	 * @return 会话工厂
	 */
	private static SessionFactory getSslFactory() {
		if (sslFactory == null) {
			synchronized (MailSessionFactory.class) {
				if (sslFactory == null) {
					sslFactory = new SslSessionFactory();
				}
			}
		}
		return sslFactory;
	}

	private static void checkConfig(SessionConfig config) {
		if (config == null) {
			throw new IllegalArgumentException("配置信息不能为空.");
		}
		if (config.getMailSmtpHost() == null || config.getMailSmtpHost().isEmpty()) {
			throw new IllegalArgumentException("邮件服务器地址不能为空.");
		}
		if (config.getMailSmtpPort() <= 0 || config.getMailSmtpPort() > 65535) {
			throw new IllegalArgumentException("端口号必须在1~~65535范围内.");
		}
		if (config.isMailSmptAuth()) {
			if (config.getAuthUserName() == null || config.getAuthUserName().isEmpty()) {
				throw new IllegalArgumentException("验证用户名不能为空.");
			}
			if (config.getAuthPassword() == null || config.getAuthPassword().isEmpty()) {
				throw new IllegalArgumentException("验证用户密码不能为空.");
			}
		}
	}

	private interface SessionFactory {
		Session getSession(SessionConfig config);
	}

	@SuppressWarnings("restriction")
	private static class SslSessionFactory implements SessionFactory {
		static {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		}

		@Override
		public Session getSession(SessionConfig config) {
			Authenticator authenticator = new PasswordAuthenticator(config.getAuthUserName(), config.getAuthPassword());
			Properties props = config.getProperties();
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			Session session = Session.getInstance(props, authenticator);
			return session;
		}
	}

	private static class NormalSessionFactory implements SessionFactory {
		@Override
		public Session getSession(SessionConfig config) {
			Authenticator authenticator = new PasswordAuthenticator(config.getAuthUserName(), config.getAuthPassword());
			Session session = Session.getInstance(config.getProperties(), authenticator);
			return session;
		}
	}
}
