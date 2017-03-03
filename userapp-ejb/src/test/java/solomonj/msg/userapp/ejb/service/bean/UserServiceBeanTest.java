package solomonj.msg.userapp.ejb.service.bean;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceBeanTest {

	/**
	 *** My first unit test
	 * 
	 * @author Solomon Jozsef
	 */

	@Mock
	IUserRepository userRepositoryBean;
	
	@InjectMocks
	UserServiceBean userServiceBean;

	@Test(expected = ServiceException.class)
	public void searchUser() throws Exception {

		User user = new User();
		user.setUsername("Robi");
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userRepositoryBean.searchUserByName("Robi")).thenReturn(users);
		assertEquals("Robi", userServiceBean.searchUserByName("Robi").get(0).getUsername());
		doThrow(new RepositoryException("user.read")).when(userRepositoryBean).searchUserByName(any());
		userServiceBean.searchUserByName("Robi");
		verify(userRepositoryBean, atLeastOnce()).searchUserByName(any());
		verify(userRepositoryBean, times(5)).searchUserByName(any());

	}
	
	@Test
	public void getAllUsers() throws Exception {
		User user = new User();
		user.setUsername("Robi");
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userRepositoryBean.getlAll()).thenReturn(users);
		assertEquals("Robi", userServiceBean.getAllUsers().get(0).getUsername());
	}
	
	
}
