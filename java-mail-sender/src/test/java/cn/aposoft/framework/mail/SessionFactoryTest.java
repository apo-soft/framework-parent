/**
 * 
 */
package cn.aposoft.framework.mail;

import java.util.Properties;

import javax.mail.Session;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author LiuJian
 *
 */
public class SessionFactoryTest {
	/**
	 * 测试普通邮件配置信息
	 */
	@Test
	public void testNormalConfigSession() {
		final String host = "smtp.163.com";
		final String pass = "a123456b";
		final String userName = "apotestmail@163.com";
		final boolean debug = true;
		final boolean useSsl = false;
		final SessionConfig config = SessionConfigBuilder.create(host).setAuthPassword(pass).setAuthUserName(userName)
				.setDebug(debug).setMailSmptAuth(true).setUseSsl(useSsl).build();
		Session session = MailSessionFactory.getSession(config);

		Assert.assertTrue(session.getDebug());
		Properties props = session.getProperties();
		Assert.assertEquals(host, props.getProperty("mail.smtp.host"));
		Assert.assertEquals("25", props.getProperty("mail.smtp.port"));
		Assert.assertEquals("true", props.getProperty("mail.smtp.auth"));
	}

	/**
	 * 测试普通邮件配置信息
	 */
	@Test
	public void testSslConfigSession() {
		final String host = "smtp.163.com";
		final String pass = "a123456b";
		final String userName = "apotestmail@163.com";
		final boolean debug = true;
		final boolean useSsl = true;
		final SessionConfig config = SessionConfigBuilder.create(host).setAuthPassword(pass).setAuthUserName(userName)
				.setDebug(debug).setMailSmptAuth(true).setUseSsl(useSsl).build();
		Session session = MailSessionFactory.getSession(config);
		Assert.assertTrue(session.getDebug());
		Properties props = session.getProperties();
		Assert.assertEquals(host, props.getProperty("mail.smtp.host"));
		Assert.assertEquals("465", props.getProperty("mail.smtp.port"));
		Assert.assertEquals("true", props.getProperty("mail.smtp.auth"));
		
	}
}
