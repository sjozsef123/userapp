package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IUserService;
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
			return userRepositoryBean.getlAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertUser(User user) throws ServiceException {
		try {
			userRepositoryBean.create(user);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteUserById(User user) throws ServiceException {
		try {
			userRepositoryBean.delete(user);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		try {
			userRepositoryBean.update(user);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<User> searchUserByName(String name) throws ServiceException {
		try {			
			return userRepositoryBean.searchUserByName(name);			 
		} catch (Exception e) {			
			throw new ServiceException(e.getMessage());					
		}
	}

	@Override
	public User login(String name, String pass) throws ServiceException {
		//check name length password, encrypt
		try {			
			return userRepositoryBean.login(name, pass); 
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());		
		}
	}

}
