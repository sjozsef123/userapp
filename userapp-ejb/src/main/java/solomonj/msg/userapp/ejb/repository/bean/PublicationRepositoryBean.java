package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import solomonj.msg.userapp.ejb.repository.IPublicationRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.Publication_;

public abstract class PublicationRepositoryBean<T extends Publication> extends BasicRepositoryBean<T> implements IPublicationRepository<T>{

	@PersistenceContext
	private EntityManager entityManager;
	private Class<T> cls;
	
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
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = builder.createQuery(cls);
			Root<T> root = criteriaQuery.from(cls);
			
//				root.fetch("bAuthors", JoinType.LEFT);
//				root.fetch("mAuthors", JoinType.LEFT);	
//				root.fetch("articles", JoinType.LEFT);
			
			criteriaQuery.select(root).where(builder.like(root.get(Publication_.title), "%" + filter + "%")).distinct(true);
			
			filteredPublications = entityManager.createQuery(criteriaQuery).getResultList();
			return filteredPublications;
			
		} catch (PersistenceException e) {
			
			throw new RepositoryException();
			
		}
		
	}
	

}
