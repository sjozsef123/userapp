package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

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
	private LazyDataModel<Author> lazyModel = null;

	@PostConstruct
	public void init() {
		this.lazyModel = new LazyDataModel<Author>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Author> load(final int first, final int pageSize, final String sortField,
					final SortOrder sortOrder, final Map<String, Object> filters) {
				List<Author> data = new ArrayList<>();

				try {
					final int dataSize = (AuthorManagedBean.this.getAuthorBean()
							.getCountAuthorsByName(AuthorManagedBean.this.searchName));
					this.setRowCount(dataSize);

					data = AuthorManagedBean.this.getAuthorBean().searchAuthorByName(AuthorManagedBean.this.searchName,
							first, pageSize);
				} catch (final ServiceException e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							LoginManagedBean.getResourceBundleString(e.getMessage()), null));
				}
				return data;
			}
		};
	}

	public LazyDataModel<Author> getLazyModel() {
		return this.lazyModel;
	}

	public List<Author> getAllAuthor() {
		try {
			this.allAuthors = getAuthorBean().searchAuthorByName(this.searchName, 0, 0);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
		if (this.allAuthors == null) {
			return new ArrayList<>();
		}
		return this.allAuthors;
	}

	public void insertAuthor(final Author author) {
		try {
			if (checkAuthorName(author.getName())) {
				getAuthorBean().insertAuthor(author);
			}
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void add() {
		insertAuthor(this.author);
		this.author = new Author();
	}

	public void updateAuthor(final Author author) {
		try {
			if (checkAuthorName(author.getName())) {
				getAuthorBean().updateAuthor(author);
			}
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
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
		deleteAuthorById(author);
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

	private boolean checkAuthorName(final String name) {
		if (name.length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min 3 character", null));
			return false;
		} else {
			return true;
		}
	}

}
