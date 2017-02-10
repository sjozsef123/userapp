package solomonj.msg.userapp.ejb.repository.bean;

import javax.ejb.Stateless;

import solomonj.msg.userapp.ejb.repository.IPublicationRepository;
import solomonj.msg.userapp.jpa.model.Publication;

@Stateless
public class PublicationRepositoryBean extends BasicRepositoryBean<Publication> implements IPublicationRepository<Publication>{

	public PublicationRepositoryBean() {
		super(Publication.class);
	}

}
