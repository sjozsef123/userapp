package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IAuthorService;
import solomonj.msg.userapp.ejb.repository.IAuthorRepository;
import solomonj.msg.userapp.jpa.model.Author;

/**
 * This session bean manages the authors.
 * 
 * @author Szocs Csilla
 *
 */
@Stateless
public class AuthorServiceBean implements IAuthorService {
	@EJB
	private IAuthorRepository authorRepositoryBean;

	@Override
	public List<Author> getAllAuthors() throws ServiceException {
		try {
			return authorRepositoryBean.getlAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertAuthor(Author author) throws ServiceException {
		try {
			authorRepositoryBean.create(author);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateAuthor(Author author) throws ServiceException {
		try {
			authorRepositoryBean.update(author);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteAuthor(Author author) throws ServiceException {
		try {
			authorRepositoryBean.delete(author);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Author> searchAuthorByName(String name) throws ServiceException {
		try {
			return authorRepositoryBean.searchAuthorByName(name);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}

	}

}
