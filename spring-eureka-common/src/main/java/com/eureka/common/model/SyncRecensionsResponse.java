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
})
@XmlRootElement(name = "sync_recensions_responset")
public class SyncRecensionsResponse {

	@XmlElement(required = true)
	private List<Recension> recensions;
	
	public SyncRecensionsResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<Recension> getRecensions() {
		return recensions;
	}

	public void setRecensions(List<Recension> recensions) {
		this.recensions = recensions;
	}

}
