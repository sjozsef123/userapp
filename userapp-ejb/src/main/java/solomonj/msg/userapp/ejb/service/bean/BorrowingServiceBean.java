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
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.util.DebugMessages;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;
import solomonj.msg.userapp.jpa.model.User;

/**
 * This session bean manages the borrowings.
 *
 * @author Simo Zoltan
 *
 */
@Stateless
public class BorrowingServiceBean implements IBorrowingService {

	private static final long serialVersionUID = 4993524182966520915L;

	@EJB
	private IUserRepository userRepositoryBean;

	@EJB
	private IPubRepository pubRepositoryBean;

	@EJB
	private IBorrowingRepository borrowingRepositoryBean;

	private final Logger oLogger = Logger.getLogger(BorrowingServiceBean.class);
	private int copiesLeft;

	@Override
	public void returnPublication(final PublicationBorrowingPK borrowingPK) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.DELETE_BORROWING);
			final Publication publication = this.pubRepositoryBean.getPublicationById(borrowingPK.getPublicationId());
			this.copiesLeft = publication.getCopiesLeft();
			publication.setCopiesLeft(this.copiesLeft + 1);
			this.pubRepositoryBean.update(publication);
			this.oLogger.debug(DebugMessages.UPDATE_BORROWING_PUBLICATION_OK);
			if (this.borrowingRepositoryBean.getBorrowById(borrowingPK).getDeadline()
					.before(Date.valueOf(LocalDate.now()))) {
				final User user = this.userRepositoryBean.getUserById(borrowingPK.getUserId());
				user.setLoyaltyIndex(user.getLoyaltyIndex() - 1);
				this.userRepositoryBean.update(user);
				this.oLogger.debug(DebugMessages.UPDATE_BORROWING_USER_OK);
			}
			this.borrowingRepositoryBean.deleteBorrowing(borrowingPK);
			this.oLogger.debug(DebugMessages.DELETE_BORROWING_OK);
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void borrowPublication(final PublicationBorrowing borrowing) throws ServiceException {
		try {
			this.oLogger.debug(DebugMessages.CREATE_BORROWING);
			final Publication publication = this.pubRepositoryBean.getPublicationById(borrowing.getId().getPublicationId());
			this.copiesLeft = publication.getCopiesLeft();
			if (this.copiesLeft > 0) {
				final int loyaltyIndex = this.userRepositoryBean.getUserById(borrowing.getId().getUserId()).getLoyaltyIndex();
				if (loyaltyIndex > 0) {
					publication.setCopiesLeft(this.copiesLeft - 1);
					this.pubRepositoryBean.update(publication);
					this.oLogger.debug(DebugMessages.UPDATE_BORROWING_PUBLICATION_OK);
					borrowing.setPublication(publication);
					this.borrowingRepositoryBean.insertBorrowing(borrowing);
					this.oLogger.debug(DebugMessages.CREATE_BORROWING_OK);
				} else {
					this.oLogger.error("borrowing.lowloyalty");
					throw new ServiceException("borrowing.lowloyalty");
				}
			} else {
				this.oLogger.error("borrowing.nocopiesleft");
				throw new ServiceException("borrowing.nocopiesleft");
			}
		} catch (final RepositoryException e) {
			this.oLogger.error(e.getClass() + e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}
}
