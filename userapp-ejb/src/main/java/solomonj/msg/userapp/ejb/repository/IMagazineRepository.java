package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Magazine;

/**
 * Interface for handling magazines.
 * 
 * @author Majai Robert
 *
 */
public interface IMagazineRepository extends IPublicationRepository<Magazine> {

	
	List<Magazine> filterMagazineByName(String filter) throws RepositoryException;
}
