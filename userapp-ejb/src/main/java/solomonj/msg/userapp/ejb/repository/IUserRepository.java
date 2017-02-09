package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

public interface IUserRepository {
	
	public static final String jndiNAME="java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/UserRepositoryBean";
	
	public List<User> getAllUsers() throws RepositoryException;

	public void insertUser(User user) throws RepositoryException;
	
	public void deleteUserById(int id) throws RepositoryException;
	
	public void updateUser(User user) throws RepositoryException;
	
	public List<User> searchUserByName(String name) throws RepositoryException;

}
