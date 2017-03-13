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
			if (checkArticleTitle(article.getTitle())) {
				article.getId();
				getArticleBean().insertArticle(article);
			}
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
			if (checkArticleTitle(article.getTitle())) {
				getArticleBean().updateArticle(article);
			}
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

	public void add() {
		insertArticle(this.article);
		this.article = new Article();
	}

	public void clearFilter() {
		this.searchName = "";
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(final Article article) {
		this.article = article;
	}

	public String getSearchName() {
		return this.searchName;
	}

	public void setSearchName(final String searchName) {
		this.searchName = searchName;
	}

	public void onEdit(final RowEditEvent event) {

		final Article article = (Article) event.getObject();

		updateArticle(article);

		final FacesMessage msg = new FacesMessage("Article Edited", ((Article) event.getObject()).getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(final RowEditEvent event) {

		final FacesMessage msg = new FacesMessage("Edit Cancelled", ((Article) event.getObject()).getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void dummy() {

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

	private boolean checkArticleTitle(final String title) {
		if (title.length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min 3 character", null));
			return false;
		} else {
			return true;
		}
	}

}
