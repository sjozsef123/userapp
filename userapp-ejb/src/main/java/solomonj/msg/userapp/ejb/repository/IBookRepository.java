package solomonj.msg.userapp.ejb.repository;


import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Book;

public interface IBookRepository extends IPublicationRepository<Book>{
	
	//ide az egyeni metodusok
	List<Book> filterBookByName(String filter) throws RepositoryException;
}
