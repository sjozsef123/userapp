package solomonj.msg.userapp.ejb.repository.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import solomonj.msg.userapp.ejb.repository.IRoleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Role;

@Stateless(name = "IRoleRepository", mappedName = "ejb/IRoleRepository")
public class RoleRepositoryBean implements IRoleRepository {

	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	@Override
	public List<Role> getRoles() {
		TypedQuery<Role> query = entityManager.createNamedQuery("Role.findAll", Role.class);
		return query.getResultList();
	}

	@Override
	public void deleteRole(int id) throws RepositoryException {

		Role role = entityManager.find(Role.class, id);
		if (role != null) {
			try {
				entityManager.remove(role);
				entityManager.flush();
			} catch (PersistenceException e) {
				throw new RepositoryException("Cannot delete role, there is user tied with it");
			}
		} else {
			throw new RepositoryException("Role with id=" + id + "not found");
		}

	}

	@Override
	public void insertRole(Role role) throws RepositoryException {
		try {
			entityManager.persist(role);
		} catch (PersistenceException e) {
			throw new RepositoryException("Role already exists");
		}

	}

}
