package cn.aposoft.framework.mail;

import java.util.Collection;

import javax.mail.Session;

public class MailClientImpl implements MailClient {

	private Session session;

	public MailClientImpl(Session session) {
		this.session = session;
	}

	@Override
	public void send(MailContact from, MailReceiver to, String subject, String content) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, MailReceiver to, MailMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void send(MailContact from, Collection<MailReceiver> tos, MailMessage message) {
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
