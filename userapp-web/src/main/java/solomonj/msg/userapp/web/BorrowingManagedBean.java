package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class BorrowingManagedBean implements Serializable {

	private static final long serialVersionUID = 7044748365143600630L;
	private IBorrowingService borrowingBean = null;

	private PublicationBorrowing borrowing = new PublicationBorrowing();
	private PublicationBorrowingPK borrowingId = new PublicationBorrowingPK();
	private User user = new User();

	private IBorrowingService getBorrowingBean() {
		if (borrowingBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				borrowingBean = (IBorrowingService) jndi.lookup(IBorrowingService.jndiNAME);
			} catch (NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("borrowing.naming"), null));
			}
		}
		return borrowingBean;
	}

	public boolean getBorrowingIdCompleted() {
		return (getBorrowingId() == null)
				|| ((getBorrowingId().getUserId() == 0) || (getBorrowingId().getPublicationId() == 0)) ? false : true;
	}

	public void selectUser(User u) {
		clearVariables();
		setUser(u);
		getBorrowingId().setUserId(u.getId());
	}

	public void selectPublication(Publication p) {
		getBorrowingId().setPublicationId(p.getId());
	}

	public void setBorrowingId(PublicationBorrowingPK borrowingId) {
		this.borrowingId = borrowingId;
	}

	public PublicationBorrowingPK getBorrowingId() {
		return borrowingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private void clearVariables() {
		setBorrowingId(new PublicationBorrowingPK());
		setUser(new User());
	}

	public void returnBorrowing() {
		try {
			getBorrowingBean().returnPublication(borrowingId);
			clearVariables();
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void borrowBorrowing() {
		try {
			user.setBorrowing(null);
			borrowing.setId(getBorrowingId());
			borrowing.setUser(user);
			borrowing.setBorrowingDate(Date.valueOf(LocalDate.now()));
			borrowing.setDeadline(Date.valueOf(LocalDate.now().plusDays(20)));
			getBorrowingBean().borrowPublication(borrowing);
			clearVariables();
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

}
