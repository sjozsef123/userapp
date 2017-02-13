package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Article;
/**
 * interface for Article CRUD operations.
 * @author szocsc
 *
 */
public interface IArticleService {
	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/ArticleServiceBean";
	/**
	 * This function gets all the Articles from DB
	 * @return List of Articles
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<Article> getAllArticles() throws ServiceException;
	/**
	 * This method inserts an Articles into DB.
	 * 
	 * @param article
	 * @throws ServiceException, when Server could not finish request
	 */
	public void insertArticle(Article article) throws ServiceException;
	/**
	 * This method updates the Article .
	 * 
	 * @param article
	 * @throws ServiceException, when Server could not finish request
	 */
	public void updateArticle(Article article) throws ServiceException;
	/**
	 * This method deletes the Article 
	 * 
	 * @param article
	 * @throws ServiceException, when Server could not finish request
	 */
	public void deleteArticle(Article article) throws ServiceException;
	/**
	 * This method searches for an Article with the specified name.
	 * 
	 * @param name
	 * @return List<Article>
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<Article> searchArticleByName(String name) throws ServiceException;
}
