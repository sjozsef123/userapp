package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.InfoMessages;
import solomonj.msg.userapp.jpa.model.Publication;

/**
 * Basic repository generic functions for publications.
 * 
 * @author Majai Robert
 * @author Solomon Jozsef
 *
 */
@Stateless
public class PublicationServiceBean implements IPublicationService {

	@EJB
	private IPubRepository publicationBean;

	private Logger oLogger = Logger.getLogger(PublicationServiceBean.class);

	@Override
	public List<Publication> getAllPublication() throws ServiceException {
		try {
			oLogger.info(InfoMessages.LIST_PUBLICATIONS);
			return publicationBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException("publication.read");
		}
	}

	@Override
	public void deletePublication(Publication publication) throws ServiceException {
		try {
			oLogger.info(InfoMessages.DELETE_PUBLICATION);
			publicationBean.delete(publication);
			oLogger.info(InfoMessages.DELETE_PUBLICATION_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException(publication.getClass().getSimpleName().toLowerCase() + ".delete");
		}
	}

	@Override
	public List<Publication> filterPublicationByName(String filter) throws ServiceException {
		try {
			oLogger.info(InfoMessages.SEARCH_PUBLICATIONS_BY_NAME);
			return publicationBean.filterPublicationByName(filter);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException("publication.read");
		}
	}

}
