package cn.aposoft.framework.mail;

import java.util.Date;

public class SimpleVelocityMainSender {

	public static void main(String[] args) {
		VelocityMailContentTemplateEngine engine = new VelocityMailContentTemplateEngine();
		SimpleMailEntity entity = getSimpleMailEntity();
		String mailContent = engine.getContent("simplemail.vm", entity);

		System.out.println(mailContent);
		JavaMail mailEngine = new JavaMail(true);
		//
		// mailEngine.doSendHtmlEmail(entity.getTitle(), mailContent, "于津水",
		// "pleasantboy@qq.com");
		mailEngine.doSendHtmlEmail(entity.getTitle(), mailContent, "于津水", "602570224@qq.com");
	}

	private static SimpleMailEntity getSimpleMailEntity() {
		SimpleMailEntity entity = new SimpleMailEntity();

		entity.setContent("水加油,今天暴走桃花运.");
		entity.setSender("阿宝");
		entity.setReceiver("水哥");
		entity.setTitle("给水哥的好运邮件");
		entity.setDate(new Date().toString());

		return entity;
	}

}
