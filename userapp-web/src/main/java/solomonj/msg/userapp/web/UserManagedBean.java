package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.event.RowEditEvent;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IUserService;
import solomonj.msg.userapp.ejb.service.util.SendEmail;
import solomonj.msg.userapp.jpa.model.Role;
import solomonj.msg.userapp.jpa.model.User;

/**
 * Managed bean for users.
 *
 * @author Solomon Jozsef.
 *
 */
@Named("usermanagedbean")
@SessionScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = -16296420798818231L;
	private IUserService userBean = null;
	private User user = new User();
	private List<String> selectedRoles = new ArrayList<>();
	private List<User> allUsers = null;
	private List<User> allBadUsers = null;
	private String searchName = "";

	private IUserService getUserBean() {
		if (this.userBean == null) {
			try {
				final InitialContext jndi = new InitialContext();
				this.userBean = (IUserService) jndi.lookup(IUserService.jndiNAME);
			} catch (final NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("user.naming"), null));
			}
		}
		return this.userBean;
	}

	public boolean selectedRoles(final User u) {
		this.selectedRoles.clear();
		final List<Role> roles = u.getRoles();
		for (final Role r : roles) {
			this.selectedRoles.add(new Integer(r.getId()).toString());
		}
		return true;
	}

	public List<String> getSelectedRoles() {
		return this.selectedRoles;
	}

	public void setSelectedRoles(final List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public void resetAdd() {
		this.user = new User();
		this.selectedRoles = new ArrayList<>();
	}

	public void dummy() {

	}

	@PostConstruct
	public void init() {
		try {

			this.allUsers = getUserBean().searchUserByName(this.searchName);

		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
		if (this.allUsers == null) {
			this.allUsers = new ArrayList<>();
		}
	}

	public List<User> getAllUsers() {
		return this.allUsers;
	}

	public List<User> getAllBadUsers() {
		try {

			this.allBadUsers = getUserBean().getAllBadUsers();
			return this.allBadUsers;
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
		if (this.allBadUsers == null) {
			return new ArrayList<>();
		}
		return this.allBadUsers;
	}

	public void insert() {
		try {
			this.user.setRoles(rolesToInt());
			getUserBean().insertUser(this.user);
			this.allUsers.add(this.user);
			this.user = new User();
			this.selectedRoles = new ArrayList<>();

		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void delete(final User user) {

		try {
			getUserBean().deleteUserById(user);
			this.allUsers.remove(user);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}

	}

	public void updateUser(final User user) {

		try {

			user.setRoles(rolesToInt());

			this.selectedRoles.clear();

			getUserBean().updateUser(user);
			this.allUsers = getUserBean().searchUserByName(this.searchName);

		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}

	}

	public User getUser() {
		return this.user;
	}

	private List<Role> rolesToInt() {
		final List<Role> roles = new ArrayList<>();
		for (final String i : this.selectedRoles) {
			roles.add(new Role(Integer.parseInt(i)));
		}
		return roles;
	}

	public User login(final String n, final String p) {
		try {
			return getUserBean().login(n, p);
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
			return new User();
		}
	}

	public void sendEmail(final User u) {
		try {
			SendEmail.sendEmail(u.getEmail(), "szocscsillamaria@gmail.com");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					LoginManagedBean.getResourceBundleString("web.user.emailsent"), null));
		} catch (final ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}
	}

	public void onEdit(final RowEditEvent event) {

		final User updateUser = (User) event.getObject();

		updateUser(updateUser);

		final FacesMessage msg = new FacesMessage("User Edited", ((User) event.getObject()).getUsername());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCancel(final RowEditEvent event) {

		final FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getUsername());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getSearchName() {
		return this.searchName;
	}

	public void setSearchName(final String searchName) {
		this.searchName = searchName;
	}

}
