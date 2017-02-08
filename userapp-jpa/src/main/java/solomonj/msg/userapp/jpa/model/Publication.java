package solomonj.msg.userapp.jpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the publications database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name="publications")
public class Publication extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -8330975882778732846L;

	@Column(name="copies_left")
	private int copiesLeft;

	@Column(name="nr_of_copies")
	private int nrOfCopies;

	@Column(name="publisher")
	private String publisher;

	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	private Date releaseDate;

	@Column(name="title")
	private String title;


	public Publication() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCopiesLeft() {
		return this.copiesLeft;
	}

	public void setCopiesLeft(int copiesLeft) {
		this.copiesLeft = copiesLeft;
	}

	public int getNrOfCopies() {
		return this.nrOfCopies;
	}

	public void setNrOfCopies(int nrOfCopies) {
		this.nrOfCopies = nrOfCopies;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}