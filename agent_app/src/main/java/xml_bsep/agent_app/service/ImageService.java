package xml_bsep.agent_app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.Image;
import xml_bsep.agent_app.repository.AccomodationUnitRepository;
import xml_bsep.agent_app.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository repository;
	
	@Autowired
	AccomodationUnitRepository accomodationUnitRepository;
	
	public Image save(Image image) {
		return repository.save(image);
	}
	
	public void uploadImages(MultipartFile[] images, long accId) throws IOException {
	
		AccomodationUnit accUnit = accomodationUnitRepository.getOne(accId);
		
		ArrayList<Image> imagesModel = new ArrayList<>();
		for (MultipartFile image  : images) {
			Image imageModel = new Image();
			imageModel.setPic(image.getBytes());
			imageModel.setAccomodationUnit(accUnit);
			imagesModel.add(imageModel);
		}
		repository.saveAll(imagesModel);	
	}

	public ArrayList<byte[]> getImagesByAccomodationUnit(long accId){
		List<Image> images = repository.getImagesByAccomodationUnit(accId);
		
		if(images == null) return null;
		
		ArrayList<byte[]> binaryImages = new ArrayList<>();
		for (Image image : images) {
			binaryImages.add(image.getPic());
		}
		
		return binaryImages;
	}
	
	public byte[] getImageById(long id){
		Image image = repository.getOne(id);
		return image.getPic();
	}
	
	public List<Long> getImagesIdByAccomodationUnit(long accId){
		return repository.getImagesId(accId);
	}
	
}
