package xml_bsep.messages_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.messages_service.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query("select mes from Message mes where (mes.fromUser.username = :username1 and mes.toUser.username = :username2) or (mes.fromUser.username = :username2 and mes.toUser.username = :username1)")
	public List<Message> getMessages(@Param("username1") String username1, @Param("username2") String username2);
	
}
