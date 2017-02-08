package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import javax.ejb.Local;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Role;

@Local
public interface IRoleRepository {
	
	//public static final String jndiNAME="java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/RoleRepositoryBean";
	
	public List<Role> getRoles();
	
	public void deleteRole(int id) throws RepositoryException;
	
	public void insertRole(Role role) throws RepositoryException;
}
