package solomonj.msg.appuser.common;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;

public interface IBorrowingService {	
	
	public static final String jndiNAME="java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/BorrowingServiceBean";
	
	public void returnPublication(PublicationBorrowing borrowing) throws ServiceException;

	public void borrowPublication(PublicationBorrowing borrowing) throws ServiceException;
	
}
