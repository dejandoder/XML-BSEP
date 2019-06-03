package bsep.kt1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bsep.kt1.model.User;


public interface UserRepository extends JpaRepository<User, String> {
	
	public User save(User user);
	
	@Query("select user from User user where user.email = :email")
	public User getUserByEmail(@Param("email") String email);

}
