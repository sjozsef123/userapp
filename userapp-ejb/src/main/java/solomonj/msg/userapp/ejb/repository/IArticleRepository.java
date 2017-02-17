package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Article;
/**
 * Interface for handling articles.
 * 
 * @author Szocs Csilla
 *
 */
public interface IArticleRepository extends IBasicRepository<Article>{
	
	/**
	 * This method searches for Articles with the specified name.
	 * 
	 * @param name
	 * @return articles
	 * @throws RepositoryException
	 */
	public List<Article>searchArticleByName(String name) throws RepositoryException;
	public List<Article>getFreeArticles() throws RepositoryException;
}
