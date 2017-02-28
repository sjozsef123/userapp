package solomonj.msg.userapp.ejb.service.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.ejb.repository.IBookRepository;
import solomonj.msg.userapp.ejb.repository.IMagazineRepository;
import solomonj.msg.userapp.ejb.repository.INewspaperRepository;
import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
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
	@EJB
	private IBookRepository bookBean;
	@EJB
	private IMagazineRepository magazineBean;
	@EJB
	private INewspaperRepository newspaperBean;
	
	private Logger oLogger = Logger.getLogger(PublicationServiceBean.class);

	@Override
	public List<Publication> getAllPublication() throws ServiceException {
		try {
			oLogger.debug(DebugMessages.LIST_PUBLICATIONS);
			return publicationBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deletePublication(Publication publication) throws ServiceException {
		try {
			oLogger.debug(DebugMessages.DELETE_PUBLICATION);
			publicationBean.delete(publication);
			oLogger.debug(DebugMessages.DELETE_PUBLICATION_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Publication> getPublicationByFilter(PublicationFilter filter) throws ServiceException {
		List<Publication> filteredPublications = new ArrayList<>(); 
		
		try {
			oLogger.debug(DebugMessages.SEARCH_PUBLICATIONS_BY_NAME);

			if(Arrays.asList(filter.getType()).contains(new String("Book"))) {
				filteredPublications.addAll(bookBean.getByFilter(filter));
			}
			if(Arrays.asList(filter.getType()).contains(new String("Magazine"))) {
				filteredPublications.addAll(magazineBean.getByFilter(filter));
			}
			if(Arrays.asList(filter.getType()).contains(new String("Newspaper"))) {
				filteredPublications.addAll(newspaperBean.getByFilter(filter));
			}
			oLogger.debug(DebugMessages.SEARCH_PUBLICATIONS_BY_NAME_OK);
			return filteredPublications;
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void createPublication(Publication publication) throws ServiceException {
		try {
			oLogger.debug(DebugMessages.CREATE_PUBLICATION);
			publicationBean.create(publication);
			oLogger.debug(DebugMessages.CREATE_PUBLICATION_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public void updatePublication(Publication publication) throws ServiceException {
		try {
			oLogger.debug(DebugMessages.UPDATE_PUBLICATION);
			publicationBean.update(publication);
			oLogger.debug(DebugMessages.UPDATE_PUBLICATION_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
