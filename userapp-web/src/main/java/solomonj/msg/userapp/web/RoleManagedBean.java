package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.IRole;
import solomonj.msg.appuser.common.ServiceException;
import solomonj.msg.userapp.jpa.model.Role;

@Named("rolemanagedbean")
@ApplicationScoped
public class RoleManagedBean implements Serializable {

	private static final long serialVersionUID = -6796469792037802850L;
	private IRole roleBean = null;
	private Role role = new Role();

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {

		return getRoleBean().getRoles();

	}

	private IRole getRoleBean() {
		if (roleBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				roleBean = (IRole) jndi.lookup(IRole.jndiNAME);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return roleBean;
	}

	public void delete(Role role) {
		deleteRole(role.getId());
	}

	public void deleteRole(int id) {
		try {
			getRoleBean().deleteRole(id);
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
