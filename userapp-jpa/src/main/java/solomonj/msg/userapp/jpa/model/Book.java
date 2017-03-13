package solomonj.msg.userapp.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="book")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("book")
public class Book extends Publication {

	@Transient
	private static final long serialVersionUID = 6162720738158711916L;

	@XmlElementWrapper(name="authors")
	@XmlElement(name="author")
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name = "publications_authors", joinColumns = @JoinColumn(name = "publication_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> bAuthors;

	public Book() {

		this.bAuthors = new ArrayList<>();
	}

	public List<Author> getbAuthors() {
		return this.bAuthors;
	}

	public void setbAuthors(final List<Author> bAuthors) {
		this.bAuthors = bAuthors;
	}

	@Override
	public String toString() {
		return "Book [bAuthors=" + bAuthors + ", id=" + id + ", getCopiesLeft()=" + getCopiesLeft()
				+ ", getNrOfCopies()=" + getNrOfCopies() + ", getPublisher()=" + getPublisher() + ", getReleaseDate()="
				+ getReleaseDate() + ", getTitle()=" + getTitle() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bAuthors == null) ? 0 : bAuthors.hashCode());
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
		Book other = (Book) obj;
		if (bAuthors == null) {
			if (other.bAuthors != null)
				return false;
		} else if (!bAuthors.equals(other.bAuthors))
			return false;
		return true;
	}
	
	

}
