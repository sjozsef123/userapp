package solomonj.msg.userapp.ejb.repository.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IAuthorRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Author;

/**
 * This session bean manages the authors.
 *
 * @author Szocs Csilla
 *
 */
@Stateless
public class AuthorRepositoryBean extends BasicRepositoryBean<Author> implements IAuthorRepository {
	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private final Logger oLogger = Logger.getLogger(AuthorRepositoryBean.class);

	public AuthorRepositoryBean() {
		super(Author.class);
	}

	@Override
	public List<Author> searchAuthorByName(final String name, final int start, final int fin) throws RepositoryException {
		try {
			this.oLogger.debug(DebugMessages.SEARCH_AUTHORS_BY_NAME);
			final TypedQuery<Author> query = this.entityManager.createQuery("Select a " + "from Author a where a.name LIKE :name ",
					Author.class);
			query.setParameter("name", "%" + name + "%");
			if (fin > 0) {
				query.setFirstResult(start);
				query.setMaxResults(fin);
			}
			this.oLogger.debug(DebugMessages.SEARCH_AUTHORS_BY_NAME_OK);
			return query.getResultList();
		} catch (final PersistenceException e) {
			this.oLogger.error("Failed to get author by name", e);
			throw new RepositoryException("author.read");
		}
	}

	@Override
	public int getCountAuthorsByName(final String name) throws RepositoryException {
		try {
			this.oLogger.debug(DebugMessages.COUNT_ALL_AUTHORS);
			final Query query = this.entityManager.createQuery("SELECT count(a) FROM Author a WHERE a.name LIKE ?1");
			query.setParameter(1, "%" + name + "%");
			this.oLogger.debug(DebugMessages.COUNT_ALL_AUTHORS_OK);
			return (int) ((long) (query.getSingleResult()));
		} catch (final PersistenceException e) {
			this.oLogger.error("Failed to count authors", e);
			throw new RepositoryException("author.read");
		}
	}

}
