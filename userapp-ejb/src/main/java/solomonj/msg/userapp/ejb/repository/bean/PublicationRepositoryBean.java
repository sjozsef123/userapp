package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import solomonj.msg.userapp.ejb.repository.IPublicationRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.Publication_;

@Stateless
public abstract class PublicationRepositoryBean<T extends Publication> extends BasicRepositoryBean<Publication> implements IPublicationRepository<Publication>{

	


	@PersistenceContext
	private EntityManager entityManager;
	
	public PublicationRepositoryBean(Class cls) {
		super(cls);
	}	
	
	@Override
	public List<Publication> filterPublicationByName(String filter) throws RepositoryException {

		List<Publication> filteredPublications = new ArrayList<>();
		
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Publication> criteriaQuery = builder.createQuery(Publication.class);
			Root<Publication> root = criteriaQuery.from(Publication.class);
			
			criteriaQuery.select(root).where(builder.equal(root.get(Publication_.title), filter));
			
			filteredPublications = entityManager.createQuery(criteriaQuery).getResultList();
			return filteredPublications;
			
		} catch (PersistenceException e) {
			
			throw new RepositoryException();
			
		}
		
	}
	

}
