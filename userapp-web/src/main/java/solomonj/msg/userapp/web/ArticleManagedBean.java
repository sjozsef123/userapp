package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IArticleService;
import solomonj.msg.userapp.jpa.model.Article;

@Named("articleManagedBean")
@ApplicationScoped
public class ArticleManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private IArticleService articleBean = null;
	private Article article = new Article();
	private boolean edit;
	private List<Article> allArticles = null;
	private String searchName = "";
	public List<Article>getAllArticles(){
		try {
			allArticles = getArticleBean().searchArticleByName(searchName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if(allArticles == null){
			return new ArrayList<>();
		}
		return allArticles;
	}
	public void insertArticle(Article article){
		try {
			getArticleBean().insertArticle(article);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	public void deleteArticleById(Article article){
		try {
			getArticleBean().deleteArticle(article);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}
	}
	public void updateArticle(Article article){
		try {
			getArticleBean().updateArticle(article);
		} catch (ServiceException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "User's name already exists", null));
		}
	}
	public void delete(Article article){
		if(edit){
			cancelEdit();
		}
		deleteArticleById(article);
	}
	public void cancelEdit() {
		this.article = new Article();
		edit = false;
		
	}
	public void editArticle(Article article){
		this.article = article;
		edit = true;
	}
	public void add(){
		if(checkUserName()){
			insertArticle(article);
			article = new Article();
		}
	}
	public void resetAdd(){
		article = new Article();
		
	}
	public void saveEdit(){
		if(checkUserName()){
			updateArticle(article);
			this.article = new Article();
			edit = false;
		}
	}
	public void clearFilter(){
		searchName = "";
	}
	private boolean checkUserName() {
		if (article.getTitle().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min 3 character", null));
			return false;
		} else {
			return true;
		}
	}
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	private IArticleService getArticleBean() {
		if (articleBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				articleBean = (IArticleService) jndi.lookup(IArticleService.jndiNAME);
			} catch (NamingException e) {
				e.printStackTrace();

			}
		}
		return articleBean;
	}

}
