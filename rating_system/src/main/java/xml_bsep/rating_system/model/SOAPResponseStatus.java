package xml_bsep.rating_system.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "SOAPResponseStatus")
@XmlEnum
public enum SOAPResponseStatus {
	SUCCESS, ERROR;
}
