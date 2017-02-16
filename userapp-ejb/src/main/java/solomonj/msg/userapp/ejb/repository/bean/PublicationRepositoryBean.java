package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IPublicationRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.Publication_;
import solomonj.msg.userapp.jpa.model.User;

/**
 * Basic repository generic functions for publications.
 * 
 * @author Majai Robert
 * @author Solomon Jozsef
 *
 */
public abstract class PublicationRepositoryBean<T extends Publication> extends BasicRepositoryBean<T>
		implements IPublicationRepository<T> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> cls;
	private Logger oLogger = Logger.getLogger(PublicationRepositoryBean.class);

	public PublicationRepositoryBean() {

	}

	public PublicationRepositoryBean(Class<T> cls) {
		super(cls);
		this.cls = cls;
	}

	@Override
	public List<T> filterPublicationByName(String filter) throws RepositoryException {
		List<T> filteredPublications = new ArrayList<>();
		try {
			oLogger.debug(DebugMessages.SEARCH_PUBLICATIONS_BY_NAME);
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = builder.createQuery(cls);
			Root<T> root = criteriaQuery.from(cls);

			criteriaQuery.select(root).where(builder.like(root.get(Publication_.title), "%" + filter + "%"));

			filteredPublications = entityManager.createQuery(criteriaQuery).getResultList();
			oLogger.debug(DebugMessages.SEARCH_PUBLICATIONS_BY_NAME_OK);
			return filteredPublications;
		} catch (PersistenceException e) {
			oLogger.error("Failed to get publication by name", e);
			throw new RepositoryException("publication.read");
		}

	}

	@Override
	public Publication getPublicationById(int id) {
		oLogger.debug(DebugMessages.GET_PUBLICATION_BY_ID);
		return entityManager.find(cls, id);
	}

}
