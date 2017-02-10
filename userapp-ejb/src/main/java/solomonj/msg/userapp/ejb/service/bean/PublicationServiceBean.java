package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.userapp.ejb.repository.IPublicationRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;

@Stateless
public class PublicationServiceBean implements IPublicationService {

	@EJB
	private IPublicationRepository<Publication> publicationBean;
	private Logger oLogger = Logger.getLogger(PublicationServiceBean.class);
	
	
	@Override
	public List<Publication> getAllPublication() throws ServiceException{

		try {
			return publicationBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error("Failed to query publications");
			throw new ServiceException("publication.read");
		}
	}

	@Override
	public void deletePublication(Publication publication) throws ServiceException {

		try {
			publicationBean.delete(publication);
		} catch (RepositoryException e) {
			oLogger.equals("Failed to delete publication");
			throw new ServiceException("publication.delete");
		}
	}

}
