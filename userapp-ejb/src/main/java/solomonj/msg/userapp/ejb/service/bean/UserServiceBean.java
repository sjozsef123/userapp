package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import solomonj.msg.appuser.common.IUserService;
import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.User;

public class UserServiceBean implements IUserService{

	@Override
	public List<User> getUsers() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserById(int id) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> searchUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
