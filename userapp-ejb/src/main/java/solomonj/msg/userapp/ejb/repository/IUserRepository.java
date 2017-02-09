package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

public interface IUserRepository {
	
	public List<User> getAllUsers() throws RepositoryException;

	public void insertUser(User user) throws RepositoryException;
	
	public void deleteUserById(int id) throws RepositoryException;
	
	public void updateUser(User user) throws RepositoryException;
	
	public List<User> searchUserByName(String name) throws RepositoryException;
	
	public User getUserById(int id) throws RepositoryException;

}
