package solomonj.msg.userapp.ejb.repository.bean;

import solomonj.msg.userapp.ejb.repository.IBookRepository;
import solomonj.msg.userapp.jpa.model.Book;

public class BookRepositoryBean extends BasicRepositoryBean<Book> implements IBookRepository{

	public BookRepositoryBean() {
		super(Book.class);
	}
}
