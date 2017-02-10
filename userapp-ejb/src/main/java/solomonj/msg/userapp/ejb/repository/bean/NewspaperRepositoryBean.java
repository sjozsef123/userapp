package solomonj.msg.userapp.ejb.repository.bean;

import solomonj.msg.userapp.ejb.repository.INewspaperRepository;
import solomonj.msg.userapp.jpa.model.Newspaper;

public class NewspaperRepositoryBean extends PublicationRepositoryBean<Newspaper> implements INewspaperRepository{

	public NewspaperRepositoryBean() {
		super(Newspaper.class);
	}

}
