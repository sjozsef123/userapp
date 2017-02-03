package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	private User item = new User();
	private User beforeEditItem = null;
	private boolean edit;

	private List<User> allUsers = null;

	public void editt(User item) {
		beforeEditItem = item.clone();
		this.item = item;
		edit = true;
	}

	public void cancelEdit() {
		this.item.restore(beforeEditItem);
		this.item = new User();
		edit = false;
	}

	public void add() {

		insertUser(item);
		item = new User();
	}

	public void resetAdd() {
		item = new User();
	}

	public void saveEdit() {
		updateUser(item);
		this.item = new User();
		edit = false;
	}

	@Override
	public List<User> getAllUsers() {
		allUsers = getUserBean().getAllUsers();
		if (allUsers == null) {
			return new ArrayList<>();
		}
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
	public void updateUser(User user) {
		getUserBean().updateUser(user);

	}

	public User getItem() {
		return item;
	}

	public boolean isEdit() {
		return edit;
	}

	public void delete(User item) {
		deleteUserById(item.getId());
	}

}
