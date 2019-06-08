package com.eureka.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status"
})
@XmlRootElement(name = "approve_reservation_response")
public class ApproveReservationResponse {

	@XmlElement(required = true)
	private SOAPResponseStatus status;
	
	public ApproveReservationResponse() {
		// TODO Auto-generated constructor stub
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStauts(SOAPResponseStatus status) {
		this.status = status;
	}

}

