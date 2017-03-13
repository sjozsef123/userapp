package solomonj.msg.userapp.jpa.model;

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
public abstract class Publication extends BaseEntity {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + copiesLeft;
		result = prime * result + nrOfCopies;
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Publication other = (Publication) obj;
		if (copiesLeft != other.copiesLeft)
			return false;
		if (nrOfCopies != other.nrOfCopies)
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
}