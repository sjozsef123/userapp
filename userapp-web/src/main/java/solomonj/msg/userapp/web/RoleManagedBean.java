package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IRoleService;
import solomonj.msg.userapp.jpa.model.Role;

@Named("rolemanagedbean")
@SessionScoped
public class RoleManagedBean implements Serializable {

	private static final long serialVersionUID = -6796469792037802850L;
	private IRoleService roleBean = null;
	private Role role = new Role();

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
			try {
				return getRoleBean().getRoles();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

	}

	private IRoleService getRoleBean() {
		if (roleBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				roleBean = (IRoleService) jndi.lookup(IRoleService.jndiNAME);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return roleBean;
	}

	public void delete(Role role) {
		deleteRole(role);
	}

	public void deleteRole(Role role) {
		try {
			getRoleBean().deleteRole(role);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}

	}

	public void add() {
		if (role.getRolename().length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Min. 3 character", null));
		} else {
			insertRole(role);
			role = new Role();
		}

	}

	public void resetAdd() {
		role = new Role();
	}

	public void insertRole(Role role) {

		try {
			getRoleBean().insertRole(role);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		}

	}

}
