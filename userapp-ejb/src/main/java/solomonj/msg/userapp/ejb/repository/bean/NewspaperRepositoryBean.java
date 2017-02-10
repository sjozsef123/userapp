package solomonj.msg.userapp.ejb.repository.bean;

import javax.ejb.Stateless;

import solomonj.msg.userapp.ejb.repository.INewspaperRepository;
import solomonj.msg.userapp.jpa.model.Newspaper;

@Stateless
public class NewspaperRepositoryBean extends PublicationRepositoryBean<Newspaper> implements INewspaperRepository{

	public NewspaperRepositoryBean() {
		super(Newspaper.class);
	}
}
