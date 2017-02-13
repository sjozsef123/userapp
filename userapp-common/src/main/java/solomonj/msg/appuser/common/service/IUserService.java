package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.User;

/**
 * interface for User CRUD operations.
 * @author 
 *
 */
public interface IUserService {

	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/UserServiceBean";
	/**
	 * This function gets all the Users from DB.
	 * @return users
	 * @throws ServiceException ServiceException, when Server could not finish request
	 */
	public List<User> getAllUsers() throws ServiceException;
	/**
	 * This method inserts a User into DB.
	 * @param user
	 * @throws ServiceException, when Server could not finish request
	 */
	public void insertUser(User user) throws ServiceException;
	/**
	 * This method deletes the User 
	 * @param user
	 * @throws ServiceException, when Server could not finish request
	 */
	public void deleteUserById(User user) throws ServiceException;
	/**
	 * This method updates the User 
	 * @param user
	 * @throws ServiceException, when Server could not finish request
	 */
	public void updateUser(User user) throws ServiceException;
	/**
	 * This method searches for the User with the specified name.
	 * @param name
	 * @return users
	 * @throws RepositoryException
	 */
	public List<User> searchUserByName(String name) throws ServiceException;
}
