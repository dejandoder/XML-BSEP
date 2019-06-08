package com.eureka.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "agentId",
    "accUnit"
})
@XmlRootElement(name = "add_new_accomodation_unit_request")
public class AddNewAccomodationUnitRequest {

	@XmlElement(required = true)
	private long agentId;
	@XmlElement(required = true)
	private AccomodationUnit accUnit;
	
	public AddNewAccomodationUnitRequest() {
		
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public AccomodationUnit getAccUnit() {
		return accUnit;
	}

	public void setAccUnit(AccomodationUnit accUnit) {
		this.accUnit = accUnit;
	}
	
	
}
