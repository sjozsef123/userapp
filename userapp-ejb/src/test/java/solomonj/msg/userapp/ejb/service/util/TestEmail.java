package solomonj.msg.userapp.ejb.service.util;

import org.junit.Test;

public class TestEmail {
	@Test
	public void test() {
		SendEmail.sendEmail("szocscsillamaria@gmail.com", "szocscsillamaria@gmail.com");
	}
}
