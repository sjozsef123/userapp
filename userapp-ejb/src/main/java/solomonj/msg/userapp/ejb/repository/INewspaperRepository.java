package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Newspaper;

/**
 * Interface for handling newspapers.
 * 
 * @author Majai Robert
 *
 */
public interface INewspaperRepository extends IPublicationRepository<Newspaper> {

	List<Newspaper> getByFilter(PublicationFilter filter) throws RepositoryException;
}
