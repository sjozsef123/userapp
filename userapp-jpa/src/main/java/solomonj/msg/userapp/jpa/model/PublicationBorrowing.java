package solomonj.msg.userapp.jpa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="publications_borrowings")
public class PublicationBorrowing implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PublicationBorrowingPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="borrowing_date")
	private Date borrowingDate;

	@Temporal(TemporalType.DATE)
	private Date deadline;

	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("publicationId")
	@JoinColumn(name = "publication_id")
	private Publication publication;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;

	public PublicationBorrowing() {
	}

	public PublicationBorrowingPK getId() {
		return this.id;
	}

	public void setId(PublicationBorrowingPK id) {
		this.id = id;
	}

	public Date getBorrowingDate() {
		return this.borrowingDate;
	}

	public void setBorrowingDate(Date borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Publication getPublication() {
		return this.publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}