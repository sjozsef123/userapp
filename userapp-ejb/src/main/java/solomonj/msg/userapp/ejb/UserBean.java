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
	public void insertUser(String username) {
		User user = new User();
		user.setUsername(username);
		
		entityManager.persist(user);
	
		
	}

}
