package solomonj.msg.userapp.ejb.service.bean;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;
import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;
import solomonj.msg.userapp.jpa.model.User;

/**
 * @author Jozsef Solomon
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BorrowingServiceTest {

	@InjectMocks
	BorrowingServiceBean borrowingService;

	@Mock
	IUserRepository userRepository;

	@Mock
	IPubRepository pubRepository;

	@Mock
	IBorrowingRepository borrowRepository;

	@Mock
	PublicationBorrowing borrowing;

	@Mock
	PublicationBorrowingPK pB;

	@Test(expected = ServiceException.class)
	public void returnPubAfterDeadline() throws Exception {

		Publication book = new Book();
		book.setCopiesLeft(1);
		User user = new User();
		user.setLoyaltyIndex(1);

		when(pubRepository.getPublicationById(anyInt())).thenReturn(book);
		doNothing().when(pubRepository).update(book);
		when(borrowing.getDeadline()).thenReturn(Date.valueOf(LocalDate.now().minusDays(1)));
		when(borrowRepository.getBorrowById(any())).thenReturn(borrowing);

		when(userRepository.getUserById(anyInt())).thenReturn(user);
		doNothing().when(userRepository).update(user);
		doThrow(new RepositoryException("")).when(borrowRepository).deleteBorrowing(any());
		PublicationBorrowingPK borrowingPK = new PublicationBorrowingPK();

		borrowingService.returnPublication(borrowingPK);

		assertEquals(2, book.getCopiesLeft());
		assertEquals(0, user.getLoyaltyIndex());

	}

	@Test(expected = ServiceException.class)
	public void borrowPubNoStock() throws Exception {
		Publication book = new Book();
		book.setCopiesLeft(0);
		when(borrowing.getId()).thenReturn(pB);
		when(pB.getPublicationId()).thenReturn(5);
		when(pubRepository.getPublicationById(anyInt())).thenReturn(book);
		borrowingService.borrowPublication(borrowing);

	}

	
	@Test(expected = ServiceException.class)
	public void borrowPubWithLowLoyalty() throws Exception {
		User user = new User();
		user.setLoyaltyIndex(0);
		Publication book = new Book();
		book.setCopiesLeft(1);
		when(borrowing.getId()).thenReturn(pB);
		when(pB.getPublicationId()).thenReturn(5);
		when(pubRepository.getPublicationById(anyInt())).thenReturn(book);
		when(pB.getUserId()).thenReturn(5);
		when(userRepository.getUserById(anyInt())).thenReturn(user);
		
		
		borrowingService.borrowPublication(borrowing);
		
	}
	
	@Test
	public void borrowPub() throws Exception {
		User user = new User();
		user.setLoyaltyIndex(1);
		Publication book = new Book();
		book.setCopiesLeft(1);
		when(borrowing.getId()).thenReturn(pB);
		when(pB.getPublicationId()).thenReturn(5);
		when(pubRepository.getPublicationById(anyInt())).thenReturn(book);
		when(pB.getUserId()).thenReturn(5);
		when(userRepository.getUserById(anyInt())).thenReturn(user);
		doNothing().when(pubRepository).update(any());
		doNothing().when(borrowRepository).insertBorrowing(borrowing);
		borrowingService.borrowPublication(borrowing);
		assertEquals(0, book.getCopiesLeft());
	}
}
