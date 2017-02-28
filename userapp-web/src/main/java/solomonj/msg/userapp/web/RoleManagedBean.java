package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.exception.ServiceException;
import solomonj.msg.appuser.common.service.IRoleService;
import solomonj.msg.userapp.jpa.model.Role;

/**
 * Managed bean for roles.
 * 
 * @author Solomon Jozsef
 *
 */
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
			return null;
		}

	}

	private IRoleService getRoleBean() {
		if (roleBean == null) {
			try {
				InitialContext jndi = new InitialContext();
				roleBean = (IRoleService) jndi.lookup(IRoleService.jndiNAME);
			} catch (NamingException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						LoginManagedBean.getResourceBundleString("role.naming"), null));
			}
		}
		return roleBean;
	}


	public void deleteRole(Role role) {
		try {
			getRoleBean().deleteRole(role);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}

	}

	public void add() {

		insertRole(role);
		role = new Role();

	}

	public void resetAdd() {
		role = new Role();
	}

	public void insertRole(Role role) {
		try {
			getRoleBean().insertRole(role);
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					LoginManagedBean.getResourceBundleString(e.getMessage()), null));
		}

	}

}
