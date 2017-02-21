package solomonj.msg.userapp.ejb.repository.bean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing_;
import solomonj.msg.userapp.jpa.model.User;
import solomonj.msg.userapp.jpa.model.User_;

/**
 * This session bean manages the users.
 * 
 * @author Solomon Jozsef
 * @author Simo Zoltan
 *
 */
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
			oLogger.debug(DebugMessages.SEARCH_USERS_BY_NAME);
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			root.fetch(User_.borrowings, JoinType.LEFT);
			criteriaQuery.select(root).where(builder.like(root.get("username"), "%" + name + "%")).distinct(true);

			resultList = entityManager.createQuery(criteriaQuery).getResultList();
			oLogger.debug(DebugMessages.SEARCH_USERS_BY_NAME_OK);
			return (resultList == null) ? new ArrayList<>() : resultList;
		} catch (PersistenceException e) {
			oLogger.error("Failed to query user list.", e);
			throw new RepositoryException("user.read");
		}
	}

	@Override
	public List<User> getAllBadUsers() throws RepositoryException {
		List<User> resultList;
		try {
			oLogger.debug(DebugMessages.LIST_USERS);
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);

			ListJoin<User, PublicationBorrowing> borrows = root.join(User_.borrowings, JoinType.LEFT);
			ParameterExpression<Date> d = builder.parameter(Date.class);
			Predicate predSize = builder.lessThanOrEqualTo(borrows.get(PublicationBorrowing_.deadline), d);

			criteriaQuery.select(root).where(predSize).distinct(true);
			resultList = entityManager.createQuery(criteriaQuery).setParameter(d, Date.valueOf(LocalDate.now()))
					.getResultList();

			oLogger.debug(DebugMessages.LIST_USERS_OK);
			return (resultList == null) ? new ArrayList<>() : resultList;
		} catch (PersistenceException e) {
			oLogger.error("Failed to query user list.", e);
			throw new RepositoryException("user.read");
		}
	}

	@Override
	public User getUserById(int id) {
		oLogger.debug(DebugMessages.GET_USER_BY_ID);
		return entityManager.find(User.class, id);
	}

	@Override
	public User login(String name, String pass) throws RepositoryException {
		try {
			oLogger.debug(name + DebugMessages.LOGIN_USER);
			User user;
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			root.fetch("borrowings", JoinType.LEFT);
			criteriaQuery.select(root).where(builder.and(builder.equal(root.get("username"), name)),
					builder.equal(root.get("password"), pass));

			user = entityManager.createQuery(criteriaQuery).getSingleResult();

			if (user == null) {
				oLogger.error("No user with given password and name.");
				throw new RepositoryException("user.login");
			}
			oLogger.debug(name + DebugMessages.LOGIN_USER_OK);
			return user;
		} catch (PersistenceException e) {
			oLogger.error("Failed to login user.", e);
			throw new RepositoryException("user.login");
		}
	}

}
