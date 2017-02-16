package solomonj.msg.userapp.ejb.repository.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Article;

/**
 * This session bean manages the articles.
 * 
 * @author Szocs Csilla
 *
 */
@Stateless
public class ArticleRepositoryBean extends BasicRepositoryBean<Article> implements IArticleRepository {
	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private Logger oLogger = Logger.getLogger(ArticleRepositoryBean.class);

	public ArticleRepositoryBean() {
		super(Article.class);
	}

	@Override
	public List<Article> searchArticleByName(String title) throws RepositoryException {
		try {
			oLogger.debug(DebugMessages.SEARCH_ARTICLES_BY_NAME);
			TypedQuery<Article> query = entityManager.createQuery("Select a " + "from Article a where a.title LIKE :title",
					Article.class);
			query.setParameter("title", "%" + title + "%");
			oLogger.debug(DebugMessages.SEARCH_ARTICLES_BY_NAME_OK);
			return query.getResultList();
		} catch (PersistenceException e) {
			oLogger.error("Failed to get article by name", e);
			throw new RepositoryException("article.read");
		}
		
	}

}
