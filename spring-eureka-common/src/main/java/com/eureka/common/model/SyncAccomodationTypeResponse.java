package com.eureka.common.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
		   "types"
})
@XmlRootElement(name = "sync_acc_types_response")
public class SyncAccomodationTypeResponse {

	@XmlElement(required = true)
	ArrayList<AccomodationType> types;
	
	public SyncAccomodationTypeResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<AccomodationType> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<AccomodationType> types) {
		this.types = types;
	}
	
	
}
