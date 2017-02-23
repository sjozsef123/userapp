package solomonj.msg.userapp.ejb.repository;


import java.util.List;

import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Book;
/**
 * Interface for handling books. 
 * 
 * @author Majai Robert
 *
 */
 
public interface IBookRepository extends IPublicationRepository<Book> {
	
	List<Book> getByFilter(PublicationFilter filter) throws RepositoryException;
}
