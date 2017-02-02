package solomonj.msg.appuser.common;

import java.util.List;

import solomonj.msg.userapp.jpa.model.Role;

public interface IRole {
	public static final String jndiNAME="java:global/userapp-ear-0.0.1-SNAPSHOT/userapp-ejb-0.0.1-SNAPSHOT/RoleBean";
	
	public List<Role> getRoles();
}
