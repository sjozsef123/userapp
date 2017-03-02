package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;

/**
 * Interface for handling publications.
 *
 * @author Majai Robert
 * @author Solomon Jozsef
 * @param <T>
 */
public interface IPublicationRepository<T extends Publication> extends IBasicRepository<T> {

	/**
	 * This function searches for publications using namefilter.
	 *
	 * @param filter
	 * @return list
	 * @throws RepositoryException
	 */
	List<T> filterPublicationByName(String filter) throws RepositoryException;

	/**
	 * This function returns publication by id when found.
	 *
	 * @param id
	 * @return publication
	 */
	public Publication getPublicationById(int id) throws RepositoryException;
}
