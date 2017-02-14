package solomonj.msg.userapp.ejb.repository.bean;

import javax.ejb.Stateless;

import solomonj.msg.userapp.ejb.repository.IRoleRepository;
import solomonj.msg.userapp.jpa.model.Role;

/**
 * This session bean manages the roles.
 * 
 * @author Solomon Jozsef
 *
 */
@Stateless
public class RoleRepositoryBean extends BasicRepositoryBean<Role> implements IRoleRepository {

	public RoleRepositoryBean() {
		super(Role.class);
	}

}
