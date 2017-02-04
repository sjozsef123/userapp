package solomonj.msg.userapp.jpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name = "roles")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role extends BaseEntity {

	private static final long serialVersionUID = -2662876033544729199L;

	private String rolename;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
	private List<User> users;

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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id+ ", users=" + users + "]";
	}



}