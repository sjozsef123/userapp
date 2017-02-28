package solomonj.msg.userapp.ejb.service.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.bean.UserRepositoryBean;
import solomonj.msg.userapp.ejb.util.DebugMessages;

/**
 * Email handling class
 *
 * @author Szocs Csilla
 *
 */
public class SendEmail {
	private static Logger oLogger = Logger.getLogger(UserRepositoryBean.class);

	public static void sendEmail(final String to, final String from) throws ServiceException {

		final Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		final Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("szocscsillamaria", "aranyalma");
			}
		});

		// compose the message
		try {
			oLogger.debug(DebugMessages.SEND_EMAIL);
			final MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Ping");
			message.setSentDate(new Date());
			message.setText("Hello, this is example of sending email " );

			// Send message
			final Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", "szocscsillamaria", "aranyalma");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			oLogger.debug(DebugMessages.SEND_EMAIL_OK);
		} catch (final MessagingException mex) {
			oLogger.error(mex.getMessage());
			throw new ServiceException("error.email.failed");
		}
	}

}
