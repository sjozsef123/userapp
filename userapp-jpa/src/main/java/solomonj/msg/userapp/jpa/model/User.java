package solomonj.msg.userapp.jpa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

@Entity
@Table(name="users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends BaseEntity implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -3160124704546098071L;

	@Column(name = "loyalty_index")
	private int loyaltyIndex = 10;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private List<PublicationBorrowing> borrowings;

	@ManyToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	public User() {
		roles = new ArrayList<>();
		borrowings = new ArrayList<>();
	}

	public User(int id, String userName) {
		this();
		setId(id);
		this.username = userName;		
}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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
	

}