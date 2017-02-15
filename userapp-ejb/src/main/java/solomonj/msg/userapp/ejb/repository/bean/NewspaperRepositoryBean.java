package solomonj.msg.userapp.ejb.repository.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.INewspaperRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Newspaper_;

@Stateless
public class NewspaperRepositoryBean extends PublicationRepositoryBean<Newspaper> implements INewspaperRepository{

	
	@PersistenceContext
	private EntityManager entityManager;
	private Logger oLogger = Logger.getLogger(NewspaperRepositoryBean.class);
	
	public NewspaperRepositoryBean() {
		super(Newspaper.class);
	}
	
	@Override
	public List<Newspaper> filterNewspaperByName(String filter) throws RepositoryException {

		List<Newspaper> filteredNewspapers = new ArrayList<>();
		
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Newspaper> criteriaQuery = builder.createQuery(Newspaper.class);
			Root<Newspaper> root = criteriaQuery.from(Newspaper.class);
			
			root.fetch("articles", JoinType.LEFT);
			
			criteriaQuery.select(root).where(builder.like(root.get(Newspaper_.title), "%" + filter + "%")).distinct(true);
			
			filteredNewspapers = entityManager.createQuery(criteriaQuery).getResultList();
			return filteredNewspapers;
			
		} catch (PersistenceException e) {
			
			oLogger.error("Failed to query newspapers by filter.");
			throw new RepositoryException("newspaper.read");
			
		}
	}
}
