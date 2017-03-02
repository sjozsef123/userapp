package solomonj.msg.userapp.ejb.service.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.ejb.repository.IBookRepository;
import solomonj.msg.userapp.ejb.repository.IMagazineRepository;
import solomonj.msg.userapp.ejb.repository.INewspaperRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Publication;

@RunWith(MockitoJUnitRunner.class)
public class PublicationServiceBeanTest {

	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	public IBookRepository bookBean;
	
	@Mock
	public IMagazineRepository magazineBean;
	
	@Mock
	public INewspaperRepository newspaperBean;
	
	@InjectMocks
	public PublicationServiceBean publicationServiceBean;
	
	@Test
	public void testGetPublicationByFilterBook() throws Exception{

		PublicationFilter filter = new PublicationFilter();
		String[] type = new String[1];
		type[0] = new String("Book");
		filter.setType(type);
		
		List<Book> bookList = new ArrayList<>();
		bookList.add(new Book());
		bookList.add(new Book());
		bookList.add(new Book());
		
		assertTrue(Arrays.asList(filter.getType()).contains(new String("Book")));
		assertFalse(Arrays.asList(filter.getType()).contains(new String("Magazine")));
		assertFalse(Arrays.asList(filter.getType()).contains(new String("Newspaper")));
		when(bookBean.getByFilter(filter)).thenReturn(bookList);
		
		List<Publication> pubs = publicationServiceBean.getPublicationByFilter(filter);
		
		assertEquals(3, pubs.size());

		verify(bookBean, atLeastOnce()).getByFilter(filter);		
		
	}
	
	@Test
	public void testGetPublicationByFilterMagazine() throws Exception{

		PublicationFilter filter = new PublicationFilter();
		String[] type = new String[1];
		type[0] = new String("Magazine");
		filter.setType(type);
		
		List<Magazine> magazineList = new ArrayList<>();
		magazineList.add(new Magazine());
		magazineList.add(new Magazine());
		magazineList.add(new Magazine());
		
		assertFalse(Arrays.asList(filter.getType()).contains(new String("Book")));
		assertTrue(Arrays.asList(filter.getType()).contains(new String("Magazine")));
		assertFalse(Arrays.asList(filter.getType()).contains(new String("Newspaper")));
		when(magazineBean.getByFilter(filter)).thenReturn(magazineList);
		
		List<Publication> pubs = publicationServiceBean.getPublicationByFilter(filter);
		assertEquals(3, pubs.size());
		
		verify(magazineBean, atLeastOnce()).getByFilter(filter);		
		
	}
	
	@Test
	public void testGetPublicationByFilterNewspaper() throws Exception{

		PublicationFilter filter = new PublicationFilter();
		String[] type = new String[1];
		type[0] = new String("Newspaper");
		filter.setType(type);
		
		List<Newspaper> newspaperList = new ArrayList<>();
		newspaperList.add(new Newspaper());
		newspaperList.add(new Newspaper());
		newspaperList.add(new Newspaper());
		
		assertFalse(Arrays.asList(filter.getType()).contains(new String("Book")));
		assertFalse(Arrays.asList(filter.getType()).contains(new String("Magazine")));
		assertTrue(Arrays.asList(filter.getType()).contains(new String("Newspaper")));
		when(newspaperBean.getByFilter(filter)).thenReturn(newspaperList);
		
		List<Publication> pubs = publicationServiceBean.getPublicationByFilter(filter);
		assertEquals(3, pubs.size());
		
		verify(newspaperBean, atLeastOnce()).getByFilter(filter);		
		
	}
	
	@Test(expected=ServiceException.class)
	public void testGetPublicationByFilterThrowException() throws ServiceException {
		
		PublicationFilter filter = new PublicationFilter();
		String[] type = new String[1];
		type[0] = new String("Trap");
		filter.setType(type);
		
		doThrow(new RepositoryException("publication.read"));
	}
}
