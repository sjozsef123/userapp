package solomonj.msg.userapp.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role extends BaseEntity {
	
	@Transient
	private static final long serialVersionUID = 231135871492528636L;

	@Column(name="rolename")
	private String rolename;

	public Role() {
	}
	
	public Role(int id) {
		
		this.id = id;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public String toString() {
		return "Role [rolename=" + rolename + "]";
	}

}