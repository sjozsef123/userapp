package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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
			allAuthors = getAuthorBean().searchAuthorByName(searchName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if (allAuthors == null) {
			return new ArrayList<>();
		}
		return allAuthors;
	}

	public void editAuthor(Author author) {
		this.author = author;
		edit = true;
	}

	public void cancelEdit() {
		this.author = new Author();
		edit = false;
	}

	public void resetAdd() {
		author = new Author();
	}

	public void insertAuthor(Author author) {
		try {
			getAuthorBean().insertAuthor(author);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void add() {
		if (checkUserName()) {
			insertAuthor(author);
			author = new Author();
		}
	}

	public void updateAuthor(Author author) {
		try {
			getAuthorBean().updateAuthor(author);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void saveEdit() {
		if (checkUserName()) {

			updateAuthor(author);
			this.author = new Author();
		}
	}

	public void deleteAuthorById(Author author) {
		try {
			getAuthorBean().deleteAuthor(author);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void delete(Author author) {
		if (edit) {
			cancelEdit();
		}
		deleteAuthorById(author);
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public void clearFilter() {
		searchName = "";
	}

	private IAuthorService getAuthorBean() {
		if (authorBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				authorBean = (IAuthorService) jndi.lookup(IAuthorService.jndiNAME);
			} catch (NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("author.naming"), null));
			}
		}
		return authorBean;
	}

	private boolean checkUserName() {
		if (author.getName().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min 3 character", null));
			return false;
		} else {
			return true;
		}
	}

}
