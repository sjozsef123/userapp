package solomonj.msg.appuser.common.service;

import java.io.Serializable;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;

public interface IBorrowingService extends Serializable{	
	
	public static final String jndiNAME="java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/BorrowingServiceBean";
	
	public void returnPublication(PublicationBorrowingPK borrowingPK) throws ServiceException;

	public void borrowPublication(PublicationBorrowing borrowing) throws ServiceException;
	
}
