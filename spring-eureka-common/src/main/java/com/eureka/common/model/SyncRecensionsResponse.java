package com.eureka.common.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "recensions",
   "status"
})
@XmlRootElement(name = "sync_recensions_request")
public class SyncRecensionsResponse {

	@XmlElement(required = true)
	private List<Recension> recensions;
	@XmlElement(required = true)
	private SOAPResponseStatus status;
	
	public SyncRecensionsResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<Recension> getRecensions() {
		return recensions;
	}

	public void setRecensions(List<Recension> recensions) {
		this.recensions = recensions;
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStatus(SOAPResponseStatus status) {
		this.status = status;
	}
	
}
