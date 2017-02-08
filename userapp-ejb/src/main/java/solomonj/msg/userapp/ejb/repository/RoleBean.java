package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import solomonj.msg.appuser.common.IRole;
import solomonj.msg.appuser.common.ServiceException;
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
	public void deleteRole(int id) throws ServiceException {

		Role role = entityManager.find(Role.class, id);
		if (role != null) {
			try {
				entityManager.remove(role);
				entityManager.flush();
			} catch (PersistenceException e) {
				throw new ServiceException("Cannot delete role, there is user tied with it");
			}
		} else {
			throw new ServiceException("Role with id=" + id + "not found");
		}

	}

	@Override
	public void insertRole(Role role) throws ServiceException {
		try {
			entityManager.persist(role);
		} catch (PersistenceException e) {
			throw new ServiceException("Role already exists");
		}

	}

}
