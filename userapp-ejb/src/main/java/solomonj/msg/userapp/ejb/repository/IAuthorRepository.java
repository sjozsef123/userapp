package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Author;

public interface IAuthorRepository extends IBasicRepository<Author>{
	/**
	 * This method searches for the Author with the specified name.
	 * @param name
	 * @return authors
	 * @throws RepositoryException
	 */
	public List<Author>searchAuthorByName(String name) throws RepositoryException;
}
