package xml_bsep.messages_service.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "RecensionStatus")
@XmlEnum
public enum RecensionStatus {
	PENDING, APPROVED, DECLINED
}
