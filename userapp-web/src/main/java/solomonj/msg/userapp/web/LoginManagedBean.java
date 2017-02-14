package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import solomonj.msg.userapp.jpa.model.Role;
import solomonj.msg.userapp.jpa.model.User;

/**
 * Managed bean for login.
 * 
 * @author Simo Zoltan
 *
 */
@Named("loginmanagedbean")
@SessionScoped
public class LoginManagedBean implements Serializable {

	@Inject
	private UserManagedBean userManagedBean;

	private static final long serialVersionUID = 9036899636413702756L;
	private static final Locale LOCALE_HU = new Locale("hu", "hu");
	private static Locale currentLocale = null;
	private User loggedInUser = null;
	private static final String RESOURCE_BUNDLE_NAME = "msg";

	public void login(String n, String p) {
		setLoggedInUser(userManagedBean.login(n, p));
	}

	public void logout() {
		setLoggedInUser(null);
	}

	public static void setLanguage() {
		setCurrentLocale(getCurrentLocale());
	}

	public static void changeLanguage() {
		if (currentLocale == Locale.ENGLISH) {
			setCurrentLocale(LOCALE_HU);
		} else {
			setCurrentLocale(Locale.ENGLISH);
		}
	}

	public static Locale getCurrentLocale() {
		if (currentLocale == null) {
			UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
			setCurrentLocale(viewRoot.getLocale());
		}
		return currentLocale;
	}

	public static void setCurrentLocale(Locale currentLocale) {
		LoginManagedBean.currentLocale = currentLocale;
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		viewRoot.setLocale(currentLocale);
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	private void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public String isLoggedIn() {
		return (getLoggedInUser() == null) || (getLoggedInUser().getUsername() == null)
				|| (getLoggedInUser().getUsername().isEmpty()) ? "login.xhtml" : null;
	}

	public String isAdmin() {
		if (isLoggedIn() == null) {
			List<Role> lr = getLoggedInUser().getRoles();
			for (Role role : lr) {
				if (role.getRolename().toLowerCase().contains("admin")) {
					return null;
				}
			}
		}
		return "login.xhtml";
	}

	public static String getResourceBundleString(String resourceBundleKey) throws MissingResourceException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, RESOURCE_BUNDLE_NAME);
		return bundle.getString(resourceBundleKey);
	}

}
