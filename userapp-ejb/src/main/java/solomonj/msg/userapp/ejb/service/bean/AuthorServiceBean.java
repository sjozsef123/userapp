package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IAuthorService;
import solomonj.msg.userapp.ejb.repository.IAuthorRepository;
import solomonj.msg.userapp.ejb.repository.bean.AuthorRepositoryBean;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.InfoMessages;
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
			oLogger.info(InfoMessages.LIST_AUTHORS);
			return authorRepositoryBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertAuthor(Author author) throws ServiceException {
		try {
			oLogger.info(InfoMessages.CREATE_AUTHOR);
			authorRepositoryBean.create(author);
			oLogger.info(InfoMessages.CREATE_AUTHOR_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateAuthor(Author author) throws ServiceException {
		try {
			oLogger.info(InfoMessages.UPDATE_AUTHOR);
			authorRepositoryBean.update(author);
			oLogger.info(InfoMessages.UPDATE_AUTHOR_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteAuthor(Author author) throws ServiceException {
		try {
			oLogger.info(InfoMessages.DELETE_AUTHOR);
			authorRepositoryBean.delete(author);
			oLogger.info(InfoMessages.DELETE_AUTHOR_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Author> searchAuthorByName(String name) throws ServiceException {
		try {
			oLogger.info(InfoMessages.SEARCH_AUTHORS_BY_NAME);
			return authorRepositoryBean.searchAuthorByName(name);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

}
