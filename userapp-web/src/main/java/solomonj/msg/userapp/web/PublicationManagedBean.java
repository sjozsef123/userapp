package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.userapp.jpa.model.Publication;

@Named("publicationmanagedbean")
@SessionScoped
public class PublicationManagedBean implements Serializable {

	private static final long serialVersionUID = 1565015472267456236L;

	private Logger oLogger = Logger.getLogger(PublicationManagedBean.class);
	private IPublicationService publicationBean;
	private List<Publication> publicationList;
	private String filter = "";
	
	public IPublicationService getpublicationBean() {
		if (publicationBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				publicationBean = (IPublicationService) jndi.lookup(IPublicationService.jndiNAME);
			} catch (NamingException e) {

				oLogger.error("Can't get bean");
			}
		}
		return publicationBean;
	}

	public String getType(Publication pub) {
		return pub.getClass().getSimpleName();
	}
	
	public void deletePublication(Publication publication) {
		
		try {
			
			publicationBean.deletePublication(publication);
		} catch (ServiceException e) {
			
			oLogger.error("Failed to delete publication");
		}
	}

	public List<Publication> getPublicationList() {
		
		publicationList = new ArrayList<>();
		
		try {
			publicationList = getpublicationBean().filterPublicationByName(filter);
			return publicationList;
		} catch (ServiceException e) {
			
			oLogger.equals("Failed to query publication list.");
			return publicationList;
		}
	}

	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}
	
	public void add(Publication publication) {
			
	}
	
	public void test(String a) {
		System.out.println("retek");
			System.out.println(a);
	}


	public String getFilter() {
		return filter;
	}


	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	public void clearFilter() {
		
		filter = "";
	}
	
}
