package solomonj.msg.userapp.ejb.service.bean;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import solomonj.msg.userapp.ejb.repository.IUserRepository;
import solomonj.msg.userapp.ejb.repository.exception.RepositoryException;
import solomonj.msg.userapp.jpa.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceBeanTest {

	/**
	 *** My first simple mock test
	 * @author Solomon Jozsef
	 */
	
	@InjectMocks
	IUserRepository userRepository = mock(IUserRepository.class);
	
	@Test
	public void searchUser() {
		
		User user = new User();
		user.setUsername("Robi");
		List<User> users = new ArrayList<>();
		users.add(user);
		
		try {
			when(userRepository.searchUserByName("Robi")).thenReturn(users);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		try {
			assertEquals("Robi",userRepository.searchUserByName("Robi").get(0).getUsername());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
	}
}
