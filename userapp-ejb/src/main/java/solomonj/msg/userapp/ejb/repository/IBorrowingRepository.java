package solomonj.msg.userapp.ejb.repository;

import java.io.Serializable;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;

public interface IBorrowingRepository extends Serializable{
	
	void insertBorrowing(PublicationBorrowing borrowing) throws RepositoryException;
	
	void deleteBorrowing(PublicationBorrowingPK borrowingPK) throws RepositoryException;
	
	PublicationBorrowing getBorrowById(PublicationBorrowingPK borrowingPK) throws RepositoryException;
	
}
