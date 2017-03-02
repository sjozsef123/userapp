package solomonj.msg.userapp.ejb.service.bean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;
import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Publication;
import solomonj.msg.userapp.jpa.model.PublicationBorrowing;
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;
import solomonj.msg.userapp.jpa.model.User;

/**
 * BorrowingService test class.
 *
 * @author Simo Zoltan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BorrowingServiceBeanTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	public IUserRepository userRepositoryBean;

	@Mock
	public IPubRepository pubRepositoryBean;

	@Mock
	public IBorrowingRepository borrowingRepositoryBean;

	@InjectMocks
	public BorrowingServiceBean borrowingServiceBean;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReturnPublication_afterDeadlineUpdateUser() throws Exception{
		final Publication pM = new Magazine();
		final User u = new User();
		final PublicationBorrowing b = new PublicationBorrowing();
		b.setDeadline(new Date(1));
		pM.setCopiesLeft(0);
		u.setLoyaltyIndex(1);
		when(this.pubRepositoryBean.getPublicationById(anyInt())).thenReturn(pM);
		doNothing().when(this.pubRepositoryBean).update(any());
		when(this.borrowingRepositoryBean.getBorrowById(any())).thenReturn(b);
		when(this.userRepositoryBean.getUserById(anyInt())).thenReturn(u);
		doNothing().when(this.userRepositoryBean).update(any());
		doNothing().when(this.borrowingRepositoryBean).deleteBorrowing(any());

		final PublicationBorrowingPK pk = new PublicationBorrowingPK();
		pk.setPublicationId(0);
		pk.setUserId(0);
	    this.borrowingServiceBean.returnPublication(pk);

		verify(this.pubRepositoryBean, times(1)).getPublicationById(anyInt());
		verify(this.pubRepositoryBean, times(1)).update(any());
		verify(this.borrowingRepositoryBean, times(1)).getBorrowById(any());
		verify(this.userRepositoryBean, times(1)).getUserById(anyInt());
		verify(this.userRepositoryBean, times(1)).update(any());
		verify(this.borrowingRepositoryBean, times(1)).deleteBorrowing(any());
	}

	@Test(expected = ServiceException.class)
	public void testReturnPublication_onUpdateException() throws Exception{
		final Publication pM = new Magazine();
		pM.setCopiesLeft(0);
		when(this.pubRepositoryBean.getPublicationById(anyInt())).thenReturn(pM);
		doThrow(new RepositoryException("publication.read")).when(this.pubRepositoryBean).update(any());

		final PublicationBorrowingPK pk = new PublicationBorrowingPK();
		pk.setPublicationId(0);
		pk.setUserId(0);
	    this.borrowingServiceBean.returnPublication(pk);
	}

	@Test
	public void testBorrowPublication_successfulBorrow() throws Exception{
		final Publication pM = new Magazine();
		final User u = new User();
		final PublicationBorrowing b = new PublicationBorrowing();
		final PublicationBorrowingPK pk = new PublicationBorrowingPK();
		pk.setPublicationId(0);
		pk.setUserId(0);
		b.setId(pk);
		pM.setCopiesLeft(1);
		u.setLoyaltyIndex(1);
		when(this.pubRepositoryBean.getPublicationById(anyInt())).thenReturn(pM);
		doNothing().when(this.pubRepositoryBean).update(any());
		when(this.userRepositoryBean.getUserById(anyInt())).thenReturn(u);
		doNothing().when(this.borrowingRepositoryBean).insertBorrowing(any());

	    this.borrowingServiceBean.borrowPublication(b);

		verify(this.pubRepositoryBean, times(1)).getPublicationById(anyInt());
		verify(this.pubRepositoryBean, times(1)).update(any());
		verify(this.userRepositoryBean, times(1)).getUserById(anyInt());
		verify(this.borrowingRepositoryBean, times(1)).insertBorrowing(any());
	}

	@Test(expected = ServiceException.class)
	public void testBorrowPublication_lowLoyaltyException() throws Exception{
		final Publication pM = new Magazine();
		final User u = new User();
		final PublicationBorrowing b = new PublicationBorrowing();
		final PublicationBorrowingPK pk = new PublicationBorrowingPK();
		pk.setPublicationId(0);
		pk.setUserId(0);
		b.setId(pk);
		pM.setCopiesLeft(1);
		u.setLoyaltyIndex(0);
		when(this.pubRepositoryBean.getPublicationById(anyInt())).thenReturn(pM);
		when(this.userRepositoryBean.getUserById(anyInt())).thenReturn(u);

	    this.borrowingServiceBean.borrowPublication(b);
	}

	@Test(expected = ServiceException.class)
	public void testBorrowPublication_noCopiesLeftException() throws Exception{
		final Publication pM = new Magazine();
		final PublicationBorrowing b = new PublicationBorrowing();
		final PublicationBorrowingPK pk = new PublicationBorrowingPK();
		pk.setPublicationId(0);
		pk.setUserId(0);
		b.setId(pk);
		pM.setCopiesLeft(0);
		when(this.pubRepositoryBean.getPublicationById(anyInt())).thenReturn(pM);

	    this.borrowingServiceBean.borrowPublication(b);
	}

	@Test(expected = ServiceException.class)
	public void testBorrowPublication_onReadException() throws Exception{
		final Publication pM = new Magazine();
		final PublicationBorrowing b = new PublicationBorrowing();
		final PublicationBorrowingPK pk = new PublicationBorrowingPK();
		pk.setPublicationId(0);
		pk.setUserId(0);
		b.setId(pk);
		pM.setCopiesLeft(0);
		doThrow(new RepositoryException("publication.read")).when(this.pubRepositoryBean).getPublicationById(anyInt());

	    this.borrowingServiceBean.borrowPublication(b);
	}

}
