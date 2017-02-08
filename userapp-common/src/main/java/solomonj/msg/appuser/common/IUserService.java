package solomonj.msg.appuser.common;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.User;

public interface IUserService {

	public static final String jndiNAME="java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/UserServiceBean";
	
	public List<User> getAllUsers() throws ServiceException;
	
	public void insertUser(User user) throws ServiceException;
	
	public void deleteUserById(int id) throws ServiceException;
	
	public void updateUser(User user) throws ServiceException;
	
	public List<User> searchUserByName(String name) throws ServiceException;
}
