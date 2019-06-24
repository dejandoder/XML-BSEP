package xml_bsep.acc_service.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.common.model.AccomodationUnit;

import xml_bsep.acc_service.dto.AccomodationUnitDTO;
import xml_bsep.acc_service.dto.SearchDTO;
import xml_bsep.acc_service.service.AccomodationUnitService;

@RestController
@RequestMapping("/")
@Validated
public class AccomodationUnitController {

	@Autowired
	private AccomodationUnitService accService;
	

	@PostMapping(value = "/all/search")
	public ResponseEntity<List<AccomodationUnitDTO>> search(@Valid @RequestBody SearchDTO searchDTO){
		List<AccomodationUnitDTO> retVal = accService.search(searchDTO);
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	

	@PostMapping(value = "/user/getAccUnit")
	public ResponseEntity<AccomodationUnit> getAccUnit(@RequestBody @Min(1) long accId){
		AccomodationUnit accUnit = accService.findOne(accId);
		return new ResponseEntity<>(accUnit, HttpStatus.OK);
	}
}
