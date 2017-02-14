package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Role;

/**
 * Interface for role CRUD operations.
 * 
 * @author Solomon Jozsef
 *
 */
public interface IRoleService {

	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/RoleServiceBean";

	/**
	 * This function gets all the roles.
	 * 
	 * @return list
	 * @throws ServiceException
	 */
	public List<Role> getRoles() throws ServiceException;

	/**
	 * This function deletes the role.
	 * 
	 * @param role
	 * @throws ServiceException
	 */
	public void deleteRole(Role role) throws ServiceException;

	/**
	 * This function creates new role.
	 * 
	 * @param role
	 * @throws ServiceException
	 */
	public void insertRole(Role role) throws ServiceException;
}