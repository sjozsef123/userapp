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

import solomonj.msg.appuser.common.IUserService;
import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.userapp.jpa.model.Role;
import solomonj.msg.userapp.jpa.model.User;

@Named("usermanagedbean")
@ApplicationScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = -16296420798818231L;
	private IUserService userBean = null;
	private User user = new User();
	private boolean edit;
	private List<String> selectedRoles = new ArrayList<>();
	private List<User> allUsers = null;
	private String searchName = "";

	private IUserService getUserBean() {
		if (userBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				userBean = (IUserService) jndi.lookup(IUserService.jndiNAME);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return userBean;
	}

	public List<String> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public void editUser(User user) {
		selectedRoles.clear();
		List<Role> roles = user.getRoles();
		for (Role r : roles) {
			selectedRoles.add(new Integer(r.getId()).toString());
		}

		this.user = user;
		edit = true;
	}

	public void cancelEdit() {
		this.user = new User();
		selectedRoles = new ArrayList<>();
		edit = false;
	}

	public void add() {

		if (checkUserName()) {
			user.setRoles(rolesToInt());
			insertUser(user);
			user = new User();
			selectedRoles = new ArrayList<>();
		}
	}

	public void resetAdd() {
		user = new User();
		selectedRoles = new ArrayList<>();
	}

	public void saveEdit() {
		if (checkUserName()) {			
			user.setRoles(rolesToInt());
			updateUser(user);
			this.user = new User();
			selectedRoles = new ArrayList<>();
			edit = false;

		}
	}

	public List<User> getAllUsers() {
		try {
			allUsers = getUserBean().searchUserByName(searchName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		if (allUsers == null) {
			return new ArrayList<>();
		}
		return allUsers;

	}

	public void insertUser(User user) {
		try {
			getUserBean().insertUser(user);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}
	}

	public void deleteUserById(int id) {
		try {
			getUserBean().deleteUserById(id);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}

	}

	public void updateUser(User user) {
		try {
			getUserBean().updateUser(user);
		} catch (ServiceException e) {

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

		if (edit) {
			cancelEdit();
		}
		deleteUserById(user.getId());

	}

	private List<Role> rolesToInt() {
		List<Role> roles = new ArrayList<>();
		for (String i : selectedRoles) {
			roles.add(new Role(Integer.parseInt(i)));
		}
		return roles;
	}

	private boolean checkUserName() {
		if (user.getUsername().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min 3 character", null));
			return false;
		} else {
			return true;
		}
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	

}
