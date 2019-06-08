package xml_bsep.messages_service.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.eureka.common.model.SendMessageRequest;
import com.eureka.common.model.SendMessageResponse;
import com.eureka.common.model.SyncMessagesRequest;
import com.eureka.common.model.SyncMessagesResponse;



@Endpoint
public class MessagesEndpoint {

	private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/hotel-team1";
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sync_messages_request")
	public SyncMessagesResponse syncMessages(@RequestPayload SyncMessagesRequest request) {
		SyncMessagesResponse response = new SyncMessagesResponse();
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "send_message_request")
	public SendMessageResponse sendMessage(@RequestPayload SendMessageRequest request) {
		SendMessageResponse response = new SendMessageResponse();
		return response;
	}
	
}
