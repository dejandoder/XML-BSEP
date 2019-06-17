package xml_bsep.agent_app.soap_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import xml_bsep.agent_app.soap_clients.AccomodationServiceSoapClient;
import xml_bsep.agent_app.soap_clients.AuthServiceSoapClient;
import xml_bsep.agent_app.soap_clients.MessagesServiceSoapClient;
import xml_bsep.agent_app.soap_clients.ReservationServiceSoapClient;

@Configuration
public class SoapClientsConfig {
	

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("xml_bsep.agent_app.model");
		return marshaller;
	}

	@Bean
	public AccomodationServiceSoapClient accomodationServiceSoapClient(Jaxb2Marshaller marshaller) {
		AccomodationServiceSoapClient client = new AccomodationServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
	    client.setInterceptors(interceptors);
		client.setDefaultUri("http://localhost:8762/acc/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean
	public ReservationServiceSoapClient reservationServiceSoapClient(Jaxb2Marshaller marshaller) {
		ReservationServiceSoapClient client = new ReservationServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
		client.setInterceptors(interceptors);
		client.setDefaultUri("http://localhost:8762/res/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean
	public MessagesServiceSoapClient messagesServiceSoapClient(Jaxb2Marshaller marshaller) {
		MessagesServiceSoapClient client = new MessagesServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
		client.setInterceptors(interceptors);
		client.setDefaultUri("http://localhost:8762/mess/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean
	public AuthServiceSoapClient authServiceSoapClient(Jaxb2Marshaller marshaller) {
		AuthServiceSoapClient client = new AuthServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
		client.setInterceptors(interceptors);
		client.setDefaultUri("http://localhost:8762/auth/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		//client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean 
	public SecurityRequestInterceptor securityRequestInterceptor() {
		return new SecurityRequestInterceptor();
	}
}
