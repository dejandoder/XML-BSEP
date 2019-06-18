package xml_bsep.acc_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.common.model.Image;

import xml_bsep.acc_service.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository repository;
	
	public void saveAll(List<Image> images) {
		repository.saveAll(images);
	}
	
}
