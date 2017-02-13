package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

public interface IUserRepository extends IBasicRepository<User> {
	
	/**
	 * This method searches for the User with the specified name.
	 * @param name
	 * @return users
	 * @throws RepositoryException
	 */
	public List<User> searchUserByName(String name) throws RepositoryException;
	/**
	 * This method searches for the User with the id.
	 * @param name
	 * @return users
	 * @throws RepositoryException
	 */
	public User getUserById(int id) throws RepositoryException;
	
	public void decreaseLoyaltyIndex(int id) throws RepositoryException;

	public User login(String name, String pass) throws RepositoryException;
}
