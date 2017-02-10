package solomonj.msg.userapp.ejb.repository;

import java.util.List;

import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Article;

public interface IArticleRepository extends IBasicRepository<Article>{
	
	public List<Article>searchArticleByName(String name) throws RepositoryException;
}
