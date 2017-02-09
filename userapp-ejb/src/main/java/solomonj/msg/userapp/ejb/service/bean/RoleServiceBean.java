package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.IRoleService;
import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IRoleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Role;

@Stateless
public class RoleServiceBean implements IRoleService {

	@EJB
	private IRoleRepository roleRepositoryBean;

	@Override
	public List<Role> getRoles() throws ServiceException {
		try {
			return roleRepositoryBean.getRoles();
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteRole(int id) throws ServiceException {
		try {
			roleRepositoryBean.deleteRole(id);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertRole(Role role) throws ServiceException {
		try {
			roleRepositoryBean.insertRole(role);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}