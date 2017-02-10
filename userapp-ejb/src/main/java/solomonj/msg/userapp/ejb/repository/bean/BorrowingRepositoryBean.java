package solomonj.msg.userapp.ejb.repository.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;

@Stateless
public class BorrowingRepositoryBean implements IBorrowingRepository{

	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;
	
	private CriteriaBuilder builder;
	
	private Logger oLogger = Logger.getLogger(UserRepositoryBean.class);
	
	public BorrowingRepositoryBean() {
		builder = entityManager.getCriteriaBuilder();	
	}	
	
	@Override
	public void insertBorrowing(PublicationBorrowing borrowing) throws RepositoryException {
		try {
			entityManager.merge(borrowing);
			entityManager.flush();
			oLogger.info("Borrowing inserted.");
		} catch (PersistenceException e) {
			oLogger.error("Could not insert borrowing.", e);
			throw new RepositoryException("borrowingcreate");
		}		
	}

	@Override
	public void deleteBorrowing(PublicationBorrowingPK borrowingPK) throws RepositoryException {
		try {						
			CriteriaDelete<PublicationBorrowing> delete = builder.createCriteriaDelete(PublicationBorrowing.class);
			
			Root<PublicationBorrowing> borrowRoot = delete.from(PublicationBorrowing.class);
					
			delete.where(builder.equal(borrowRoot.get("PublicationBorrowingPK"), borrowingPK));
			
			entityManager.createQuery(delete).executeUpdate();
			entityManager.getTransaction().commit();
			
			oLogger.info("Borrowing deleted.");			
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			oLogger.error("Could not delete a borrowing.", e);
			throw new RepositoryException("borrowingdelete");
		}		
	}



}
