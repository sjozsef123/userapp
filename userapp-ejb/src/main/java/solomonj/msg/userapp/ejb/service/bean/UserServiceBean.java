package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.IUserService;
import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

@Stateless
public class UserServiceBean implements IUserService {

	@EJB
	private IUserRepository userRepositoryBean;
	

	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			return userRepositoryBean.getAllUsers();
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertUser(User user) throws ServiceException {
		try {
			userRepositoryBean.insertUser(user);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteUserById(int id) throws ServiceException {
		try {
			userRepositoryBean.deleteUserById(id);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {
			userRepositoryBean.updateUser(user);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<User> searchUserByName(String name) throws ServiceException {
		try {
			return userRepositoryBean.searchUserByName(name);
		} catch (RepositoryException e) {			
			throw new ServiceException(e.getMessage());					
		}
	}

}
