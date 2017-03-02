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
import solomonj.msg.userapp.ejb.repository.INewspaperRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Newspaper_;

/**
 * This session bean manages the newspapers.
 *
 * @author Majai Robert
 *
 */
@Stateless
public class NewspaperRepositoryBean extends PublicationRepositoryBean<Newspaper> implements INewspaperRepository {

	@PersistenceContext
	private EntityManager entityManager;
	private final Logger oLogger = Logger.getLogger(NewspaperRepositoryBean.class);

	public NewspaperRepositoryBean() {
		super(Newspaper.class);
	}

	@Override
	public List<Newspaper> getByFilter(final PublicationFilter filter) throws RepositoryException {

		List<Newspaper> filteredNewspapers = new ArrayList<>();
		final List<Predicate> predicates = new ArrayList<>();

		try {
			final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
			final CriteriaQuery<Newspaper> criteriaQuery = builder.createQuery(Newspaper.class);
			final Root<Newspaper> root = criteriaQuery.from(Newspaper.class);

			root.fetch("articles", JoinType.LEFT);
			criteriaQuery.select(root).distinct(true);

			if (filter.getTitle() != null && filter.getTitle() != "") {

				predicates.add(builder.like(root.get(Newspaper_.title), "%" + filter.getTitle() + "%"));
			}

			if (filter.getMinStock() != null) {

				predicates.add(builder.greaterThanOrEqualTo(root.get(Newspaper_.copiesLeft), filter.getMinStock()));
			}

			if (filter.getMaxStock() != null) {

				predicates.add(builder.lessThanOrEqualTo(root.get(Newspaper_.copiesLeft), filter.getMaxStock()));
			}

			if (filter.getPublisher() != null && filter.getPublisher() != "") {

				predicates.add(builder.like(root.get(Newspaper_.publisher), "%" + filter.getPublisher() + "%"));
			}

			if (filter.getReleasedAfter() != null) {

				final Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, filter.getReleasedAfter());
				predicates.add(builder.greaterThanOrEqualTo(root.<Date>get(Newspaper_.releaseDate), calendar.getTime()));
			}

			if (filter.getReleasedBefore() != null) {

				final Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, filter.getReleasedBefore());
				predicates.add(builder.lessThanOrEqualTo(root.<Date>get(Newspaper_.releaseDate), calendar.getTime()));
			}

			criteriaQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).distinct(true);

			filteredNewspapers = this.entityManager.createQuery(criteriaQuery).getResultList();
			return filteredNewspapers;

		} catch (final PersistenceException e) {

			this.oLogger.error("Failed to query newspapers by filter.");
			throw new RepositoryException("newspaper.read");

		}
	}
}
