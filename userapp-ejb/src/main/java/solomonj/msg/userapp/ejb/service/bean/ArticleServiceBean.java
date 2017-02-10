package solomonj.msg.userapp.ejb.service.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IArticleService;
import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Article;

@Stateless
public class ArticleServiceBean implements IArticleService {
	@EJB
	private IArticleRepository articleRepositoryBean;

	@Override
	public List<Article> getAllArticles() throws ServiceException {
		try {
			return articleRepositoryBean.getlAll();
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void insertArticle(Article article) throws ServiceException {
		try {
			articleRepositoryBean.create(article);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void updateArticle(Article article) throws ServiceException {
		try {
			articleRepositoryBean.update(article);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public void deleteArticle(Article article) throws ServiceException {
		try {
			articleRepositoryBean.delete(article);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<Article> searchArticleByName(String name) throws ServiceException {
		try {
			return articleRepositoryBean.searchArticleByName(name);
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
