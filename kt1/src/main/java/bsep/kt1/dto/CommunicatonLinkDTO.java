package bsep.kt1.dto;

import bsep.kt1.model.CommunicationLink;

public class CommunicatonLinkDTO {

	private CertificateDTO memberOne;
	private CertificateDTO memberTwo;
	
	public CommunicatonLinkDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CommunicatonLinkDTO(CommunicationLink cl) {
		// TODO Auto-generated constructor stub
		this.memberOne = new CertificateDTO(cl.getMemberOne());
		this.memberTwo = new CertificateDTO(cl.getMemberTwo());
	}

	public CertificateDTO getMemberOne() {
		return memberOne;
	}

	public void setMemberOne(CertificateDTO memberOne) {
		this.memberOne = memberOne;
	}

	public CertificateDTO getMemberTwo() {
		return memberTwo;
	}

	public void setMemberTwo(CertificateDTO memberTwo) {
		this.memberTwo = memberTwo;
	}
	
}
