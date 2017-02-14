package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Author;

/**
 * Interface for author CRUD operations.
 * 
 * @author Szocs Csilla
 *
 */
public interface IAuthorService {
	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/AuthorServiceBean";

	/**
	 * This function gets all the authors.
	 * 
	 * @return list
	 * @throws ServiceException
	 */
	public List<Author> getAllAuthors() throws ServiceException;

	/**
	 * This function inserts an author.
	 * 
	 * @param author
	 * @throws ServiceException
	 */
	public void insertAuthor(Author author) throws ServiceException;

	/**
	 * This function updates the author.
	 * 
	 * @param author
	 * @throws ServiceException
	 */
	public void updateAuthor(Author author) throws ServiceException;

	/**
	 * This function deletes the author
	 * 
	 * @param author
	 * @throws ServiceException
	 */
	public void deleteAuthor(Author author) throws ServiceException;

	/**
	 * This function searches for an author with the specified name.
	 * 
	 * @param name
	 * @return list
	 * @throws ServiceException
	 */
	public List<Author> searchAuthorByName(String name) throws ServiceException;
}
