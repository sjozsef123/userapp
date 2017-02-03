package solomonj.msg.userapp.jpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends BaseEntity {

	private static final long serialVersionUID = -1149625015538767160L;

	private String userName;

	// bi-directional many-to-many association to Role
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> roles;

	public User() {
	}
	
	public User(int id, String userName) {
		setId(id);
		this.userName = userName;
	}


	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [username=" + userName + ", roles=" + roles + "]";
	}
	
	@Override
	public User clone() {
		return new User(getId(), this.userName);
	}
	
	public void restore(User user) {
		 setId(user.getId());
		this.userName = user.getUserName();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}