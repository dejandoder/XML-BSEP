package xml_bsep.reservation_service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",propOrder = {
	    "resId"
	})
@XmlRootElement(name = "decline_reservation_request")
public class DeclineReservationRequest {

	@XmlElement(required = true)
	private long resId;
	
	public DeclineReservationRequest() {
		// TODO Auto-generated constructor stub
	}

	public long getResId() {
		return resId;
	}

	public void setResId(long resId) {
		this.resId = resId;
	}
	
}
