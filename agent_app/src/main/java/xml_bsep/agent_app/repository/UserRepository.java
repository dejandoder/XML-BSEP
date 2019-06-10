package xml_bsep.agent_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xml_bsep.agent_app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	
}
