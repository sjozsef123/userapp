package solomonj.msg.appuser.common.util;

public class PublicationFilter {

	private String title;
	private String[] type;
	private Integer minStock;
	private Integer maxStock;
	private String publisher;
	private Integer releasedAfter;
	private Integer releasedBefore;
	
	public PublicationFilter() {

		title = null;
		type = new String[]{"Book", "Magazine", "Newspaper"};
		minStock = null;
		maxStock = null;
		publisher = null;
		releasedAfter = null;
		releasedBefore = null;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMinStock() {
		return minStock;
	}
	public void setMinStock(Integer minStock) {
		this.minStock = minStock;
	}
	public Integer getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(Integer maxStock) {
		this.maxStock = maxStock;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Integer getReleasedAfter() {
		return releasedAfter;
	}
	public void setReleasedAfter(Integer releasedFrom) {
		this.releasedAfter = releasedFrom;
	}
	public Integer getReleasedBefore() {
		return releasedBefore;
	}
	public void setReleasedBefore(Integer releasedBefore) {
		this.releasedBefore = releasedBefore;
	}
	public String[] getType() {
		return type;
	}
	public void setType(String[] type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PublicationFilter [title=" + title + ", type=" + type + ", minStock=" + minStock + ", maxStock="
				+ maxStock + ", publisher=" + publisher + ", releasedAfter=" + releasedAfter + ", releasedBefore="
				+ releasedBefore + "]";
	}
	
	
}