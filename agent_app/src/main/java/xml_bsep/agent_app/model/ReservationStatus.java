package xml_bsep.agent_app.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ReservationStatus")
@XmlEnum
public enum ReservationStatus {
	PENDING, APPROVED, CONFIRMED;
}
