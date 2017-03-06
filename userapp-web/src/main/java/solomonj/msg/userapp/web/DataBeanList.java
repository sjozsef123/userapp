package solomonj.msg.userapp.web;

import java.util.ArrayList;
import java.util.List;

import solomonj.msg.userapp.jpa.model.User;

public class DataBeanList {
	
	public static List<User> getDataBeanList() {
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setUsername("Robi");
		user1.setEmail("Robi@yahoo.com");
		User user2 = new User();
		user2.setUsername("Barni");
		user2.setEmail("Barni@yahoo.com");
		users.add(user1);
		users.add(user2);
		return users;
	}
}
