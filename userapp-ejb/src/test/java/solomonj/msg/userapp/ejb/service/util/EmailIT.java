package solomonj.msg.userapp.ejb.service.util;

import org.junit.Test;

import org.junit.Assert;
import solomonj.msg.appuser.common.exception.ServiceException;

/**
 * Email sending integration test.
 * 
 * @author Szocs Csilla
 *
 */
public class EmailIT {
	
	@Test
	public void testSendMail() {
		try {
			SendEmail.sendEmail("szocscsillamaria@gmail.com", "szocscsillamaria@gmail.com");
		} catch (ServiceException e) {
			Assert.fail();
		}
	}
}
