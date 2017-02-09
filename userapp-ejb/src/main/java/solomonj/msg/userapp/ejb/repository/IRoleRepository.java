package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import javax.ejb.Local;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Role;

@Local
public interface IRoleRepository {	
		
	public List<Role> getRoles() throws RepositoryException;
	
	public void deleteRole(int id) throws RepositoryException;
	
	public void insertRole(Role role) throws RepositoryException;
}
