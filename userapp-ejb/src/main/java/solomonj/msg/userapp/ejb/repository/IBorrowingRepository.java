package solomonj.msg.userapp.ejb.repository;

import java.io.Serializable;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;

/**
 * Interface for handling borrowings.
 * 
 * @author Simo Zoltan
 *
 */
public interface IBorrowingRepository extends Serializable {

	/**
	 * This function creates a new borrowing.
	 * 
	 * @param borrowing
	 * @throws RepositoryException
	 */
	void insertBorrowing(PublicationBorrowing borrowing) throws RepositoryException;

	/**
	 * This function deletes a borrowing.
	 * 
	 * @param borrowingPK
	 * @throws RepositoryException
	 */
	void deleteBorrowing(PublicationBorrowingPK borrowingPK) throws RepositoryException;

	/**
	 * This function returns a borrow by id when found.
	 * 
	 * @param borrowingPK
	 * @return
	 * @throws RepositoryException
	 */
	PublicationBorrowing getBorrowById(PublicationBorrowingPK borrowingPK) throws RepositoryException;

}
