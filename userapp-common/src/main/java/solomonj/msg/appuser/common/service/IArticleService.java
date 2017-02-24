package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Article;

/**
 * Interface for Article CRUD operations.
 *
 * @author Szocs Csilla
 *
 */
public interface IArticleService {
	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/ArticleServiceBean";

	/**
	 * This function gets all the articles.
	 *
	 * @return list
	 * @throws ServiceException
	 */
	public List<Article> getAllArticles() throws ServiceException;

	/**
	 * This function counts all the articles with specified title.
	 *
	 * @return list
	 * @throws ServiceException
	 */
	public int getCountArticlesByName(String title) throws ServiceException;

	/**
	 * This method inserts an article.
	 *
	 * @param article
	 * @throws ServiceException
	 */
	public void insertArticle(Article article) throws ServiceException;

	/**
	 * This method updates the article.
	 *
	 * @param article
	 * @throws ServiceException
	 */
	public void updateArticle(Article article) throws ServiceException;

	/**
	 * This method deletes the article
	 *
	 * @param article
	 * @throws ServiceException
	 */
	public void deleteArticle(Article article) throws ServiceException;

	/**
	 * This method searches for an article with the specified name.
	 *
	 * @param name
	 * @return list
	 * @throws ServiceException
	 */
	public List<Article> searchArticleByName(String name, int start, int fin) throws ServiceException;

	/**
	 * Thos method queries those articles which are not listed in any of the
	 * existing newspapers
	 *
	 * @return list
	 * @throws ServiceException
	 */
	public List<Article> getFreeArticles() throws ServiceException;
}
