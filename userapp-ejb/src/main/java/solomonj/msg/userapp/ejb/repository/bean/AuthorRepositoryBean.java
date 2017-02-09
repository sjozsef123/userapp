package solomonj.msg.userapp.ejb.repository.bean;

import solomonj.msg.userapp.ejb.repository.IAuthorRepository;
import solomonj.msg.userapp.jpa.model.Author;

public class AuthorRepositoryBean extends BasicRepositoryBean<Author>implements IAuthorRepository{

	public AuthorRepositoryBean() {
		super(Author.class);
	}

}
