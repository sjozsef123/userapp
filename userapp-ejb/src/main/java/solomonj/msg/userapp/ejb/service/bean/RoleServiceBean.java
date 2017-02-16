package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IRoleService;
import solomonj.msg.userapp.ejb.repository.IRoleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Role;

/**
 * This session bean manages the roles.
 * 
 * @author Solomon Jozsef
 *
 */
@Stateless
public class RoleServiceBean implements IRoleService {

	@EJB
	private IRoleRepository roleRepositoryBean;
	private Logger oLogger = Logger.getLogger(RoleServiceBean.class);
	@Override
	public List<Role> getRoles() throws ServiceException {
		try {
			oLogger.debug(DebugMessages.LIST_ROLES);
			return roleRepositoryBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteRole(Role role) throws ServiceException {
		try {
			oLogger.debug(DebugMessages.DELETE_ROLE);
			roleRepositoryBean.delete(role);
			oLogger.debug(DebugMessages.DELETE_ROLE_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertRole(Role role) throws ServiceException {
		try {
			oLogger.debug(DebugMessages.CREATE_ROLE);
			roleRepositoryBean.create(role);
			oLogger.debug(DebugMessages.CREATE_ROLE_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

}