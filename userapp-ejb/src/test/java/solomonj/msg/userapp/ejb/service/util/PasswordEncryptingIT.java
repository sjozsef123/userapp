package solomonj.msg.userapp.ejb.service.util;

import org.junit.Assert;
import org.junit.Test;

import solomonj.msg.appuser.common.exception.ServiceException;

public class PasswordEncryptingIT {

	@Test 
	public void encryptTest() throws ServiceException {		
		String res = PasswordEncrypting.encrypt("pass", "user");	
		Assert.assertEquals("�L�a%]�Ƈ���.K�ɞ�", res);		
	}

}
