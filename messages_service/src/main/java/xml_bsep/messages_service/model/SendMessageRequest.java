package xml_bsep.messages_service.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "message"
})
@XmlRootElement(name = "send_message_request")
public class SendMessageRequest {

	@XmlElement(required = true)
	private Message message;
	
	public SendMessageRequest() {
		// TODO Auto-generated constructor stub
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
