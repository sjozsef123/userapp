package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IAuthorService;
import solomonj.msg.userapp.ejb.repository.IAuthorRepository;
import solomonj.msg.userapp.ejb.repository.bean.AuthorRepositoryBean;
import solomonj.msg.userapp.ejb.repository.bean.UserRepositoryBean;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
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
	private Logger oLogger = Logger.getLogger(AuthorRepositoryBean.class);

	@Override
	public List<Author> getAllAuthors() throws ServiceException {
		try {
			oLogger.info("");
			return authorRepositoryBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertAuthor(Author author) throws ServiceException {
		try {
			oLogger.info("");
			authorRepositoryBean.create(author);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateAuthor(Author author) throws ServiceException {
		try {
			oLogger.info("");
			authorRepositoryBean.update(author);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteAuthor(Author author) throws ServiceException {
		try {
			oLogger.info("");
			authorRepositoryBean.delete(author);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Author> searchAuthorByName(String name) throws ServiceException {
		try {
			oLogger.info("");
			return authorRepositoryBean.searchAuthorByName(name);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

}
