package xml_bsep.rating_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.rating_system.model.AccomodationUnit;
import xml_bsep.rating_system.model.Recension;
import xml_bsep.rating_system.model.RecensionStatus;
import xml_bsep.rating_system.model.User;

import xml_bsep.rating_system.dto.CheckReviewDTO;
import xml_bsep.rating_system.dto.RecensionDTO;
import xml_bsep.rating_system.repository.AccomodationUnitRepository;
import xml_bsep.rating_system.repository.RecensionRespository;
import xml_bsep.rating_system.repository.UserRepository;

@Service
public class RecesnsionService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecensionRespository recensionRespository;
	
	@Autowired
	AccomodationUnitRepository accUnitRepository;
	
	@Autowired
	UserService userService;
	
	public void addAcomodationUnit(AccomodationUnit accUnit) {
		accUnitRepository.save(accUnit);
	}
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public List<RecensionDTO> getRecensionsForApproval(){
		List<Recension> recensions = recensionRespository.getRecensionsForApproval();
		List<RecensionDTO> recensionsDTO = new ArrayList<>();
		for (Recension recension : recensions) {
			recensionsDTO.add(new RecensionDTO(recension));
		}
		return recensionsDTO;
	}
	
	public List<RecensionDTO> getRecensionsByAccUnit(long id){
		List<Recension> recensions = recensionRespository.getRecensionsByAccUnit(id);
		List<RecensionDTO> recensionsDTO = new ArrayList<>();
		for (Recension recension : recensions) {
			recensionsDTO.add(new RecensionDTO(recension));
		}
		return recensionsDTO;
	}
	
	public Recension save(Recension recensions) {
		recensions.setUser(userService.getCurrentUser());
		accUnitRepository.save(recensions.getAccomodationUnit());
		return recensionRespository.save(recensions);
	}
	
	public void approveRecension(long recId) {
		Recension rec = recensionRespository.findOneById(recId);
		rec.setStatus(RecensionStatus.APPROVED);
		recensionRespository.save(rec);
	}
	
	public void  declineRecension(long recId) {
		Recension rec = recensionRespository.findOneById(recId);
		rec.setStatus(RecensionStatus.DECLINED);
		recensionRespository.save(rec);
	}
	
	public boolean checkIfRecnsionExsists(CheckReviewDTO crDTO) {
		Recension rec = recensionRespository.checkIfRecensionExsists(crDTO.getUserId(), crDTO.getAccUintId());
		if(rec == null) return false;
		return true;
	}
	
	public List<Recension> findAll(){
		return recensionRespository.findAll();
	}
}
