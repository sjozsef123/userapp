package solomonj.msg.userapp.ejb.service.util;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.User;

/**
 * Timer class, for: Email sending;
 *
 * @author Szocs Csilla
 *
 */
@Singleton
@Startup
public class ShowTime {
	@EJB
	private IUserRepository userRepositoryBean;

	private final Logger oLogger = Logger.getLogger(ShowTime.class);

	@Schedule(hour = "1", persistent = false)
	public void checkBorrowing() throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.SEND_EMAIL_WITH_TIMER);
			final List<User> asd = this.userRepositoryBean.getAllBadUsers();
			System.out.println("Number of bad users " + asd.size());
	//		SendEmail.sendEmail("szocscsillamaria@gmail.com", "szocscsillamaria@gmail.com");
			this.oLogger.debug(DebugMessages.SEND_EMAIL_WITH_TIMER_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
		}

	}

	@PostConstruct
	public void startUp() {
		try {
			checkBorrowing();
		} catch (final ServiceException e) {
			this.oLogger.error(e.getMessage());
		}
	}
}
