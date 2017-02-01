package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.IUser;
import solomonj.msg.userapp.jpa.model.User;


@Named("userr")
@ApplicationScoped
public class ManagedBean implements Serializable, IUser {

	private static final long serialVersionUID = -16296420798818231L;
	private IUser userBean = null;
	List<User> users = null;
		
	@Override
	public List<User> getAllUsers() {
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

}
