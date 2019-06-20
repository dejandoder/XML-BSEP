package xml_bsep.messages_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eureka.common.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
