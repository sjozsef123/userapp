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
public class UserRepositoryBean extends BasicRepositoryBean<User>  implements IUserRepository {

	public UserRepositoryBean() {
		super(User.class);
	
	}


	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private Logger oLogger = Logger.getLogger(UserRepositoryBean.class);


	@Override
	public List<User> searchUserByName(String name) throws RepositoryException {
		TypedQuery<User> query = entityManager.createQuery("Select u " + "from User u where u.username LIKE :name",
				User.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}



}
