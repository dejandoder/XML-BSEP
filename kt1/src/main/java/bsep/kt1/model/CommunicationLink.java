package bsep.kt1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CommunicationLink {

	@Id
	@GeneratedValue
	private long id;
	
	private Certificate memberOne;
	
	private Certificate memberTwo;
	
	public CommunicationLink() {
		// TODO Auto-generated constructor stub
	}

	public CommunicationLink(Certificate memberOne, Certificate memberTwo) {
		super();
		this.memberOne = memberOne;
		this.memberTwo = memberTwo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Certificate getMemberOne() {
		return memberOne;
	}

	public void setMemberOne(Certificate memberOne) {
		this.memberOne = memberOne;
	}

	public Certificate getMemberTwo() {
		return memberTwo;
	}

	public void setMemberTwo(Certificate memberTwo) {
		this.memberTwo = memberTwo;
	}
	
}
