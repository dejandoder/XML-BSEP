package xml_bsep.messages_service.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml_bsep.messages_service.service.MessageService;

import com.eureka.common.model.SendMessageRequest;
import com.eureka.common.model.SendMessageResponse;
import com.eureka.common.model.SyncMessagesRequest;
import com.eureka.common.model.SyncMessagesResponse;



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
		SendMessageResponse response = new SendMessageResponse();
		return response;
	}
	
}
