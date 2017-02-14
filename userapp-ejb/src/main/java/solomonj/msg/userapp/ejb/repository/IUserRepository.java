package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

/**
 * Interface for handling users.
 * 
 * @author Solomon Jozsef
 * @author Simo Zoltan
 *
 */
public interface IUserRepository extends IBasicRepository<User> {

	/**
	 * This function searches for the User with the specified name.
	 * 
	 * @param name
	 * @return list
	 * @throws RepositoryException
	 */
	public List<User> searchUserByName(String name) throws RepositoryException;

	/**
	 * This function searches for the User with the id.
	 * 
	 * @param name
	 * @return user
	 * @throws RepositoryException
	 */
	public User getUserById(int id) throws RepositoryException;

	/**
	 * This function decreases the loyalty index of a user with given id.
	 * 
	 * @param id
	 * @throws RepositoryException
	 */
	public void decreaseLoyaltyIndex(int id) throws RepositoryException;

	/**
	 * This function implements login.
	 * 
	 * @param name
	 * @param pass
	 * @return user
	 * @throws RepositoryException
	 */
	public User login(String name, String pass) throws RepositoryException;
}
