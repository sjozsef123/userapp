package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IArticleService;
import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.ejb.repository.bean.ArticleRepositoryBean;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Article;

/**
 * This session bean manages the articles.
 *
 * @author Szocs Csilla
 *
 */
@Stateless
public class ArticleServiceBean implements IArticleService {

	@EJB
	private IArticleRepository articleRepositoryBean;

	private final Logger oLogger = Logger.getLogger(ArticleRepositoryBean.class);

	@Override
	public List<Article> getAllArticles() throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.LIST_ARTICLES);
			return this.articleRepositoryBean.getlAll();
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertArticle(final Article article) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.CREATE_ARTICLE);
			this.articleRepositoryBean.create(article);
			this.oLogger.debug(DebugMessages.CREATE_ARTICLE_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateArticle(final Article article) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.UPDATE_ARTICLE);
			this.articleRepositoryBean.update(article);
			this.oLogger.debug(DebugMessages.UPDATE_ARTICLE_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteArticle(final Article article) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.DELETE_ARTICLE_OK);
			this.articleRepositoryBean.delete(article);
			this.oLogger.debug(DebugMessages.DELETE_ARTICLE_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Article> searchArticleByName(final String name, final int start, final int fin) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.SEARCH_ARTICLES_BY_NAME);
			return this.articleRepositoryBean.searchArticleByName(name, start, fin);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Article> getFreeArticles() throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.LIST_FREE_ARTICLES);
			return this.articleRepositoryBean.getFreeArticles();
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public int getCountArticlesByName(final String title) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.COUNT_ALL_ARTICLES);
			return this.articleRepositoryBean.getCountArticlesByName(title);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
