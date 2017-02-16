package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IMagazineRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Magazine_;

/**
 * This session bean manages the magazines.
 * 
 * @author Majai Robert
 *
 */
@Stateless
public class MagazineRepositoryBean extends PublicationRepositoryBean<Magazine> implements IMagazineRepository {

	
	@PersistenceContext
	private EntityManager entityManager;
	private Logger oLogger = Logger.getLogger(MagazineRepositoryBean.class);

	
	public MagazineRepositoryBean() {
		super(Magazine.class);
	}
	
	@Override
	public List<Magazine> filterMagazineByName(String filter) throws RepositoryException {

		List<Magazine> filteredMagazines = new ArrayList<>();
		
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Magazine> criteriaQuery = builder.createQuery(Magazine.class);
			Root<Magazine> root = criteriaQuery.from(Magazine.class);
			
			root.fetch("mAuthors", JoinType.LEFT);
			
			criteriaQuery.select(root).where(builder.like(root.get(Magazine_.title), "%" + filter + "%")).distinct(true);
			
			filteredMagazines = entityManager.createQuery(criteriaQuery).getResultList();
			return filteredMagazines;
			
		} catch (PersistenceException e) {
			
			oLogger.error("Failed to query magazines by filter.");
			throw new RepositoryException("magazine.read");
			
		}
	}

}
