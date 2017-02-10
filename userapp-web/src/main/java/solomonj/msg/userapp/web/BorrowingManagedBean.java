package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IBorrowingService;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;
import solomonj.msg.userapp.jpa.model.User;

/**
 * 
 * @author Simo Zoltan
 *
 */
@Named("borrowingmanagedbean")
@ApplicationScoped
public class BorrowingManagedBean implements Serializable {

	private static final long serialVersionUID = 7044748365143600630L;
	//private IUserService userBean = null;
	//private IPublicationService publicationBean = null;
	private IBorrowingService borrowingBean = null;
	
	//private PublicationBorrowing borrowing;
	private PublicationBorrowingPK borrowingId = new PublicationBorrowingPK();
	private User user = new User();
	//private Publication publication;
	
//	private IUserService getUserBean() {
//		if (userBean == null) {
//			try {
//				InitialContext jndi = new InitialContext();
//				userBean = (IUserService) jndi.lookup(IUserService.jndiNAME);
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
//		return userBean;
//	}
	
	private IBorrowingService getBorrowingBean() {
	if (borrowingBean == null) {
		try {
			InitialContext jndi = new InitialContext();
			borrowingBean = (IBorrowingService) jndi.lookup(IBorrowingService.jndiNAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	return borrowingBean;
}
	
	public boolean getBorrowingIdCompleted() {
		return (borrowingId == null) || ((borrowingId.getUserId() == 0) || (borrowingId.getPublicationId() == 0)) ? false : true;
	}

	public void selectUser(User u) {
		setUser(u);	
		borrowingId = new PublicationBorrowingPK();	
		borrowingId.setUserId(u.getId());		 
	}
	
	public void selectPublication(Publication p) {		
		borrowingId.setPublicationId(p.getId());		
	}

	public User getUser() {
		return user;		
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	private void clearVariables() {
		borrowingId = null;
		setUser(new User());
	}
			
	public void returnBorrowing() {	
		try {
			getBorrowingBean().returnPublication(borrowingId);
			clearVariables();
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}
	}
	
}
