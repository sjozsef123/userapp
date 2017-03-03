package solomonj.msg.userapp.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import solomonj.msg.appuser.common.service.IPublicationService;
import solomonj.msg.appuser.common.util.PublicationFilter;
import solomonj.msg.userapp.jpa.model.Book;
import solomonj.msg.userapp.jpa.model.Magazine;
import solomonj.msg.userapp.jpa.model.Newspaper;
import solomonj.msg.userapp.jpa.model.Publication;

@RunWith(MockitoJUnitRunner.class)
public class PublicationManagedBeanTest {

//	@Rule
//	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	public IPublicationService publicationService;
	
	@InjectMocks
	public PublicationManagedBean publicationManagedBean;
	
	@Test
	public void testNewPubs() {

		publicationManagedBean.newPubs();
		
		assertFalse(publicationManagedBean.getBook() == null);
		assertFalse(publicationManagedBean.getMagazine() == null);
		assertFalse(publicationManagedBean.getNewspaper() == null);
	
	}
	
	@Test
	public void testOnLoad() {

		publicationManagedBean.onLoad();
		
		assertFalse(publicationManagedBean.getBook() == null);
		assertFalse(publicationManagedBean.getMagazine() == null);
		assertFalse(publicationManagedBean.getNewspaper() == null);
	
	}
	
	@Test
	public void testInit() throws Exception{
		
//		List<Publication> publications = new ArrayList<>();
//		publications.add(new Book());
//		publications.add(new Magazine());
//		publications.add(new Newspaper());
//		
//		PublicationFilter filter = new PublicationFilter();
//		String[] type = new String[3];
//		type[0] = new String("Book");
//		type[1] = new String("Magazine");
//		type[2] = new String("Newspaper");
//		filter.setType(type);
		
//		when(publicationBean.getPublicationByFilter(filter)).thenReturn(publications);
//		
//		
//		publicationManagedBean.init();
//		
//		
//		assertFalse(publicationManagedBean.getPublicationList() == null);
		
	}
}
