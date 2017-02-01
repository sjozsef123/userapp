package solomonj.msg.appuser.common;

import java.util.List;

import solomonj.msg.userapp.jpa.model.User;

public interface IUser {
	public static final String jndiNAME="java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/UserBean";
	public List<User> getAllUsers();

	public void insertUser(String username);

}
