package xml_bsep.agent_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reservationId"
})
@XmlRootElement(name = "confirm_reservation_request")
public class ConfirmReservationRequest {

	@XmlElement(required = true)
	private long reservationId;
	
	public ConfirmReservationRequest() {
		// TODO Auto-generated constructor stub
	}

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	
}
