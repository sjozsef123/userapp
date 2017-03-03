package solomonj.msg.userapp.jpa.model;

import java.util.ArrayList;
import java.util.List;

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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Publication {

	@Transient
	private static final long serialVersionUID = -5115846190988191659L;

	@XmlElementWrapper(name="authors")
	@XmlElement(name="author")
	@ManyToMany
	@JoinTable(name = "publications_authors", joinColumns = @JoinColumn(name = "publication_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> mAuthors;

	public Magazine() {

		this.mAuthors = new ArrayList<>();
	}

	public List<Author> getmAuthors() {
		return this.mAuthors;
	}

	public void setmAuthors(final List<Author> mAuthors) {
		this.mAuthors = mAuthors;
	}

}
