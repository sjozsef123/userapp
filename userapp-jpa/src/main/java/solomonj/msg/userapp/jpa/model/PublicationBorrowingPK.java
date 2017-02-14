package solomonj.msg.userapp.jpa.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the publications_borrowings database table.
 * 
 */
@Embeddable
public class PublicationBorrowingPK implements Serializable {

	@Transient
	private static final long serialVersionUID = -7733366869965132043L;

	@Column(name="publication_id", insertable=false, updatable=false)
	private int publicationId;

	@Column(name="user_id", insertable=false, updatable=false)
	private int userId;

	public PublicationBorrowingPK() {
	}
	public int getPublicationId() {
		return this.publicationId;
	}
	public void setPublicationId(int publicationId) {
		this.publicationId = publicationId;
	}
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PublicationBorrowingPK)) {
			return false;
		}
		PublicationBorrowingPK castOther = (PublicationBorrowingPK)other;
		return 
			(this.publicationId == castOther.publicationId)
			&& (this.userId == castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.publicationId;
		hash = hash * prime + this.userId;
		
		return hash;
	}
}