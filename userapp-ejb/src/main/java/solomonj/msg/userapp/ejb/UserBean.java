package solomonj.msg.userapp.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import solomonj.msg.appuser.common.IUser;
import solomonj.msg.userapp.jpa.model.User;

@Stateless
public class UserBean implements IUser {

	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;
	
	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = entityManager.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public void insertUser(User user) {
		entityManager.persist(user);
		
	}

	@Override
	public void deleteUserById(int id) {
		User user =entityManager.find(User.class, id);
		entityManager.remove(user);
		
	}

	@Override
	public List<User> searchUserByName(String name) {
		TypedQuery<User> query = entityManager.createQuery("Select u "
				+ "from User u where u.username LIKE :name", User.class );
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

	@Override
	public User searchUserById(int id) {
		return entityManager.find(User.class,id);
	}

	@Override
	public void updateUser(int id, String username) {
		User user =entityManager.find(User.class, id);
		user.setUsername(username);
		
	}

}
