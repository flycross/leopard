package io.leopard.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;

public class MailClientImpl implements MailClient {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.user}")
	private String user;

	@Value("${mail.password}")
	private String password;

	@Override
	public boolean sendText(String to, String subject, String content) throws EmailException {
		SimpleEmail email = new SimpleEmail();
		email.setHostName(host);// 设置使用发电子邮件的邮件服务器
		email.addTo(to);
		email.setAuthentication(user, password);
		email.setFrom(user);
		email.setSubject(subject);
		email.setMsg(content);
		email.setSSLOnConnect(true);
		email.setSslSmtpPort("465"); // 若启用，设置smtp协议的SSL端口号
		email.send();
		return true;
	}

	@Override
	public boolean sendHtml(String to, String subject, String content) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(host);// 设置使用发电子邮件的邮件服务器
		email.addTo(to);
		email.setAuthentication(user, password);
		email.setFrom(user);
		email.setSubject(subject);
		email.setMsg(content);
		email.setSSLOnConnect(true);
		email.setSslSmtpPort("465"); // 若启用，设置smtp协议的SSL端口号
		email.send();
		return true;
	}

}
