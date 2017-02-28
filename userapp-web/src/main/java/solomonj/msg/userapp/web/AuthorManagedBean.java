package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.event.RowEditEvent;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IAuthorService;
import solomonj.msg.userapp.jpa.model.Author;

/**
 * Managed bean for authors.
 *
 * @author Szocs Csilla
 *
 */
@Named("authorManagedBean")
@SessionScoped
public class AuthorManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private IAuthorService authorBean = null;
	private Author author = new Author();
	private List<Author> allAuthors = null;
	private String searchName = "";
	private boolean edit;

	public List<Author> getAllAuthor() {
		try {
			this.allAuthors = getAuthorBean().searchAuthorByName(this.searchName);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
		if (this.allAuthors == null) {
			return new ArrayList<>();
		}
		return this.allAuthors;
	}

	public void editAuthor(final Author author) {
		this.author = author;
		this.edit = true;
	}

	public void cancelEdit() {
		this.author = new Author();
		this.edit = false;
	}

	public void resetAdd() {
		this.author = new Author();
	}

	public void insertAuthor(final Author author) {
		try {
			getAuthorBean().insertAuthor(author);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void add() {
		if (checkUserName()) {
			insertAuthor(this.author);
			this.author = new Author();
		}
	}

	public void updateAuthor(final Author author) {
		try {
			getAuthorBean().updateAuthor(author);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void saveEdit() {
		if (checkUserName()) {

			updateAuthor(this.author);
			this.author = new Author();
		}
	}

	public void deleteAuthorById(final Author author) {
		try {
			getAuthorBean().deleteAuthor(author);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void delete(final Author author) {
		if (this.edit) {
			cancelEdit();
		}
		deleteAuthorById(author);
	}

	public boolean isEdit() {
		return this.edit;
	}

	public void setEdit(final boolean edit) {
		this.edit = edit;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	public String getSearchName() {
		return this.searchName;
	}

	public void setSearchName(final String searchName) {
		this.searchName = searchName;
	}

	public void clearFilter() {
		this.searchName = "";
	}

	private IAuthorService getAuthorBean() {
		if (this.authorBean == null) {
			try {
				final InitialContext jndi = new InitialContext();
				this.authorBean = (IAuthorService) jndi.lookup(IAuthorService.jndiNAME);
			} catch (final NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("author.naming"), null));
			}
		}
		return this.authorBean;
	}

	private boolean checkUserName() {
		if (this.author.getName().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min 3 character", null));
			return false;
		} else {
			return true;
		}
	}

	public void onEdit(final RowEditEvent event) {

		final Author author = (Author) event.getObject();

		updateAuthor(author);

		final FacesMessage msg = new FacesMessage("Author Edited", ((Author) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(final RowEditEvent event) {

		final FacesMessage msg = new FacesMessage("Edit Cancelled", ((Author) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
