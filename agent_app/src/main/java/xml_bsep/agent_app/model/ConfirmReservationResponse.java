package xml_bsep.agent_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "status"
})
@XmlRootElement(name = "confirm_reservation_response")
public class ConfirmReservationResponse {

	@XmlElement(required = true)
	private SOAPResponseStatus status;
	
	public ConfirmReservationResponse() {
		// TODO Auto-generated constructor stub
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStatus(SOAPResponseStatus stauts) {
		this.status = stauts;
	}
	
}
