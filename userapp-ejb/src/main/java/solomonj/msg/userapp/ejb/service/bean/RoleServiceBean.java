package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.DependsOn;
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
	return null;
		
	}

	@Override
	public void deleteRole(int id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertRole(Role role) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

}
