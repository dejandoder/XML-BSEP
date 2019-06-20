package xml_bsep.messages_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.common.model.Message;

import xml_bsep.messages_service.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	MessageRepository repository;

	public List<Message> findAll(){
		return repository.findAll();
	}
	
}
