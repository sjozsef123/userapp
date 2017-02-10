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

import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

@Stateless
public class UserRepositoryBean extends BasicRepositoryBean<User> implements IUserRepository {

	public UserRepositoryBean() {
		super(User.class);
	}

	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private Logger oLogger = Logger.getLogger(UserRepositoryBean.class);

	@Override
	public List<User> searchUserByName(String name) throws RepositoryException {	
		List<User> resultList;
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			root.fetch("borrowings", JoinType.LEFT);			
			criteriaQuery.select(root).where(builder.like(root.get("username"), "%"+name+"%")).distinct(true);
			
			resultList = entityManager.createQuery(criteriaQuery).getResultList();
			return (resultList == null) ? new ArrayList<>() : resultList;
		} catch (PersistenceException e) {
			oLogger.error("Failed to query user list.", e);
			throw new RepositoryException("user.read");
		}
	}

	@Override
	public User getUserById(int id) {
		return entityManager.find(User.class, id);
	}

}
