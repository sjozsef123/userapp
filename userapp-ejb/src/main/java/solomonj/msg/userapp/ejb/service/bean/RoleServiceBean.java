package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IRoleService;
import solomonj.msg.userapp.ejb.repository.IRoleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
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

	@Override
	public List<Role> getRoles() throws ServiceException {
		try {
			return roleRepositoryBean.getlAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteRole(Role role) throws ServiceException {
		try {
			roleRepositoryBean.delete(role);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertRole(Role role) throws ServiceException {
		try {
			roleRepositoryBean.create(role);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

}