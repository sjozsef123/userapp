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
	 * @param fin
	 * @param start
	 * @return authors
	 * @throws RepositoryException
	 */
	public List<Author>searchAuthorByName(String name, int start, int fin) throws RepositoryException;

	/**
	 * This methid gets count of all AUTHORS with specified name.
	 *
	 * @return All author count
	 * @throws RepositoryException
	 */
	public int getCountAuthorsByName(String name) throws RepositoryException;
}
