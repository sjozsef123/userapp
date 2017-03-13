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
	private String publicationId;

	@Column(name="user_id", insertable=false, updatable=false)
	private String userId;

	public PublicationBorrowingPK() {
	}
	public String getPublicationId() {
		return this.publicationId;
	}
	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((publicationId == null) ? 0 : publicationId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicationBorrowingPK other = (PublicationBorrowingPK) obj;
		if (publicationId == null) {
			if (other.publicationId != null)
				return false;
		} else if (!publicationId.equals(other.publicationId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}