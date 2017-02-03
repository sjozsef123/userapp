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

import solomonj.msg.userapp.jpa.model.User;

@Named("userr")
@ApplicationScoped
public class UserManagedBean implements Serializable, IUser {
	private Logger oLogger = Logger.getLogger(UserManagedBean.class);
	private static final long serialVersionUID = -16296420798818231L;
	private IUser userBean = null;
	private User user;
	private User beforeEditItem = null;
	private boolean edit;

	private List<User> allUsers = null;

	public void edit(User item) {
		beforeEditItem = item.clone();
		this.user = item;
		edit = true;
	}

	public void cancelEdit() {
		this.user.restore(beforeEditItem);
		this.user = new User();
		edit = false;
	}
	

	 public void add() {
	    
	        insertUser(user);
	        user = new User();
	    }

	    public void resetAdd() {
	    	user = new User();
	    }

	@Override
	public List<User> getAllUsers() {
		allUsers = getUserBean().getAllUsers();

		return allUsers;

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

	public User getItem() {
		return user;
	}

	public void setItem(User item) {
		this.user = item;
	}

	public boolean isEdit() {
		return edit;
	}

}
