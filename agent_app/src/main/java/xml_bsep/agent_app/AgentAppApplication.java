package xml_bsep.agent_app;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import xml_bsep.agent_app.service.UserService;



@SpringBootApplication
public class AgentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentAppApplication.class, args);
	}

}
@Configuration
class RestTemplateConfig {
	
	@Autowired
	UserService userService;
	
	// Create a bean for restTemplate to call services
	@Bean		// Load balance between service instances running at different ports.
	public RestTemplate restTemplate() {
	    RestTemplate restTemplate = new RestTemplate();

	    KeyStore keyStore;
		HttpComponentsClientHttpRequestFactory requestFactory = null;
		
		try {
			keyStore = KeyStore.getInstance("jks");
			ClassPathResource classPathResource = new ClassPathResource("agent.jks");
			InputStream inputStream = classPathResource.getInputStream();
			keyStore.load(inputStream, "password".toCharArray());

			SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder()
					.loadTrustMaterial(null, new TrustSelfSignedStrategy())
					.loadKeyMaterial(keyStore, "password".toCharArray()).build(),
					NoopHostnameVerifier.INSTANCE);

			HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
					.setMaxConnTotal(Integer.valueOf(5))
					.setMaxConnPerRoute(Integer.valueOf(5))
					.build();

			requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			requestFactory.setReadTimeout(Integer.valueOf(10000));
			requestFactory.setConnectTimeout(Integer.valueOf(10000));
			
			restTemplate.setRequestFactory(requestFactory);
		} catch (Exception exception) {
			System.out.println("Exception Occured while creating restTemplate "+exception);
			exception.printStackTrace();
		}
		 restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor(){
		        @Override
		        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		            request.getHeaders().set("Authorization", "Bearer " + userService.getJwtToken());//Set the header for each request
		            return execution.execute(request, body);
		        }
		    });
	    return restTemplate;
	}
}