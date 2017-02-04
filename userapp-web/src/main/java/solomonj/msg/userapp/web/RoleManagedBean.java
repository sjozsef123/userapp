package solomonj.msg.userapp.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import solomonj.msg.appuser.common.IRole;
import solomonj.msg.userapp.jpa.model.Role;

@Named("roler")
@ApplicationScoped
public class RoleManagedBean implements Serializable, IRole {

	private static final long serialVersionUID = -6796469792037802850L;
	private IRole roleBean = null;
	private Role role = new Role();

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	@Override
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

	@Override
	public void deleteRole(int id) {
		getRoleBean().deleteRole(id);

	}

	public void add() {
		insertRole(role);
		role = new Role();
	}

	public void resetAdd() {
		role = new Role();
	}

	@Override
	public void insertRole(Role role) {
		getRoleBean().insertRole(role);

	}

}
