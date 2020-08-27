package io.github.paulooorg.api.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.infrastructure.app.ApplicationProperties;

@ApplicationScoped
public class EmailService {
	@Inject
	private Logger logger;
	
	private String host;
	
	private Integer port;
	
	private String sender;
	
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	@PostConstruct
	public void init() {
		host = ApplicationProperties.get("email.host");
		port = Integer.valueOf(ApplicationProperties.get("email.port"));
		sender = ApplicationProperties.get("email.sender");
	}

	public Future<Boolean> send(String subject, String message, String to) {
		return executorService.submit(() -> {
			try {
				Email email = createEmail();
				email.setSubject(subject);
				email.setMsg(message);
				email.addTo(to);
				email.send();
				return true;
			} catch (Exception e) {
				logger.error("Failed to send email to {} with subject {}", to, subject);
				logger.error(e.getMessage(), e);
				return false;
			}
		});
	}
	
	private Email createEmail() throws Exception {
		Email email = new SimpleEmail();
		email.setHostName(host);
		email.setSmtpPort(port);
		email.setSSLOnConnect(false);
		email.setFrom(sender);
		return email;
	}
}
