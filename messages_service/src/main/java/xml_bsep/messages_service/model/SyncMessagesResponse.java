package xml_bsep.messages_service.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "messages",
   "status"
})
@XmlRootElement(name = "sync_messages_response")
public class SyncMessagesResponse {

	@XmlElement(required = true)
	private List<Message> messages;
	@XmlElement(required = true)
	private SOAPResponseStatus status;
	
	public SyncMessagesResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStatus(SOAPResponseStatus status) {
		this.status = status;
	}
	
}
