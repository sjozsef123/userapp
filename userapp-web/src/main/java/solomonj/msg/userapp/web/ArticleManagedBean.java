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

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IArticleService;
import solomonj.msg.userapp.jpa.model.Article;

/**
 * Managed bean for articles.
 *
 * @author Szocs Csilla
 *
 */
@Named("articleManagedBean")
@SessionScoped
public class ArticleManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private IArticleService articleBean = null;
	private Article article = new Article();
	private boolean edit;
	private List<Article> allArticles = null;
	private String searchName = "";
	private LazyDataModel<Article> lazyModel = null;

	@PostConstruct
	public void init() {
		this.lazyModel = new LazyDataModel<Article>() {
			private static final long serialVersionUID = 1L;

			@Override
			public List<Article> load(final int first, final int pageSize, final String sortField,
					final SortOrder sortOrder, final Map<String, Object> filters) {
				List<Article> data = new ArrayList<Article>();

				try {
					final int dataSize = (ArticleManagedBean.this.getArticleBean()
							.getCountArticlesByName(ArticleManagedBean.this.searchName));
					this.setRowCount(dataSize);

					data = ArticleManagedBean.this.getArticleBean()
							.searchArticleByName(ArticleManagedBean.this.searchName, first, pageSize);
				} catch (final ServiceException e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							LoginManagedBean.getResourceBundleString(e.getMessage()), null));
				}
				return data;
			}

		};
	}

	public LazyDataModel<Article> getLazyModel() {
		return this.lazyModel;
	}

	public List<Article> getAllArticles() {
		try {
			this.allArticles = getArticleBean().searchArticleByName(this.searchName, 0, 0);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
		if (this.allArticles == null) {
			return new ArrayList<>();
		}
		return this.allArticles;
	}

	public void insertArticle(final Article article) {
		try {
			getArticleBean().insertArticle(article);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void deleteArticleById(final Article article) {
		try {
			getArticleBean().deleteArticle(article);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void updateArticle(final Article article) {
		try {
			getArticleBean().updateArticle(article);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public List<Article> getFreeArticles() {

		try {

			return getArticleBean().getFreeArticles();
		} catch (final ServiceException e) {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
			return new ArrayList<>();
		}
	}

	public void delete(final Article article) {
		if (this.edit) {
			cancelEdit();
		}
		deleteArticleById(article);
	}

	public void cancelEdit() {
		this.article = new Article();
		this.edit = false;

	}

	public void editArticle(final Article article) {
		this.article = article;
		this.edit = true;
	}

	public void add() {
		if (checkUserName()) {
			insertArticle(this.article);
			this.article = new Article();
		}
	}

	public void resetAdd() {
		this.article = new Article();

	}

	public void saveEdit() {
		if (checkUserName()) {
			updateArticle(this.article);
			this.article = new Article();
			this.edit = false;
		}
	}

	public void clearFilter() {
		this.searchName = "";
	}

	private boolean checkUserName() {
		if (this.article.getTitle().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min 3 character", null));
			return false;
		} else {
			return true;
		}
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(final Article article) {
		this.article = article;
	}

	public boolean isEdit() {
		return this.edit;
	}

	public void setEdit(final boolean edit) {
		this.edit = edit;
	}

	public String getSearchName() {
		return this.searchName;
	}

	public void setSearchName(final String searchName) {
		this.searchName = searchName;
	}

	private IArticleService getArticleBean() {
		if (this.articleBean == null) {
			try {
				final InitialContext jndi = new InitialContext();
				this.articleBean = (IArticleService) jndi.lookup(IArticleService.jndiNAME);
			} catch (final NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("article.naming"), null));
			}
		}
		return this.articleBean;
	}

}
