package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.PublicationStat;

/**
 * Interface for publication CRUD operations.
 * 
 * @author Majai Robert
 * @author Solomon Jozsef
 *
 */
public interface IPublicationService {

	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/PublicationServiceBean";

	/**
	 * This function gets all the publications.
	 * 
	 * @return list
	 * @throws ServiceException
	 */
	List<Publication> getAllPublication() throws ServiceException;

	/**
	 * This function deletes the publication.
	 * 
	 * @param publication
	 * @throws ServiceException
	 */
	void deletePublication(Publication publication) throws ServiceException;

	/**
	 * This function searches for publications using namefilter.
	 * 
	 * @param filter
	 * @return list
	 * @throws ServiceException
	 */
	List<Publication> getPublicationByFilter(PublicationFilter filter) throws ServiceException;
	
	void createPublication(Publication publication) throws ServiceException;
	void updatePublication(Publication publication) throws ServiceException;
	
	public List<PublicationStat> getPubStat() throws ServiceException;
}
