package solomonj.msg.userapp.jpa.model;

public class PublicationStat {
	private String subjectName;
	private Integer marks;

	
	public PublicationStat(String subjectName, Integer marks) {
		super();
		this.subjectName = subjectName;
		this.marks = marks;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getMarks() {
		return marks;
	}

	public void setMarks(Integer marks) {
		this.marks = marks;
	}

}
