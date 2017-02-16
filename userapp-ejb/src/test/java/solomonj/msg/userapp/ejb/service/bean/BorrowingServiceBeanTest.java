package solomonj.msg.userapp.ejb.service.bean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
import solomonj.msg.userapp.jpa.model.PublicationBorrowingPK;

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

	@Test(expected = ServiceException.class)
	public void testreturnPublication() throws Exception{
		Publication pM = new Magazine();
		pM.setCopiesLeft(0);
		when(pubRepositoryBean.getPublicationById(anyInt())).thenReturn(pM);
		doThrow(new RepositoryException("publication.read")).when(pubRepositoryBean).update(any());

		PublicationBorrowingPK pk = new PublicationBorrowingPK();
		pk.setPublicationId(0);
		pk.setUserId(0);		
	    borrowingServiceBean.returnPublication(pk);		

		verify(pubRepositoryBean, atLeastOnce()).getPublicationById(anyInt());		
		verify(pubRepositoryBean, atMost(1)).update(any());
		
	}

}
