package solomonj.msg.appuser.common.service;

import java.util.List;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Publication;

public interface IPublicationService {

	public static final String jndiNAME = "java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/PublicationServiceBean";
	
	List<Publication> getAllPublication() throws ServiceException;
	void deletePublication(Publication publication) throws ServiceException;
	List<Publication> filterPublicationByName(String filter) throws ServiceException;
	void createPublication(Publication publication) throws ServiceException;
}
