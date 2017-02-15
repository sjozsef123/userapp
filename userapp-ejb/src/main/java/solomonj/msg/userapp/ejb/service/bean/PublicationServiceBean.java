package solomonj.msg.userapp.ejb.service.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.userapp.ejb.repository.IBookRepository;
import solomonj.msg.userapp.ejb.repository.IMagazineRepository;
import solomonj.msg.userapp.ejb.repository.INewspaperRepository;
import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;

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
	public List<Publication> getAllPublication() throws ServiceException{

		try {
			return publicationBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException("publication.read");
		}
	}

	@Override
	public void deletePublication(Publication publication) throws ServiceException {

		try {
			publicationBean.delete(publication);
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException(publication.getClass().getSimpleName().toLowerCase() + ".delete");
		}
	}

	@Override
	public List<Publication> filterPublicationByName(String filter) throws ServiceException {

		List<Publication> filteredPublications = new ArrayList<>(); 
		
		try {
			filteredPublications.addAll(bookBean.filterBookByName(filter));
			filteredPublications.addAll(magazineBean.filterMagazineByName(filter));
			filteredPublications.addAll(newspaperBean.filterNewspaperByName(filter));
			return filteredPublications;
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException("publication.read");
		}
	}

	@Override
	public void createPublication(Publication publication) throws ServiceException {

		try {
			publicationBean.create(publication);
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException("publication.create");
		}
	}
	
	@Override
	public void updatePublication(Publication publication) throws ServiceException {

		try {
			publicationBean.update(publication);
		} catch (RepositoryException e) {
			oLogger.error(e.getMessage());
			throw new ServiceException("publication.update");
		}
	}

}
