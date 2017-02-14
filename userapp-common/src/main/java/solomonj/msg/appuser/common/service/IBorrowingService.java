package solomonj.msg.appuser.common.service;

import java.io.Serializable;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;

/**
 * Interface for borrowing CRUD operations.
 * 
 * @author Simo Zoltan
 *
 */
public interface IBorrowingService extends Serializable {

	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/BorrowingServiceBean";

	/**
	 * This function returns a borrowed publication.
	 * 
	 * @param borrowingPK
	 * @throws ServiceException
	 */
	public void returnPublication(PublicationBorrowingPK borrowingPK) throws ServiceException;

	/**
	 * This function borrows a publication.
	 * 
	 * @param borrowing
	 * @throws ServiceException
	 */
	public void borrowPublication(PublicationBorrowing borrowing) throws ServiceException;

}
