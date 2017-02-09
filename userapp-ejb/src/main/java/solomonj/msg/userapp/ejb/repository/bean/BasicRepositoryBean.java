package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import solomonj.msg.userapp.ejb.repository.IBasicRepository;
import solomonj.msg.userapp.jpa.model.BaseEntity;

public abstract class BasicRepositoryBean<T extends BaseEntity> implements IBasicRepository<T>{

	private Class<T> cls;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public BasicRepositoryBean(Class<T> cls) {
		this.cls = cls;
	}
	
	@Override
	public List<T> getlAll() {

		List<T> publications; 
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(cls);
		Root<T> root = criteriaQuery.from(cls);
		
		criteriaQuery.select(root);
		
		publications = entityManager.createQuery(criteriaQuery).getResultList();
		
		return (publications == null) ? new ArrayList<>() : publications;
	
	}

	@Override
	public void create(T t) {

		entityManager.persist(t);
		entityManager.flush();
	}

	@Override
	public void delete(T t) {

		T toDelete = entityManager.find(cls, t.getId());
		entityManager.remove(toDelete);
		entityManager.flush();
	}
	
	@Override
	public void update(T t) {
		
		entityManager.merge(t);
		entityManager.flush();
	}


}
