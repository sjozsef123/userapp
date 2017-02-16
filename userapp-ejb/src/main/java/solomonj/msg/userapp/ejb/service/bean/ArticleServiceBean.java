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
import solomonj.msg.userapp.ejb.util.InfoMessages;
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

	private Logger oLogger = Logger.getLogger(ArticleRepositoryBean.class);

	@Override
	public List<Article> getAllArticles() throws ServiceException {
		try {
			oLogger.info(InfoMessages.LIST_ARTICLES);
			return articleRepositoryBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertArticle(Article article) throws ServiceException {
		try {
			oLogger.info(InfoMessages.CREATE_ARTICLE);
			articleRepositoryBean.create(article);
			oLogger.info(InfoMessages.CREATE_ARTICLE_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateArticle(Article article) throws ServiceException {
		try {
			oLogger.info(InfoMessages.UPDATE_ARTICLE);
			articleRepositoryBean.update(article);
			oLogger.info(InfoMessages.UPDATE_ARTICLE_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteArticle(Article article) throws ServiceException {
		try {
			oLogger.info(InfoMessages.DELETE_ARTICLE_OK);
			articleRepositoryBean.delete(article);
			oLogger.info(InfoMessages.DELETE_ARTICLE_OK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Article> searchArticleByName(String name) throws ServiceException {
		try {
			oLogger.info(InfoMessages.SEARCH_ARTICLES_BY_NAME);
			return articleRepositoryBean.searchArticleByName(name);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
