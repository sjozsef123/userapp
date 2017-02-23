package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		List<Predicate> predicates = new ArrayList<>();
		
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
			Root<Book> root = criteriaQuery.from(Book.class);

			root.fetch("bAuthors", JoinType.LEFT);
			criteriaQuery.select(root);

			if (filter.getTitle() != null && filter.getTitle() != "") {

				predicates.add(builder.like(root.get(Book_.title), "%" + filter.getTitle() + "%"));
			}

			if (filter.getMinStock() != null) {
				
				predicates.add(builder.greaterThanOrEqualTo(root.get(Book_.copiesLeft), filter.getMinStock()));
			}
			
			if(filter.getMaxStock() != null) {
				
				predicates.add(builder.lessThanOrEqualTo(root.get(Book_.copiesLeft), filter.getMaxStock()));
			}
			
			if (filter.getPublisher() != null && filter.getPublisher() != "") {
				
				predicates.add(builder.like(root.get(Book_.publisher), "%" + filter.getPublisher() + "%"));
			}
			
			if (filter.getReleasedAfter() != null) {
			
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, filter.getReleasedAfter());
				predicates.add(builder.greaterThanOrEqualTo(root.<Date>get(Book_.releaseDate), calendar.getTime()));
			}
			
			if (filter.getReleasedBefore() != null) {
				
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, filter.getReleasedBefore());
				predicates.add(builder.lessThanOrEqualTo(root.<Date>get(Book_.releaseDate), calendar.getTime()));
			}
			
			criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			
			filteredBooks = entityManager.createQuery(criteriaQuery).getResultList();
			return filteredBooks;

		} catch (PersistenceException e) {

			oLogger.error("Failed to query books by filter.");
			throw new RepositoryException("book.read");

		}
	}
}
