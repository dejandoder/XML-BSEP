package xml_bsep.agent_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "reservationId"
})
@XmlRootElement(name = "agent_reservation_response")
public class AgentReservationResponse {

	@XmlElement(required = true)
	private SOAPResponseStatus status;
	@XmlElement(required = true)
	private long reservationId;
	
	public AgentReservationResponse() {
		// TODO Auto-generated constructor stub
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStatus(SOAPResponseStatus status) {
		this.status = status;
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	
}
