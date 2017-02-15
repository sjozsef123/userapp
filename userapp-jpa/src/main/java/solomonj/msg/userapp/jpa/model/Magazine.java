package solomonj.msg.userapp.jpa.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Publication {
	
	@Transient
	private static final long serialVersionUID = -5115846190988191659L;

	@ManyToMany
	@JoinTable(name = "publications_authors", joinColumns = @JoinColumn(name = "publication_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> mAuthors;

	public Magazine() {
		
		mAuthors = new ArrayList<>();
	}

	public List<Author> getmAuthors() {
		return mAuthors;
	}

	public void setmAuthors(List<Author> mAuthors) {
		this.mAuthors = mAuthors;
	}
	
	public int getReleaseYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getReleaseDate());
		return calendar.get(Calendar.YEAR);
	}
	
	public int getReleaseMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getReleaseDate());
		return calendar.get(Calendar.MONTH);
	}
}
