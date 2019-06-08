package com.eureka.common.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "messageId",
   "status"
})
@XmlRootElement(name = "send_message_response")
public class SendMessageResponse {

	@XmlElement(required = true)
	private long messageId;
	@XmlElement(required = true)
	private SOAPResponseStatus status;
	
	public SendMessageResponse() {
		// TODO Auto-generated constructor stub
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public SOAPResponseStatus getStatus() {
		return status;
	}

	public void setStatus(SOAPResponseStatus status) {
		this.status = status;
	}
	
}
