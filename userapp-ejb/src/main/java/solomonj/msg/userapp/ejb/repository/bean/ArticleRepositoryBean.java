package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Article;
import solomonj.msg.userapp.jpa.model.Article_;
import solomonj.msg.userapp.jpa.model.Book_;
import solomonj.msg.userapp.jpa.model.Newspaper;

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

	@Override
	public List<Article> getFreeArticles() throws RepositoryException {

		try {
			System.out.println("asdadsadasdasdasdasda");
//			 TypedQuery<Article> query = entityManager.createNamedQuery("Article.freeArticles", Article.class);
//			 List<Article> results = query.getResultList();
//			 results.forEach(a -> System.out.println(a.getTitle()));
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Article> articleCriteriaQuery = builder.createQuery(Article.class);
			Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);
					
			articleCriteriaQuery.select(articleRoot); 
			
			List<Article> allArticles = entityManager.createQuery(articleCriteriaQuery).getResultList();
			System.out.println("---------articles");
			allArticles.forEach(a -> System.out.println(a.getTitle()));
			
			CriteriaQuery<Newspaper> newspaperCriteriaQuery = builder.createQuery(Newspaper.class);
			Root<Newspaper> newspaperRoot = newspaperCriteriaQuery.from(Newspaper.class);
			
			newspaperRoot.fetch("articles", JoinType.LEFT);
			newspaperCriteriaQuery.select(newspaperRoot).distinct(true);
			
			List<Newspaper> newspapers = entityManager.createQuery(newspaperCriteriaQuery).getResultList();
			
			System.out.println("---------newspapers");
			newspapers.forEach(n -> System.out.println(n.getTitle()));
			
			List<Article> free = new ArrayList<>();
			
			for(Newspaper nwsppr: newspapers) {
				
				for(Article artcl: allArticles) {
					if(!nwsppr.getArticles().contains(artcl))
						free.add(artcl);
				}
			}
			
			System.out.println("---------free");
			free.forEach(freearticle -> System.out.println(freearticle.getTitle()));
			
			return free;
			
		} catch (PersistenceException e) {
			
			oLogger.error("Failed to get free articles", e);
			throw new RepositoryException("article.read");
		}
	}

}
