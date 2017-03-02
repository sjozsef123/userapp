package solomonj.msg.userapp.ejb.service.bean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.bean.BorrowingRepositoryBean;
import solomonj.msg.userapp.ejb.repository.bean.UserRepositoryBean;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceBeanTest {

	/**
	 *** My first simple mock test
	 * 
	 * @author Solomon Jozsef
	 */

	@InjectMocks
	UserServiceBean userServiceBean;

	@Mock
	UserRepositoryBean userRepositoryBean;

	@Test
	public void searchUser() throws Exception {

		User user = new User();
		user.setUsername("Robi");
		List<User> users = new ArrayList<>();
		users.add(user);

		when(userRepositoryBean.searchUserByName("Robi")).thenReturn(users);

		assertEquals("Robi", userServiceBean.searchUserByName("Robi").get(0).getUsername());

	}
}
