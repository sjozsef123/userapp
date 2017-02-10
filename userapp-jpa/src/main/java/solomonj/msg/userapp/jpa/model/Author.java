package solomonj.msg.userapp.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the authors database table.
 * 
 */
@Entity
@Table(name="authors")
public class Author extends BaseEntity {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Column(name="name")
	private String name;

	public Author() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


}