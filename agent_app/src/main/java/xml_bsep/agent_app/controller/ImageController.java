package xml_bsep.agent_app.controller;



import java.io.IOException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import xml_bsep.agent_app.dto.AccomodationUnitDTO;
import xml_bsep.agent_app.service.ImageService;
import xml_bsep.agent_app.service.UserService;

@RestController
@RequestMapping("/")
public class ImageController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ImageService service;
	
	@Autowired
	UserService userService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/uploadImages", produces =  MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity uploadImages(@RequestParam("file") MultipartFile[] images, @RequestParam("accId") long accId){
		try {
			service.uploadImages(images, accId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("NP_EVENT DS {} {}", userService.getCurrentUserName(), accId);
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		logger.info("NP_EVENT DS {} {}", userService.getCurrentUserName(), accId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping(value = "/getImages", produces = MediaType.IMAGE_JPEG_VALUE)
	public List<byte[]> getImges(@RequestBody AccomodationUnitDTO accUnitDTO) {
		List<byte[]> binaryImage = service.getImagesByAccomodationUnit(accUnitDTO.getId());
		logger.info("NP_EVENT PSS {} {}", userService.getCurrentUserName(), accUnitDTO.getId());
		return binaryImage;
	}
	
	@ResponseBody
	@PostMapping(value = "/getImageIds")
	public List<Long> getImage(@RequestBody AccomodationUnitDTO accUnit) {
		logger.info("NP_EVENT PSS {} {}", userService.getCurrentUserName(), accUnit.getId());
		return service.getImagesIdByAccomodationUnit(accUnit.getId());
	}
	
	@ResponseBody
	@PostMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@RequestBody long id) {
		byte[] binaryImage = service.getImageById(id);
		logger.info("NP_EVENT PSS {} {}", userService.getCurrentUserName(), id);////////////
		return binaryImage;
	}
	
}


