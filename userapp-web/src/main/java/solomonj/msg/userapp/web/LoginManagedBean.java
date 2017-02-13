package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import solomonj.msg.userapp.jpa.model.User;

@Named("loginmanagedbean")
@SessionScoped
public class LoginManagedBean implements Serializable {

	@Inject
	private static UserManagedBean userManagedBean;

	private static final long serialVersionUID = 9036899636413702756L;
	private static final Locale LOCALE_HU = new Locale("hu", "hu");
	private static Locale currentLocale = null;
	private User loggedInUser = null;

	public boolean login(String n, String p) {
		userManagedBean.login(n, p);
//		if (getLoggedInUser() != null) {
//			return true;
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null,
//					new FacesMessage(FacesMessage.SEVERITY_INFO, "User login error", null));
//			return false;
//		}
		return true;
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
	
	public boolean isLoggedIn() {		
		//return (getLoggedInUser() == null) || (getLoggedInUser().getUsername().isEmpty()) ? false : true;		
		return true;
	}
	
	public boolean isAdmin() {		
//		if (isLoggedIn()) {
//			List<Role> lr = getLoggedInUser().getRoles();
//			for (Role role : lr) {
//				if (role.getRolename().toLowerCase().contains("admin")) {
//					return true;
//				}
//			}		
//		}
		return false;			
	}

}
