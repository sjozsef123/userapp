package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IArticleService;
import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.ejb.repository.bean.ArticleRepositoryBean;
import solomonj.msg.userapp.ejb.repository.bean.UserRepositoryBean;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
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
			oLogger.info("");
			return articleRepositoryBean.getlAll();
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertArticle(Article article) throws ServiceException {
		try {
			articleRepositoryBean.create(article);
			oLogger.info("");
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateArticle(Article article) throws ServiceException {
		try {
			articleRepositoryBean.update(article);
			oLogger.info("");
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteArticle(Article article) throws ServiceException {
		try {
			articleRepositoryBean.delete(article);
			oLogger.info("");
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Article> searchArticleByName(String name) throws ServiceException {
		try {
			oLogger.info("");
			return articleRepositoryBean.searchArticleByName(name);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

}
