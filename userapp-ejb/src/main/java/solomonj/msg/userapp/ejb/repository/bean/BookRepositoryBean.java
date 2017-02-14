package solomonj.msg.userapp.ejb.repository.bean;

import javax.ejb.Stateless;

import solomonj.msg.userapp.ejb.repository.IBookRepository;
import solomonj.msg.userapp.jpa.model.Book;

@Stateless
public class BookRepositoryBean extends PublicationRepositoryBean<Book> implements IBookRepository{

	public BookRepositoryBean() {
		super(Book.class);
	}
}
