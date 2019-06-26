package xml_bsep.agent_app.soap_config;

import java.io.File;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;


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
	public AccomodationServiceSoapClient accomodationServiceSoapClient(Jaxb2Marshaller marshaller) throws Exception {
		AccomodationServiceSoapClient client = new AccomodationServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
	    client.setInterceptors(interceptors);
		client.setDefaultUri("https://localhost:8762/acc/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.setMessageSender(httpComponentsMessageSender());
		client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean
	public ReservationServiceSoapClient reservationServiceSoapClient(Jaxb2Marshaller marshaller) throws Exception {
		ReservationServiceSoapClient client = new ReservationServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
		client.setInterceptors(interceptors);
		client.setDefaultUri("https://localhost:8762/res/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.setMessageSender(httpComponentsMessageSender());
		client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean
	public MessagesServiceSoapClient messagesServiceSoapClient(Jaxb2Marshaller marshaller) throws Exception {
		MessagesServiceSoapClient client = new MessagesServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
		client.setInterceptors(interceptors);
		client.setDefaultUri("https://localhost:8762/mess/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.setMessageSender(httpComponentsMessageSender());
		client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean
	public AuthServiceSoapClient authServiceSoapClient(Jaxb2Marshaller marshaller) throws Exception {
		AuthServiceSoapClient client = new AuthServiceSoapClient();
		ClientInterceptor[] interceptors = new ClientInterceptor[]{securityRequestInterceptor()};
		client.setInterceptors(interceptors);
		client.setDefaultUri("https://localhost:8762/auth/soap");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		client.setMessageSender(httpComponentsMessageSender());
		//client.getWebServiceTemplate().setCheckConnectionForFault(true);
		return client;
	}
	
	@Bean 
	public SecurityRequestInterceptor securityRequestInterceptor() {
		return new SecurityRequestInterceptor();
	}
	
	@Bean
	  public HttpComponentsMessageSender httpComponentsMessageSender() throws Exception {
	    HttpComponentsMessageSender httpComponentsMessageSender = new HttpComponentsMessageSender();
	    httpComponentsMessageSender.setHttpClient(httpClient());

	    return httpComponentsMessageSender;
	  }

	  public HttpClient httpClient() throws Exception {
	    return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory())
	        .addInterceptorFirst(new RemoveSoapHeadersInterceptor()).build();
	  }

	  public SSLConnectionSocketFactory sslConnectionSocketFactory() throws Exception {
	    // NoopHostnameVerifier essentially turns hostname verification off as otherwise following error
	    // is thrown: java.security.cert.CertificateException: No name matching localhost found
	    return new SSLConnectionSocketFactory(sslContext(), NoopHostnameVerifier.INSTANCE);
	  }

	  public SSLContext sslContext() throws Exception {
	    return SSLContextBuilder.create()
	        .loadTrustMaterial( new ClassPathResource("agent.jks").getFile(), "password".toCharArray()).build();
	  }
}
