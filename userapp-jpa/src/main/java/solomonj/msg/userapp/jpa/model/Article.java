package solomonj.msg.userapp.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "articles")
//@NamedQuery(name = "Article.freeArticles", query = "SELECT a FROM Article a WHERE a.publicationId is null")
public class Article extends BaseEntity {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Column(name="title")
	private String title;

	public Article() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}