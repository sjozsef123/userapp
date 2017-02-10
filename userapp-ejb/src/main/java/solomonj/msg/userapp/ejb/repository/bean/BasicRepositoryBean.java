package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IBasicRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.BaseEntity;

public abstract class BasicRepositoryBean<T extends BaseEntity> implements IBasicRepository<T>{

	private Class<T> cls;
	private Logger oLogger;
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public BasicRepositoryBean(Class<T> cls) {
		this.cls = cls;
		 oLogger = Logger.getLogger(cls);
	}
	
	@Override
	public List<T> getlAll() throws RepositoryException {

		List<T> resultList; 

		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<T> criteriaQuery = builder.createQuery(cls);
			Root<T> root = criteriaQuery.from(cls);
			
			criteriaQuery.select(root);
			
			resultList = entityManager.createQuery(criteriaQuery).getResultList();
			return (resultList == null) ? new ArrayList<>() : resultList;
		} catch (PersistenceException e) {
			oLogger.error("Failed to query " + cls.getSimpleName() + "list.", e);
			throw new RepositoryException(cls.getSimpleName().toLowerCase() + ".read", e);
		}
	
	}

	@Override
	public void create(T t) throws RepositoryException {
		try {
			entityManager.persist(t);
			entityManager.flush();
		} catch (EntityExistsException  e) {
			
			oLogger.error("Duplicate entry for " + cls.getSimpleName(), e);
			throw new RepositoryException(cls.getSimpleName().toLowerCase() + ".create", e);
		} catch (PersistenceException e) {
			
			oLogger.error("Failed to create" + cls.getSimpleName(), e);
			throw new RepositoryException(cls.getSimpleName().toLowerCase() + ".create", e);
		}
	}

	@Override
	public void delete(T t) throws RepositoryException {
		
		try {
			T toDelete = entityManager.find(cls, t.getId());
			entityManager.remove(toDelete);
			entityManager.flush();
		} catch (PersistenceException e) {
			oLogger.error("Can't delete " + cls.getSimpleName(), e);
			throw new RepositoryException(cls.getSimpleName().toLowerCase() + ".delete", e);
		}
	}
	
	@Override
	public void update(T t) throws RepositoryException {
		
		try {
			entityManager.merge(t);
			entityManager.flush();
		} catch (PersistenceException e) {
			oLogger.error("Can't delete " + cls.getSimpleName(), e);
			throw new RepositoryException(cls.getSimpleName().toLowerCase() + ".update", e);
		}
	}


}
