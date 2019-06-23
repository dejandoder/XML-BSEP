package xml_bsep.agent_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.agent_app.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query("select message from Message message where (message.fromUser.id=:user1 and message.toUser.id=:user2) or (message.fromUser.id=:user2 and message.toUser.id=:user1)")
	public List<Message> getMessagesBetweenUsers(@Param("user1") long user1,@Param("user2") long user2);
}
