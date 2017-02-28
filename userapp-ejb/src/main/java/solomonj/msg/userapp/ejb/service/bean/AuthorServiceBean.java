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
import solomonj.msg.userapp.ejb.util.DebugMessages;
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
	private final Logger oLogger = Logger.getLogger(AuthorRepositoryBean.class);

	@Override
	public List<Author> getAllAuthors() throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.LIST_AUTHORS);
			return this.authorRepositoryBean.getlAll();
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void insertAuthor(final Author author) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.CREATE_AUTHOR);
			this.authorRepositoryBean.create(author);
			this.oLogger.debug(DebugMessages.CREATE_AUTHOR_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateAuthor(final Author author) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.UPDATE_AUTHOR);
			this.authorRepositoryBean.update(author);
			this.oLogger.debug(DebugMessages.UPDATE_AUTHOR_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteAuthor(final Author author) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.DELETE_AUTHOR);
			this.authorRepositoryBean.delete(author);
			this.oLogger.debug(DebugMessages.DELETE_AUTHOR_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Author> searchAuthorByName(final String name,final int start, final int fin) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.SEARCH_AUTHORS_BY_NAME);
			return this.authorRepositoryBean.searchAuthorByName(name, start, fin);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public int getCountAuthorsByName(final String name) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.COUNT_ALL_ARTICLES);
			return this.authorRepositoryBean.getCountAuthorsByName(name);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
