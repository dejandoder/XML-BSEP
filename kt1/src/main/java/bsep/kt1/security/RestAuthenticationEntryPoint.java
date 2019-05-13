package bsep.kt1.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import bsep.kt1.utils.LoggingUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());;

	//Metoda koja se izvrsava ukoliko neautorizovani korisnik pokusa da pristupi zasticenom REST servisu
	//Metoda vraca 401 Unauthorized response, ukoliko postoji Login Page u aplikaciji, pozeljno je da se korisnik redirektuje na tu stranicu
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        logger.warn(LoggingUtils.getSeMarker(),"SE_EVENT Unauthorised access attempt to {} from ip adress{}",request.getRequestURL(),request.getRemoteAddr());
    	response.sendRedirect("/html/login.html");
    }
}