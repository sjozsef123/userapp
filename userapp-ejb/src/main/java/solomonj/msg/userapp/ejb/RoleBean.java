package solomonj.msg.userapp.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import solomonj.msg.appuser.common.IRole;
import solomonj.msg.userapp.jpa.model.Role;


@Stateless
public class RoleBean implements IRole {


	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;
	
	@Override
	public List<Role> getRoles() {
		TypedQuery<Role> query = entityManager.createNamedQuery("Role.findAll", Role.class);
		return query.getResultList();
	}

	@Override
	public void deleteRole(int id) {
		
		Role role = entityManager.find(Role.class, id);
		entityManager.remove(role);
		
	}

	@Override
	public void insertRole(Role role) {
		entityManager.persist(role);
		
	}
	
	

}
