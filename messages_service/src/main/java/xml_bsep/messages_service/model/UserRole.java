package xml_bsep.messages_service.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "UserRole")
@XmlEnum
public enum UserRole {
	USER, ADMIN, AGENT;
}
