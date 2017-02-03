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
	private List<Role> roles;

	@Override
	public List<Role> getRoles() {
		if(roles == null) {
			roles = getRoleBean().getRoles();
		}
		return roles;
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

}
