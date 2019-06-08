package xml_bsep.agent_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "agentId",
    "reservation"
})
@XmlRootElement(name = "agent_reservation_request")
public class AgentReservationRequest {

	@XmlElement(required = true)
	private long agentId;
	@XmlElement(required = true)
	private Reservation reservation;
	
	public AgentReservationRequest() {
		// TODO Auto-generated constructor stub
	}

	public long getAgentId() {
		return agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	
}
