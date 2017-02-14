package solomonj.msg.userapp.ejb.repository.bean;

import javax.ejb.Stateless;

import solomonj.msg.userapp.ejb.repository.IMagazineRepository;
import solomonj.msg.userapp.jpa.model.Magazine;

/**
 * This session bean manages the magazines.
 * 
 * @author Majai Robert
 *
 */
@Stateless
public class MagazineRepositoryBean extends PublicationRepositoryBean<Magazine> implements IMagazineRepository {

	public MagazineRepositoryBean() {
		super(Magazine.class);
	}

}
