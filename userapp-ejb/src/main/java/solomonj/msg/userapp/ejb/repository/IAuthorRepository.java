package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Author;
/**
 * Interface for handling authors.
 * 
 * @author Szocs Csilla
 *
 */
public interface IAuthorRepository extends IBasicRepository<Author>{
	
	/**
	 * This method searches for Authors with the specified name.
	 * @param name
	 * @return authors
	 * @throws RepositoryException
	 */
	public List<Author>searchAuthorByName(String name) throws RepositoryException;
}
