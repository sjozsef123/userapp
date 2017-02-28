package solomonj.msg.userapp.ejb.repository.bean;

import javax.ejb.Stateless;

import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.jpa.model.Publication;

/**
 * This session bean manages the publications.
 *
 * @author Majai Robert
 *
 */
@Stateless
public class PubRepositoryBean extends PublicationRepositoryBean<Publication> implements IPubRepository {

	public PubRepositoryBean() {
		super(Publication.class);
	}
}
