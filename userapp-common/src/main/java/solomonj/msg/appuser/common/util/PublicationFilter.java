package solomonj.msg.appuser.common.util;

import java.sql.Date;

public class PublicationFilter {

	private String title;
	private String type;
	private int minStock;
	private int maxStock;
	private String publisher;
	private Date releasedFrom;
	private Date releasedBefore;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getMinStock() {
		return minStock;
	}
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	public int getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getReleasedFrom() {
		return releasedFrom;
	}
	public void setReleasedFrom(Date releasedFrom) {
		this.releasedFrom = releasedFrom;
	}
	public Date getReleasedBefore() {
		return releasedBefore;
	}
	public void setReleasedBefore(Date releasedBefore) {
		this.releasedBefore = releasedBefore;
	}
	
	
}
