package xml_bsep.acc_service.model;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ReservationStatus")
@XmlEnum
public enum ReservationStatus {
	PENDING, APPROVED, CONFIRMED;
}
