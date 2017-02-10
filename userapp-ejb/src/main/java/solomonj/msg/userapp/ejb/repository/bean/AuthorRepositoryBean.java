package solomonj.msg.userapp.ejb.repository.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IAuthorRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Author;
@Stateless
public class AuthorRepositoryBean extends BasicRepositoryBean<Author> implements IAuthorRepository {
	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private Logger oLogger = Logger.getLogger(AuthorRepositoryBean.class);

	public AuthorRepositoryBean() {
		super(Author.class);
	}

	@Override
	public List<Author> searchAuthorByName(String name) throws RepositoryException {
		TypedQuery<Author> query = entityManager.createQuery("Select a " + "from Author a where a.name LIKE :name ",
				Author.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

}
