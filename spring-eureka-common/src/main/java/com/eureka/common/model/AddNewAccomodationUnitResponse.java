package com.eureka.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "accUnitId"
})
@XmlRootElement(name = "add_new_accomodation_unit_response")
public class AddNewAccomodationUnitResponse {

	@XmlElement(required = true)
	private SOAPResponseStatus status;
	@XmlElement(required = true)
	private long accUnitId;
	
	public AddNewAccomodationUnitResponse() {
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStatus(SOAPResponseStatus status) {
		this.status = status;
	}

	public long getAccUnitId() {
		return accUnitId;
	}

	public void setAccUnitId(long accUnitId) {
		this.accUnitId = accUnitId;
	}
}
