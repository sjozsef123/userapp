package solomonj.msg.userapp.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the roles database table.
 * 
 */
@XmlRootElement(name="role")
@XmlAccessorType(XmlAccessType.FIELD)
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
	
	public Role(String id) {
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}
}