package solomonj.msg.userapp.ejb.service.bean;

import java.sql.Date;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IBorrowingService;
import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;
import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.bean.UserRepositoryBean;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;
import solomonj.msg.userapp.jpa.model.User;

@Stateless
public class BorrowingServiceBean implements IBorrowingService {

	private static final long serialVersionUID = 4993524182966520915L;

	@EJB
	private IUserRepository userRepositoryBean;

	@EJB
	private IPubRepository PubRepositoryBean;

	@EJB
	private IBorrowingRepository borrowingRepositoryBean;

	private Logger oLogger = Logger.getLogger(UserRepositoryBean.class);
	private int copiesLeft;

	@Override
	public void returnPublication(PublicationBorrowingPK borrowingPK) throws ServiceException {
		try {
			Publication publication = PubRepositoryBean.getPublicationById(borrowingPK.getPublicationId());
		    copiesLeft = publication.getCopiesLeft();
			publication.setCopiesLeft(copiesLeft + 1);
			PubRepositoryBean.update(publication);			
			if (borrowingRepositoryBean.getBorrowById(borrowingPK).getDeadline()
					.before(Date.valueOf(LocalDate.now()))) {
				userRepositoryBean.decreaseLoyaltyIndex(borrowingPK.getUserId());
			}
			borrowingRepositoryBean.deleteBorrowing(borrowingPK);
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void borrowPublication(PublicationBorrowing borrowing) throws ServiceException {
		try {
			Publication publication = PubRepositoryBean.getPublicationById(borrowing.getId().getPublicationId());
			copiesLeft = publication.getCopiesLeft();
			if (copiesLeft > 0) {
				int loyaltyIndex = userRepositoryBean.getUserById(borrowing.getId().getUserId()).getLoyaltyIndex();
				if (loyaltyIndex > 0) {
					publication.setCopiesLeft(copiesLeft - 1);
					PubRepositoryBean.update(publication); 					
					borrowing.setPublication(publication);
					borrowingRepositoryBean.insertBorrowing(borrowing);
				} else {
					oLogger.error("User Loyalty index is low, can't borrow Publication");
					throw new ServiceException("User Loyalty index is low, can't borrow Publication");
				}
			} else {
				oLogger.error("No copies left, can't borrow this Publication");
				throw new ServiceException("No copies left, can't borrow this Publication");
			}
		} catch (RepositoryException e) {
			oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
}
