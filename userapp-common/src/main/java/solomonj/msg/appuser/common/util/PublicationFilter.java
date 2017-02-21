package solomonj.msg.appuser.common.util;

import java.sql.Date;
import java.util.List;

public class PublicationFilter {

	private String title;
	private List<String> type; 
	private int minStock;
	private int maxStock;
	private String publisher;
	private Date releasedAfter;
	private Date releasedBefore;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		System.out.println("title set to " + title);
		this.title = title;
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
	public Date getReleasedAfter() {
		return releasedAfter;
	}
	public void setReleasedAfter(Date releasedFrom) {
		this.releasedAfter = releasedFrom;
	}
	public Date getReleasedBefore() {
		return releasedBefore;
	}
	public void setReleasedBefore(Date releasedBefore) {
		this.releasedBefore = releasedBefore;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PublicationFilter [title=" + title + ", type=" + type + ", minStock=" + minStock + ", maxStock="
				+ maxStock + ", publisher=" + publisher + ", releasedAfter=" + releasedAfter + ", releasedBefore="
				+ releasedBefore + "]";
	}
	
	
}
