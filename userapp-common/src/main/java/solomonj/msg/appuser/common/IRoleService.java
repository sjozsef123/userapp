package solomonj.msg.appuser.common;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Role;

public interface IRoleService {

	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/RoleServiceBean";

	public List<Role> getRoles() throws ServiceException;

	public void deleteRole(int id) throws ServiceException;

	public void insertRole(Role role) throws ServiceException;
}