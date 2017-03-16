package solomonj.msg.userapp.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends BaseEntity {
	
	@Transient
	private static final long serialVersionUID = -3160124704546098071L;

	@Column(name = "loyalty_index")
	private int loyaltyIndex = 10;

	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private List<PublicationBorrowing> borrowings;

	@XmlElementWrapper(name="roles")
	@XmlElement(name="role")
	@ManyToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	public User() {
		roles = new ArrayList<>();
		borrowings = new ArrayList<>();
	}

	public User(String id, String userName) {
		this();
		this.id = id;
		this.username = userName;		
}
	
	public int getLoyaltyIndex() {
		return this.loyaltyIndex;
	}

	public void setLoyaltyIndex(int loyaltyIndex) {
		this.loyaltyIndex = loyaltyIndex;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PublicationBorrowing> getBorrowing() {
		return this.borrowings;
	}

	public void setBorrowing(List<PublicationBorrowing> publicationBorrowings) {
		this.borrowings = publicationBorrowings;
	}

	public PublicationBorrowing addPublicationsBorrowing(PublicationBorrowing publicationBorrowing) {
		getBorrowing().add(publicationBorrowing);
		publicationBorrowing.setUser(this);

		return publicationBorrowing;
	}

	public PublicationBorrowing removePublicationsBorrowing(PublicationBorrowing publicationBorrowing) {
		getBorrowing().remove(publicationBorrowing);
		publicationBorrowing.setUser(null);

		return publicationBorrowing;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((borrowings == null) ? 0 : borrowings.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + loyaltyIndex;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (borrowings == null) {
			if (other.borrowings != null)
				return false;
		} else if (!borrowings.equals(other.borrowings))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (loyaltyIndex != other.loyaltyIndex)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}