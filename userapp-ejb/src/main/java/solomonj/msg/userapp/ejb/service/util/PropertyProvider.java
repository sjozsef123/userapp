package solomonj.msg.userapp.ejb.service.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.bean.UserRepositoryBean;

/**
 * Resource bundle
 * 
 * @author Solomon Jozsef
 *
 */
public class PropertyProvider {
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("userapp");
	private static Logger oLogger = Logger.getLogger(UserRepositoryBean.class);

	private PropertyProvider() {
	}

	/**
	 * get property using ResourceBundle
	 * 
	 * @param property
	 *            - name of property
	 * @return value of the property
	 */
	public static String getProperty(String property) {
		try {
			return RESOURCE_BUNDLE.getString(property);
		} catch (MissingResourceException e) {
			oLogger.error("missing resource exception", e);
			return "!" + property + "!";
		}
	}
}
