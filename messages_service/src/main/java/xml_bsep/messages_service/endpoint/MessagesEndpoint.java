package xml_bsep.messages_service.endpoint;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml_bsep.messages_service.service.MessageService;

import xml_bsep.messages_service.model.Message;
import xml_bsep.messages_service.model.SendMessageRequest;
import xml_bsep.messages_service.model.SendMessageResponse;
import xml_bsep.messages_service.model.SyncMessagesRequest;
import xml_bsep.messages_service.model.SyncMessagesResponse;



@Endpoint
public class MessagesEndpoint {

	private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/hotel-team1";
	
	@Autowired
	MessageService service;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sync_messages_request")
	public SyncMessagesResponse syncMessages(@RequestPayload SyncMessagesRequest request) {
		SyncMessagesResponse response = new SyncMessagesResponse();
		response.setMessages(service.findAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "send_message_request")
	public SendMessageResponse sendMessage(@RequestPayload SendMessageRequest request) {
		Message message = service.save(request.getMessage());
		message.setDate(new Date());
		SendMessageResponse response = new SendMessageResponse();
		response.setMessageId(message.getId());
		return response;
	}
	
}
