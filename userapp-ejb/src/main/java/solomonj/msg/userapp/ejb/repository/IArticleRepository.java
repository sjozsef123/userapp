package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Article;

public interface IArticleRepository extends IBasicRepository<Article>{
	/**
	 * This method searches for the Article with the specified name.
	 * @param name
	 * @return articles
	 * @throws RepositoryException
	 */
	public List<Article>searchArticleByName(String name) throws RepositoryException;
}
