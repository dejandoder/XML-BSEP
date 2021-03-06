package xml_bsep.agent_app.security;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.Permission;
import xml_bsep.agent_app.model.UserRole;
import xml_bsep.agent_app.repository.UserRepository;

@Service   // It has to be annotated with @Service.
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if(!Pattern.matches("[a-zA-Z0-9.,?]*", username)) {
			throw new UsernameNotFoundException("Username: " + username + " not found");
		}
		
		xml_bsep.agent_app.model.User user = repository.findByUsername(username);
		
		if(user == null) throw new UsernameNotFoundException("Username: " + username + " not found");
		
		if(user.getRole() != UserRole.AGENT) 
			throw new UsernameNotFoundException("Username: " + username + " not found");
		
		
				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
		                	.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole());
				for(Permission permission : user.getPermissions()) {
					grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
				}
				
				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		
	}
}