package solomonj.msg.userapp.ejb.repository.bean;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing_;

@Stateless
public class BorrowingRepositoryBean implements IBorrowingRepository {

	private static final long serialVersionUID = 376900431611702624L;

	@PersistenceContext(unitName = "userapp-jpa")
	private EntityManager entityManager;

	private CriteriaBuilder builder;

	private Logger oLogger = Logger.getLogger(UserRepositoryBean.class);

	public BorrowingRepositoryBean() {		
	}
	
	@PostConstruct
	private void setBuilder() {
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

			delete.where(builder.equal(borrowRoot.get(PublicationBorrowing_.id), borrowingPK));

			entityManager.createQuery(delete).executeUpdate();

			oLogger.info("Borrowing deleted.");
		} catch (PersistenceException e) {
			entityManager.getTransaction().rollback();
			oLogger.error("Could not delete a borrowing.", e);
			throw new RepositoryException("borrowingdelete");
		}
	}

	@Override
	public PublicationBorrowing getBorrowById(PublicationBorrowingPK borrowingPK) throws RepositoryException {

		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PublicationBorrowing> borrow = builder.createQuery(PublicationBorrowing.class);
			Root<PublicationBorrowing> borrowRoot = borrow.from(PublicationBorrowing.class);
     			
			borrow.select(borrowRoot);
			borrow.where(builder.and(builder.equal(borrowRoot.get(PublicationBorrowing_.id), borrowingPK)));
			TypedQuery<PublicationBorrowing> borrowQuery = entityManager.createQuery(borrow);
			
			oLogger.info("Borrow get was successful");
			entityManager.flush();
			return borrowQuery.getSingleResult();
		} catch (PersistenceException e) {
			oLogger.error("Failed to get borrow by id", e);
			throw new RepositoryException("borrowingread");
		}
	}

}
