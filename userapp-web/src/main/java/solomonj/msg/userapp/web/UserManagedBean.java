package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.IUser;
import solomonj.msg.userapp.jpa.model.Role;
import solomonj.msg.userapp.jpa.model.User;


@Named("userr")
@ApplicationScoped
public class UserManagedBean implements Serializable, IUser {
	private Logger oLogger = Logger.getLogger(UserManagedBean.class);
	private static final long serialVersionUID = -16296420798818231L;
	private IUser userBean = null;
	private User user;
	private int roleid;
	

	private List<User> allUsers = null;

		
	@Override
	public List<User> getAllUsers() {
		     allUsers = getUserBean().getAllUsers();
		     
		return allUsers;
		
	}
	
	public void insertUser() {
		Role role = new Role();
		role.setId(roleid);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		insertUser(this.user);
		user = null;
	}


	@Override
	public void insertUser(User user) {
		
		getUserBean().insertUser(user);
		
	}
	
	private IUser getUserBean() {
		if (userBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				userBean = (IUser) jndi.lookup(IUser.jndiNAME);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return userBean;
	}
	

	@Override
	public void deleteUserById(int id) {
		getUserBean().deleteUserById(id);
		
	}

	@Override
	public List<User> searchUserByName(String name) {
		
		return null;
	}



	@Override
	public User searchUserById(int id) {
		
		return getUserBean().searchUserById(id);
	}

	@Override
	public void updateUser(int id, String username) {
		getUserBean().updateUser(id, username);
		
	}


	public User getUser() {
		if(user == null) {
			user = new User();
		}
		return user;
	}


	public void setUser(User user) {
		
		this.user = user;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	

}
