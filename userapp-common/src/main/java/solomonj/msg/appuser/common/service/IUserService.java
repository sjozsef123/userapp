package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.User;

/**
 * Interface for user CRUD operations.
 * 
 * @author Solomon Jozsef
 *
 */
public interface IUserService {

	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/UserServiceBean";

	/**
	 * This function gets all the users;
	 * 
	 * @return list
	 * @throws ServiceException
	 */
	public List<User> getAllUsers() throws ServiceException;

	/**
	 * This function inserts a user.
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void insertUser(User user) throws ServiceException;

	/**
	 * This function deletes the user.
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void deleteUserById(User user) throws ServiceException;

	/**
	 * This function updates the user
	 * 
	 * @param user
	 * @throws ServiceException
	 */
	public void updateUser(User user) throws ServiceException;

	/**
	 * This function searches for the user with the specified name.
	 * 
	 * @param name
	 * @return users
	 * @throws ServiceException
	 */
	public List<User> searchUserByName(String name) throws ServiceException;

	/**
	 * This function implements login.
	 * 
	 * @param name
	 * @param pass
	 * @return user
	 * @throws ServiceException
	 */
	public User login(String name, String pass) throws ServiceException;
}
