package xml_bsep.admin_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eureka.common.model.User;
import com.eureka.common.security.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {

	@SuppressWarnings("unchecked")
	public User save(User user);
	
	public List<User> findAll();
	
	@Query("update User user set user.status = com.eureka.common.model.UserStatus.ACTIVATED where user.id = :id")
	public void activateUser(@Param("id") long id);
	
	@Query("update User user set user.status = com.eureka.common.model.UserStatus.BLOCKED where user.id = :id")
	public void blockUser(@Param("id") long id);
	
	public User findUserById(long id);
	
	public List<User> findUserByRole(UserRole role);
	
	@Query("select user from User user where user.role = com.eureka.common.security.UserRole.AGENT and ( user.username = :username or user.pib = :pib )")
	public List<User> checkAgentsByPibAndName(@Param("username") String username, @Param("pib") String pib);
	
}
