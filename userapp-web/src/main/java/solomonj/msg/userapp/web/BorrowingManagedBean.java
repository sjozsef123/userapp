package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.enterprise.context.SessionScoped;
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
 * Managed bean for borrowings.
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
	private Publication publication = null;
	private Publication returnPublication = null;

	IBorrowingService getBorrowingBean() {
		if (this.borrowingBean == null) {
			try {
				final InitialContext jndi = new InitialContext();
				this.borrowingBean = (IBorrowingService) jndi.lookup(IBorrowingService.jndiNAME);
			} catch (final NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("borrowing.naming"), null));
			}
		}
		return this.borrowingBean;
	}

	public boolean getBorrowingIdCompleted() {
		return (getBorrowingId() == null)
				|| ((getBorrowingId().getUserId() == 0) || (getBorrowingId().getPublicationId() == 0)) ? false : true;
	}

	public void selectUser() {
		final User u = this.user;
		clearVariables();
		this.user = u;
		System.out.println(this.user+"U");
		if (this.user != null) {
			getBorrowingId().setUserId(this.user.getId());
		}
	}

	public void selectPublication() {
		System.out.println(this.publication+"P");
		if (this.publication != null) {
			getBorrowingId().setPublicationId(this.publication.getId());
		}
	}

	public void selectReturnPublication() {
		System.out.println(this.returnPublication+"R");
		if (this.returnPublication != null) {
			getBorrowingId().setPublicationId(this.returnPublication.getId());
		}
	}

	public void setBorrowingId(final PublicationBorrowingPK borrowingId) {
		this.borrowingId = borrowingId;
	}

	public PublicationBorrowingPK getBorrowingId() {
		return this.borrowingId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Publication getPublication() {
		return this.publication;
	}

	public void setPublication(final Publication publication) {
		this.publication = publication;
	}

	public Publication getReturnPublication() {
		return this.returnPublication;
	}

	public void setReturnPublication(final Publication returnPublication) {
		this.returnPublication = returnPublication;
	}

	public PublicationBorrowing getBorrowing() {
		return this.borrowing;
	}

	public void setBorrowing(final PublicationBorrowing borrowing) {
		setReturnPublication(borrowing.getPublication());
		this.borrowing = borrowing;
	}

	void clearVariables() {
		setBorrowingId(new PublicationBorrowingPK());
		setUser(new User());
		setPublication(null);
		setReturnPublication(null);
		//setBorrowing(null);
	}

	public void returnBorrowing() {
		try {
			getBorrowingBean().returnPublication(this.borrowingId);
			clearVariables();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					LoginManagedBean.getResourceBundleString("web.borrowing.returnok"), null));
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void borrowBorrowing() {
		try {
			final PublicationBorrowing borrowing = new PublicationBorrowing();
			this.user.setBorrowing(null);
			borrowing.setId(getBorrowingId());
			borrowing.setUser(this.user);
			borrowing.setBorrowingDate(Date.valueOf(LocalDate.now()));
			borrowing.setDeadline(Date.valueOf(LocalDate.now().plusDays(20)));
			getBorrowingBean().borrowPublication(borrowing);
			clearVariables();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					LoginManagedBean.getResourceBundleString("web.borrowing.borrowok"), null));
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

}
