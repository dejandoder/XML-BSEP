package xml_bsep.rating_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xml_bsep.rating_system.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findOneByUsername(String username);
	
}
