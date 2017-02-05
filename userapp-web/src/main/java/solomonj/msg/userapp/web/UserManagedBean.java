package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import solomonj.msg.appuser.common.IUser;
import solomonj.msg.appuser.common.ServiceException;
import solomonj.msg.userapp.jpa.model.Role;
import solomonj.msg.userapp.jpa.model.User;

@Named("userr")
@ApplicationScoped
public class UserManagedBean implements Serializable {
	private Logger oLogger = Logger.getLogger(UserManagedBean.class);
	private static final long serialVersionUID = -16296420798818231L;
	private IUser userBean = null;
	private User user = new User();
	private User beforeEditItem = null;
	private boolean edit;
	private List<String> selectedRoles = new ArrayList<>();

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	private List<User> allUsers = null;

	public void editt(User user) {
		beforeEditItem = user.clone();
		List<Role> roles = user.getRoles();
		for (Role r : roles) {
			selectedRoles.add(new Integer(r.getId()).toString());
		}
		oLogger.info(selectedRoles);

		this.user = user;
		edit = true;
	}

	public void cancelEdit() {
		this.user.restore(beforeEditItem);
		this.user = new User();
		selectedRoles = new ArrayList<>();
		edit = false;
	}

	public void add() {
		oLogger.info(selectedRoles);
		/**********************/
		List<Role> roles = new ArrayList<>();
		for (String i : selectedRoles) {
			roles.add(new Role(Integer.parseInt(i)));
		}
		user.setRoles(roles);

		/**********************/
		insertUser(user);
		user = new User();
		selectedRoles = new ArrayList<>();
	}

	public void resetAdd() {
		user = new User();
		selectedRoles = new ArrayList<>();
	}

	public void saveEdit() {
		/**********************/
		List<Role> roles = new ArrayList<>();
		for (String i : selectedRoles) {
			roles.add(new Role(Integer.parseInt(i)));
		}
		oLogger.info(selectedRoles);
		user.setRoles(roles);
		/**********************/
		updateUser(user);
		this.user = new User();
		selectedRoles = new ArrayList<>();
		edit = false;
	}

	public List<User> getAllUsers() {
		allUsers = getUserBean().getAllUsers();
		if (allUsers == null) {
			return new ArrayList<>();
		}
		return allUsers;

	}

	public void insertUser(User user) {
		try {
			getUserBean().insertUser(user);
		} catch (ServiceException e) {
			oLogger.error(e.getMessage());
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}
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

	public void deleteUserById(int id) {
		try {
			getUserBean().deleteUserById(id);
		} catch (ServiceException e) {
			oLogger.error(e.getMessage());
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}
		

	}

	public List<User> searchUserByName(String name) {

		return null;
	}

	public User searchUserById(int id) {

		return getUserBean().searchUserById(id);
	}

	public void updateUser(User user) {
		try {
			getUserBean().updateUser(user);
		} catch (Exception e) {
			oLogger.error(e.getMessage());
		    FacesContext.getCurrentInstance().addMessage(null, 
		        new FacesMessage(FacesMessage.SEVERITY_INFO, "User's name already exists", null));
		}


	}

	public User getUser() {
		return user;
	}

	public boolean isEdit() {
		return edit;
	}

	public void delete(User user) {
		deleteUserById(user.getId());
	}

}
