package xml_bsep.agent_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.Image;
import xml_bsep.agent_app.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository repository;
	
	public Image save(Image image) {
		return repository.save(image);
	}
}
