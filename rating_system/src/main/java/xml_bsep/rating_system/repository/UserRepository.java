package xml_bsep.rating_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eureka.common.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findOneByUsername(String username);
	
}
