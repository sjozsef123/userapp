package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

public interface IUserRepository extends IBasicRepository<User> {
	
	
	public List<User> searchUserByName(String name) throws RepositoryException;
	
	public User getUserById(int id) throws RepositoryException;
	
	public void decreaseLoyaltyIndex(int id) throws RepositoryException;

}
