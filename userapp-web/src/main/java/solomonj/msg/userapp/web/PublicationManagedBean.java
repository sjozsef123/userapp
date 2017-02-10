package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.userapp.jpa.model.Publication;

@Named("publicationmanagedbean")
@ApplicationScoped
public class PublicationManagedBean implements Serializable {

	private static final long serialVersionUID = 1565015472267456236L;

	private IPublicationService publicationBean;
	private List<Publication> publicationList;
	
	
	public IPublicationService getpublicationBean() {
		if (publicationBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				publicationBean = (IPublicationService) jndi.lookup(IPublicationService.jndiNAME);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return publicationBean;
	}


	public String getType(Publication pub) {
		return pub.getClass().getSimpleName();
	}

	public List<Publication> getPublicationList() {
		try {
			publicationList = getpublicationBean().getAllPublication();
			return publicationList;
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}

}
