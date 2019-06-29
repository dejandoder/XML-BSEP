package com.eureka.zuul;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@SpringBootApplication
@EnableEurekaClient 	// It acts as a eureka client
@EnableZuulProxy		// Enable Zuul

public class SpringZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringZuulApplication.class, args);
	}
	
	/*@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
	    DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
	    System.setProperty("javax.net.ssl.keyStore", "src/main/resources/zuul.jks");
	    System.setProperty("javax.net.ssl.keyStorePassword", "password");
	    System.setProperty("javax.net.ssl.trustStore", "src/main/resources/zuul.jks");
	    System.setProperty("javax.net.ssl.trustStorePassword", "password");
	    EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
	    builder.withClientName("zuul-server");
	    builder.withSystemSSLConfiguration();
	    builder.withMaxTotalConnections(10);
	    builder.withMaxConnectionsPerHost(10);
	    args.setEurekaJerseyClient(builder.build());
	    return args;
	}*/
}
