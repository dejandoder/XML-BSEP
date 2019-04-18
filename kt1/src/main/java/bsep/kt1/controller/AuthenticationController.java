package bsep.kt1.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsep.kt1.model.User;
import bsep.kt1.security.CustomUserDetailsService;
import bsep.kt1.security.TokenHelper;
//import bsep.kt1.service.UserService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	//protected final Logger logger = LogManager.getLogger(DokumentiApplication.class);

	@Autowired
	TokenHelper tokenHelper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	/*@Autowired
	private UserService userService;*/

	@PostMapping(value = "/login", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> createAuthenticationToken(@RequestParam("email") String email, @RequestParam("password") String password) {
		// Izvrsavanje security dela
		
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			//logger.info("User " + email + " successfully logged in");
		} catch (AuthenticationException e) {
			//logger.info("Someone tried to log in with wrong credentials");
			return new ResponseEntity<String>("Wrong email/password.", HttpStatus.FORBIDDEN);
		}

		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		String jws = tokenHelper.generateToken(user.getEmail());

		// Vrati token kao odgovor na uspesno autentifikaciju
		return new ResponseEntity<String>(jws, HttpStatus.OK);
	}

	/*@PreAuthorize("hasRole('REGULAR_USER')")
	@GetMapping(value = "/current-user")
	public ResponseEntity<User> getCurrentUser(Principal principal) {
		if (principal == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		User user = userService.findOne(principal.getName());
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/

	@PostMapping(value = "/change-password")
	@PreAuthorize("hasRole('REGULAR_USER')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
}