package solomonj.msg.userapp.ejb.repository.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

@Stateless
public class UserRepositoryBean implements IUserRepository {

	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private Logger oLogger = Logger.getLogger(UserRepositoryBean.class);

	@Override
	public List<User> getAllUsers() throws RepositoryException {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public void insertUser(User user) throws RepositoryException {
		try {
			entityManager.persist(user);
		} catch (PersistenceException e) {
			oLogger.error(e.getMessage());
			throw new RepositoryException("User already exists");
		}

	}

	@Override
	public void deleteUserById(int id) throws RepositoryException {

		User user = entityManager.find(User.class, id);
		entityManager.remove(user);

	}

	@Override
	public List<User> searchUserByName(String name) throws RepositoryException {
		TypedQuery<User> query = entityManager.createQuery("Select u " + "from User u where u.username LIKE :name",
				User.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

	@Override
	public void updateUser(User user) throws RepositoryException {
		try {

			User userUpdate = entityManager.find(User.class, user.getId());
			userUpdate.setUsername(user.getUsername());
			userUpdate.setRoles(user.getRoles());
			entityManager.flush();

		} catch (PersistenceException e) {
			oLogger.error(e.getMessage());
			throw new RepositoryException("User's name already exists");

		}

	}

}
