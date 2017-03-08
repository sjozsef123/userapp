package solomonj.msg.userapp.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="newspaper")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("newspaper")
public class Newspaper extends Publication {

	@Transient
	private static final long serialVersionUID = 1L;

	@XmlElementWrapper(name="articles")
	@XmlElement(name="article")
	@OneToMany(cascade=CascadeType.MERGE)
	@JoinColumn(name = "publication_id")
	List<Article> articles;

	public Newspaper() {
		this.articles = new ArrayList<>();
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(final List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Newspaper [articles=" + articles + ", id=" + id + ", getCopiesLeft()=" + getCopiesLeft()
				+ ", getNrOfCopies()=" + getNrOfCopies() + ", getPublisher()=" + getPublisher() + ", getReleaseDate()="
				+ getReleaseDate() + ", getTitle()=" + getTitle() + "]";
	}

	
	
}
