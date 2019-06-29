package xml_bsep.acc_service.model;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
 "service"
})
@XmlRootElement(name = "sync_acc_services_response")
public class SyncAccServicesResponse {

	@XmlElement(required = true)
	ArrayList<AccomodationService> service;
	
	public SyncAccServicesResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<AccomodationService> getService() {
		return service;
	}

	public void setService(ArrayList<AccomodationService> service) {
		this.service = service;
	}

	
}

