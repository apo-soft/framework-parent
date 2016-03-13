package cn.aposoft.framework.mail;

import java.util.Collection;

public class MailClientImpl implements MailClient {

	@Override
	public void send(MailContact from, MailReceiver to, String subject, String content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, MailReceiver to, MailMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, Collection<MailReceiver> tos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, Collection<MailReceiver> tos, Collection<MailReceiver> ccs,
			MailMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, Collection<MailReceiver> tos, Collection<MailReceiver> ccs,
			Collection<MailReceiver> scs, MailMessage message) {
		// TODO Auto-generated method stub

	}

}
