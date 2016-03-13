/**
 * 
 */
package cn.aposoft.framework.mail;

import org.junit.Test;

import cn.aposoft.framework.mail.SessionConfig;
import cn.aposoft.framework.mail.SessionConfigBuilder;
import org.junit.Assert;

/**
 * 测试SessionConfigBuilder
 * 
 * @author LiuJian
 *
 */
public class SessionConfigBuilderTest {
	/**
	 * 测试普通邮件配置信息
	 */
	@Test
	public void testNormalConfig() {
		final String host = "smtp.163.com";
		final String pass = "a123456b";
		final String userName = "apotestmail@163.com";
		final boolean debug = true;
		final boolean useSsl = false;
		final SessionConfig config = SessionConfigBuilder.create(host).setAuthPassword(pass).setAuthUserName(userName)
				.setDebug(debug).setMailSmptAuth(true).setUseSsl(useSsl).build();
		Assert.assertEquals(host, config.getMailSmtpHost());
		Assert.assertEquals(pass, config.getAuthPassword());
		Assert.assertEquals(userName, config.getAuthUserName());
		Assert.assertTrue(config.isDebug());
		Assert.assertFalse(config.isUseSsl());
		Assert.assertEquals(25, config.getMailSmtpPort());
	}

	/**
	 * 测试普通邮件配置信息
	 */
	@Test
	public void testSslConfig() {
		final String host = "smtp.163.com";
		final String pass = "a123456b";
		final String userName = "apotestmail@163.com";
		final boolean debug = true;
		final boolean useSsl = true;
		final SessionConfig config = SessionConfigBuilder.create(host).setAuthPassword(pass).setAuthUserName(userName)
				.setDebug(debug).setMailSmptAuth(true).setUseSsl(useSsl).build();
		Assert.assertEquals(host, config.getMailSmtpHost());
		Assert.assertEquals(pass, config.getAuthPassword());
		Assert.assertEquals(userName, config.getAuthUserName());
		Assert.assertTrue(config.isDebug());
		Assert.assertTrue(config.isUseSsl());
		Assert.assertEquals(465, config.getMailSmtpPort());
	}

	/**
	 * 测试普通邮件配置信息
	 */
	@Test
	public void testCustomPortNormalConfig() {
		final String host = "smtp.163.com";
		final String pass = "a123456b";
		final String userName = "apotestmail@163.com";
		final boolean debug = true;
		final boolean useSsl = false;
		final int port = 355;
		final SessionConfig config = SessionConfigBuilder.create(host).setAuthPassword(pass).setAuthUserName(userName)
				.setDebug(debug).setMailSmptAuth(true).setUseSsl(useSsl).setMailSmtpPort(port).build();
		Assert.assertEquals(host, config.getMailSmtpHost());
		Assert.assertEquals(pass, config.getAuthPassword());
		Assert.assertEquals(userName, config.getAuthUserName());
		Assert.assertTrue(config.isDebug());
		Assert.assertFalse(config.isUseSsl());
		Assert.assertEquals(port, config.getMailSmtpPort());
	}

	/**
	 * 测试普通邮件配置信息
	 */
	@Test
	public void testCustomPortSslConfig() {
		final String host = "smtp.163.com";
		final String pass = "a123456b";
		final String userName = "apotestmail@163.com";
		final boolean debug = true;
		final boolean useSsl = true;
		final int port = 355;
		final SessionConfig config = SessionConfigBuilder.create(host).setAuthPassword(pass).setAuthUserName(userName)
				.setDebug(debug).setMailSmptAuth(true).setUseSsl(useSsl).setMailSmtpPort(port).build();
		Assert.assertEquals(host, config.getMailSmtpHost());
		Assert.assertEquals(pass, config.getAuthPassword());
		Assert.assertEquals(userName, config.getAuthUserName());
		Assert.assertTrue(config.isDebug());
		Assert.assertTrue(config.isUseSsl());
		Assert.assertEquals(port, config.getMailSmtpPort());
	}
}
