package solomonj.msg.userapp.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article extends BaseEntity implements Serializable {
	
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