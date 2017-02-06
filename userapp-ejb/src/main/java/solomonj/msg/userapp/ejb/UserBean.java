package solomonj.msg.userapp.ejb;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.IUser;
import solomonj.msg.appuser.common.ServiceException;
import solomonj.msg.userapp.jpa.model.User;


@Stateless
public class UserBean implements IUser {


	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;
	
	private Logger oLogger = Logger.getLogger(UserBean.class);

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public void insertUser(User user) throws ServiceException {
		try {
			entityManager.persist(user);
		} catch (PersistenceException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException("User already exists");
		} 

	}

	@Override
	public void deleteUserById(int id) throws ServiceException {
		User user = entityManager.find(User.class, id);
		entityManager.remove(user);

	}

	@Override
	public List<User> searchUserByName(String name) {
		TypedQuery<User> query = entityManager.createQuery("Select u " + "from User u where u.username LIKE :name",
				User.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

	@Override
	public User searchUserById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {

			User userUpdate = entityManager.find(User.class, user.getId());
			userUpdate.setUserName(user.getUserName());
			userUpdate.setRoles(user.getRoles());
			entityManager.flush();
			
		} catch (PersistenceException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException("User's name already exists");

		} 
	

	}

}
