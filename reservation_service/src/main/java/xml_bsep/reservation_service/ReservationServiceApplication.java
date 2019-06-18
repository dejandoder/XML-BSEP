package xml_bsep.reservation_service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import xml_bsep.reservation_service.service.UserService;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan("xml_bsep.reservation_service")
@EntityScan(value = "com.eureka.common.model")
public class ReservationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationServiceApplication.class, args);
	}

}


@Configuration
class RestTemplateConfig {
	
	@Autowired
	UserService userService;
	
	// Create a bean for restTemplate to call services
	@Bean
	@LoadBalanced		// Load balance between service instances running at different ports.
	public RestTemplate restTemplate() {
	    RestTemplate restTemplate = new RestTemplate();
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

