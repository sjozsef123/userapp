package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.ejb.repository.IBookRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Book_;

/**
 * This session bean manages the books.
 * 
 * @author Majai Robert
 *
 */

@Stateless
public class BookRepositoryBean extends PublicationRepositoryBean<Book> implements IBookRepository {

	@PersistenceContext
	private EntityManager entityManager;
	private Logger oLogger = Logger.getLogger(BookRepositoryBean.class);

	public BookRepositoryBean() {
		super(Book.class);
	}

	@Override
	public List<Book> getByFilter(PublicationFilter filter) throws RepositoryException {

		List<Book> filteredBooks = new ArrayList<>();

		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
			Root<Book> root = criteriaQuery.from(Book.class);

			root.fetch("bAuthors", JoinType.LEFT);
			criteriaQuery.select(root);

			if (filter.getTitle() != null && filter.getTitle() != "") {

				Predicate titleFilter = builder.like(root.get(Book_.title), "%" + filter.getTitle() + "%");
				criteriaQuery.where(titleFilter);
			}

			if (filter.getMinStock() != null) {
				
				Predicate minStock = builder.greaterThanOrEqualTo(root.get(Book_.copiesLeft), filter.getMinStock());
				criteriaQuery.where(builder.and(minStock));
			}
			
			if(filter.getMaxStock() != null) {
				
				Predicate maxStock = builder.lessThanOrEqualTo(root.get(Book_.copiesLeft), filter.getMaxStock());
				criteriaQuery.where(builder.and(maxStock));
			}
			
			filteredBooks = entityManager.createQuery(criteriaQuery).getResultList();
			return filteredBooks;

		} catch (PersistenceException e) {

			oLogger.error("Failed to query books by filter.");
			throw new RepositoryException("book.read");

		}
	}
}
