package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.userapp.ejb.repository.IPublicationRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;

@Stateless
public class PublicationServiceBean implements IPublicationService {

	@EJB
	private IPublicationRepository<Publication> publicationBean;

	@Override
	public List<Publication> getAllPublication() throws ServiceException{

		try {
			return publicationBean.getlAll();
		} catch (RepositoryException e) {
			throw new ServiceException();
		}
	}

}
