package solomonj.msg.userapp.ejb.service.bean;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import solomonj.msg.appuser.common.IBorrowingService;
import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;

@Stateless
public class BorrowingServiceBean implements IBorrowingService {

	@EJB
	private IUserRepository userRepositoryBean;
	
//	@EJB
//	private IPublication publicationRepositoryBean;
	
	@EJB
	private IBorrowingRepository borrowingRepositoryBean;
	
	
	@Override
	public void returnPublication(PublicationBorrowing borrowing) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrowPublication(PublicationBorrowing borrowing) throws ServiceException {
		try {			
//			int copiesLeft = publicationRepositoryBean.getPublicationByUuid(borrowing.getId().getPublicationId()).getCopiesLeft();
//			if (copiesLeft > 0) {
				int loyaltyIndex = userRepositoryBean.getUserById(borrowing.getId().getUserId()).getLoyaltyIndex();
//				if (loyaltyIndex > 0) {
//					publicationRepositoryBean.decreaseCopiesLeft(borrowing.getPublicationId());
//					borrowingRepositoryBean.insertBorrowing(borrowing);
//				} else {
//					//LOGGER.error("User Loyalty index is low, can't borrow Publication");
//					throw new ServiceException("User Loyalty index is low, can't borrow Publication");
//				}
//			} else {
//				//LOGGER.error("No copies left, can't borrow this Publication");
//				throw new ServiceException("No copies left, can't borrow this Publication");
//			}
		} catch (RepositoryException e) {
			//LOGGER.error("Can't Borrow publication!");
			throw new ServiceException(e.getMessage());
		}		
	}

}
