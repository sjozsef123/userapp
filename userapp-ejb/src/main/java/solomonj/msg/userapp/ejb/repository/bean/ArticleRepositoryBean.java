package solomonj.msg.userapp.ejb.repository.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Article;
@Stateless
public class ArticleRepositoryBean extends BasicRepositoryBean<Article> implements IArticleRepository{
	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private Logger oLogger = Logger.getLogger(ArticleRepositoryBean.class);

	public ArticleRepositoryBean() {
		super(Article.class);
	}

	@Override
	public List<Article> searchArticleByName(String title) throws RepositoryException{
		TypedQuery<Article> query = entityManager.createQuery("Select a " + "from Article a where a.title LIKE :title",
				Article.class);
		query.setParameter("title", "%" + title + "%");
		return query.getResultList();
	}

}
