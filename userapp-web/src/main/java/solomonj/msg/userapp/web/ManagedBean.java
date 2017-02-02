package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.IRole;
import solomonj.msg.appuser.common.IUser;
import solomonj.msg.userapp.jpa.model.Role;
import solomonj.msg.userapp.jpa.model.User;


@Named("userr")
@ApplicationScoped
public class ManagedBean implements Serializable, IUser, IRole {
	private Logger oLogger = Logger.getLogger(ManagedBean.class);
	private static final long serialVersionUID = -16296420798818231L;
	private IUser userBean = null;
	//private IRole roleBean = null;
	List<User> users = null;
		
	@Override
	public List<User> getAllUsers() {
		      oLogger.info((getUserBean().getAllUsers().get(0).getRoles()) + "---------");
		return getUserBean().getAllUsers();
		
	}

	@Override
	public void insertUser(String username) {
		
		getUserBean().insertUser(username);
		
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
	
//	private IRole getRoleBean() {
//		if (roleBean == null) {
//			try {
//				InitialContext jndi = new InitialContext();
//				roleBean = (IRole) jndi.lookup(IRole.jndiNAME);
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//		}
//		return roleBean;
//	}

	@Override
	public void deleteUserById(int id) {
		getUserBean().deleteUserById(id);
		
	}

	@Override
	public List<User> searchUserByName(String name) {
		users = getUserBean().searchUserByName(name);
		return null;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	@Override
	public User searchUserById(int id) {
		
		return getUserBean().searchUserById(id);
	}

	@Override
	public void updateUser(int id, String username) {
		getUserBean().updateUser(id, username);
		
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Role> getRoles() {
//		return getRoleBean().getRoles();
//	}
	

}
