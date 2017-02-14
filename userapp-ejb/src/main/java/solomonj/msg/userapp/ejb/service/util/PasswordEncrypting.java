package solomonj.msg.userapp.ejb.service.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;

public class PasswordEncrypting {
	private static Logger oLogger = Logger.getLogger(PasswordEncrypting.class);

	private PasswordEncrypting() {

	}

	/**
	 * This method use property provider to get algorithm and encoding to
	 * encrypt password with salt
	 * 
	 * @param password
	 * @param salt
	 * @return hashed password
	 * @throws ServiceException
	 */
	public static String encrypt(String password, String salt) throws ServiceException {
		try {
			byte[] initialBytes = (password + salt).getBytes(PropertyProvider.getProperty("encrypt.encoding"));
			MessageDigest algorithm = MessageDigest.getInstance(PropertyProvider.getProperty("encrypt.algorithm"));
			algorithm.reset();
			algorithm.update(initialBytes);
			byte[] hashBytes = algorithm.digest();
			return new String(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			oLogger.error("No Such Algorithm Exception", e);
			throw new ServiceException("No Such Algorithm Exception");
		} catch (UnsupportedEncodingException e) {
			oLogger.error("Unsuported Encoding", e);
			throw new ServiceException("Unsuported Encoding");
		}
	}
}
