/**
 * 
 */
package solomonj.msg.userapp.ejb.service;

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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import solomonj.msg.userapp.ejb.repository.IBorrowingRepository;
import solomonj.msg.userapp.ejb.repository.IPubRepository;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.ejb.service.bean.BorrowingServiceBean;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Publication;

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
	public IPubRepository PubRepositoryBean;

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

	@Test // (expected = RepositoryException.class)
	public void testreturnPublication() {
		BorrowingServiceBean borrowingServiceBean = Mockito.mock(BorrowingServiceBean.class);
		Publication pM = new Magazine();
		pM.setCopiesLeft(5);
		Mockito.when(PubRepositoryBean.getPublicationById(Mockito.anyInt())).thenReturn(pM);
		try {
			Mockito.doThrow(new RepositoryException("publication.read")).when(PubRepositoryBean)
					.update(Mockito.anyObject());
		} catch (RepositoryException e) {
			Assert.assertTrue(true);
		}
		PubRepositoryBean.getPublicationById(0);

		Mockito.verify(PubRepositoryBean, Mockito.atLeastOnce()).getPublicationById(Mockito.anyInt());
	}

}
