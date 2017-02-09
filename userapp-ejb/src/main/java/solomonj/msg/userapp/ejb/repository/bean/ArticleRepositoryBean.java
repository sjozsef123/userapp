package solomonj.msg.userapp.ejb.repository.bean;

import solomonj.msg.userapp.ejb.repository.IArticleRepository;
import solomonj.msg.userapp.jpa.model.Article;

public class ArticleRepositoryBean extends BasicRepositoryBean<Article> implements IArticleRepository{

	public ArticleRepositoryBean() {
		super(Article.class);
	}

}
