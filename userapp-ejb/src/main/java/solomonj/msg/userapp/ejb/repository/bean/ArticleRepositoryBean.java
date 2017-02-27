package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Article;
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

	private final Logger oLogger = Logger.getLogger(ArticleRepositoryBean.class);

	public ArticleRepositoryBean() {
		super(Article.class);
	}

	@Override
	public List<Article> searchArticleByName(final String title, final int start, final int fin) throws RepositoryException {
		try {
			this.oLogger.debug(DebugMessages.SEARCH_ARTICLES_BY_NAME);
			final TypedQuery<Article> query = this.entityManager
					.createQuery("Select a " + "from Article a where a.title LIKE :title", Article.class);
			query.setParameter("title", "%" + title + "%");
			if (fin > 0) {
				query.setFirstResult(start);
				query.setMaxResults(fin);
			}
			this.oLogger.debug(DebugMessages.SEARCH_ARTICLES_BY_NAME_OK);
			return query.getResultList();
		} catch (final PersistenceException e) {
			this.oLogger.error("Failed to get article by name", e);
			throw new RepositoryException("article.read");
		}

	}

	@Override
	public List<Article> getFreeArticles() throws RepositoryException {

		try {
			System.out.println("asdadsadasdasdasdasda");
			// TypedQuery<Article> query =
			// entityManager.createNamedQuery("Article.freeArticles",
			// Article.class);
			// List<Article> results = query.getResultList();
			// results.forEach(a -> System.out.println(a.getTitle()));

			final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
			final CriteriaQuery<Article> articleCriteriaQuery = builder.createQuery(Article.class);
			final Root<Article> articleRoot = articleCriteriaQuery.from(Article.class);

			articleCriteriaQuery.select(articleRoot);

			final List<Article> allArticles = this.entityManager.createQuery(articleCriteriaQuery).getResultList();
			System.out.println("---------articles");
			allArticles.forEach(a -> System.out.println(a.getTitle()));

			final CriteriaQuery<Newspaper> newspaperCriteriaQuery = builder.createQuery(Newspaper.class);
			final Root<Newspaper> newspaperRoot = newspaperCriteriaQuery.from(Newspaper.class);

			newspaperRoot.fetch("articles", JoinType.LEFT);
			newspaperCriteriaQuery.select(newspaperRoot).distinct(true);

			final List<Newspaper> newspapers = this.entityManager.createQuery(newspaperCriteriaQuery).getResultList();

			System.out.println("---------newspapers");
			newspapers.forEach(n -> System.out.println(n.getTitle()));

			final List<Article> free = new ArrayList<>();

			for (final Newspaper nwsppr : newspapers) {

				for (final Article artcl : allArticles) {
					if (!nwsppr.getArticles().contains(artcl))
						free.add(artcl);
				}
			}

			System.out.println("---------free");
			free.forEach(freearticle -> System.out.println(freearticle.getTitle()));

			return free;

		} catch (final PersistenceException e) {

			this.oLogger.error("Failed to get free articles", e);
			throw new RepositoryException("article.read");
		}
	}

	@Override
	public int getCountArticlesByName(final String title) throws RepositoryException {
		try {
			this.oLogger.debug(DebugMessages.COUNT_ALL_ARTICLES);
			final Query query = this.entityManager.createQuery("SELECT count(a) FROM Article a WHERE a.title LIKE ?1");
			query.setParameter(1, "%" + title + "%");
			this.oLogger.debug(DebugMessages.COUNT_ALL_ARTICLES);
			return (int) ((long) (query.getSingleResult()));
		} catch (final PersistenceException e) {
			this.oLogger.error("Failed to count articles", e);
			throw new RepositoryException("article.read");
		}
	}

}
