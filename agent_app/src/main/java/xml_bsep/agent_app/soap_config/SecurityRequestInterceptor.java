package xml_bsep.agent_app.soap_config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.WebServiceIOException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.transport.TransportOutputStream;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpUrlConnection;

import xml_bsep.agent_app.repository.UserRepository;
import xml_bsep.agent_app.service.UserService;

public class SecurityRequestInterceptor implements ClientInterceptor {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository repo;
	
	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {

		 	TransportContext context = TransportContextHolder.getTransportContext();
	        HttpUrlConnection connection = (HttpUrlConnection) context.getConnection();

	        try {
	        	userService.getCurrentUser();
	        	System.out.println(userService.getCurrentUser());
	        	System.out.println(userService.getJwtToken());
	            connection.addRequestHeader("Authorization","Bearer " + userService.getJwtToken());
	        } catch (IOException e) {
	            //log.error(e.getMessage());
	        }
	        return true;
	    }

	@Override
	public void afterCompletion(MessageContext arg0, Exception arg1) throws WebServiceClientException {
		// TODO Auto-generated method stub
		 logMessageContext(arg0);
	}

	@Override
	public boolean handleFault(MessageContext arg0) throws WebServiceClientException {
		// TODO Auto-generated method stub
		 logMessageContext(arg0);
		return false;
	}

	@Override
	public boolean handleResponse(MessageContext arg0) throws WebServiceClientException {
		// TODO Auto-generated method stub
		 logMessageContext(arg0);
		return false;
	}
	

		

		private void logMessageContext(MessageContext messageContext) {
		    ByteArrayOutputStream os = new ByteArrayOutputStream();
		    try {
		        messageContext.getResponse().writeTo(os);
		    } catch (IOException e) {
		        throw new WebServiceIOException(e.getMessage(), e);
		    }
		    String response = new String(os.toByteArray());
		    System.out.println("Soap response\n----------------------------\n" + response + "\n----------------------------\n");
		}


		class ByteArrayTransportOutputStream extends TransportOutputStream {

		    private ByteArrayOutputStream outputStream;

		    @Override
		    public void addHeader(String name, String value) throws IOException {
		        createOutputStream();
		        String header = name + ": " + value + "\n";
		        outputStream.write(header.getBytes());
		    }

		    public byte[] toByteArray() {
		        return outputStream.toByteArray();
		    }

		    @Override
		    protected OutputStream createOutputStream() throws IOException {
		        if (outputStream == null) {
		            outputStream = new ByteArrayOutputStream();
		        }
		        return outputStream;
		    }
		}
		

}
