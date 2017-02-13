package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("loginmanagedbean")
@SessionScoped
public class LoginManagedBean implements Serializable{

	private static final long serialVersionUID = 9036899636413702756L;
	private static final Locale LOCALE_HU = new Locale("hu", "hu");
	private static Locale currentLocale = null; 
	
	public boolean login(String n, String p) {		
		return true; 
	}
	
	public void setLanguage(){
		setCurrentLocale(getCurrentLocale());
	}

	public void changeLanguage() {		
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

}
