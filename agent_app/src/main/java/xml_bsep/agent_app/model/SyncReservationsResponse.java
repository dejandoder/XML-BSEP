package xml_bsep.agent_app.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "reservations",
   "status"
})
@XmlRootElement(name = "sync_reservations_response")
public class SyncReservationsResponse {

	@XmlElement(required = true)
	List<Reservation> reservations;
	@XmlElement(required = true)
	private SOAPResponseStatus status;
	
	public SyncReservationsResponse() {
		
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStatus(SOAPResponseStatus status) {
		this.status = status;
	}
	
}
