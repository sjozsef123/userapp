package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Author;

public interface IAuthorService {
	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/AuthorServiceBean";
	/**
	 * This function gets all the Authors from DB.
	 * 
	 * @return List of Authors
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<Author> getAllAuthors() throws  ServiceException;

	/**
	 * This method inserts an Author into DB.
	 * 
	 * @param author
	 * @throws ServiceException, when Server could not finish request
	 */
	public void insertAuthor(Author author) throws ServiceException;

	/**
	 * This method updates the Author.
	 * 
	 * @param author
	 * @throws ServiceException, when Server could not finish request
	 */
	public void updateAuthor(Author author) throws ServiceException;

	/**
	 * This method deletes the Author 
	 * 
	 * @param author
	 * @throws ServiceException, when Server could not finish request
	 */
	public void deleteAuthor(Author author) throws ServiceException;

	/**
	 * This method searches for an Author with the specified name.
	 * 
	 * @param name
	 * @return List<Author>
	 * @throws ServiceException, when Server could not finish request
	 */
	public List<Author> searchAuthorByName(String name) throws ServiceException;
}
