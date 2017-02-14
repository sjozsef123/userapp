package solomonj.msg.userapp.ejb.repository;

import java.util.List;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.BaseEntity;

/**
 * Basic repository generic functions.
 * 
 * @author Majai Robert
 * @author Solomon Jozsef
 * @param <T>
 */
public interface IBasicRepository<T extends BaseEntity> {

	/**
	 * This function gets all the entities.
	 * 
	 * @return list
	 * @throws RepositoryException
	 */
	List<T> getlAll() throws RepositoryException;

	/**
	 * This function creates a new entity.
	 * 
	 * @param t
	 * @throws RepositoryException
	 */
	void create(T t) throws RepositoryException;

	/**
	 * This function deletes the entity.
	 * 
	 * @param t
	 * @throws RepositoryException
	 */
	void delete(T t) throws RepositoryException;

	/**
	 * This function updates the entity.
	 * 
	 * @param t
	 * @throws RepositoryException
	 */
	void update(T t) throws RepositoryException;
}
