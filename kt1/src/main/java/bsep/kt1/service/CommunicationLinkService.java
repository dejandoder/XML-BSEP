package bsep.kt1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsep.kt1.dto.CertificateDTO;
import bsep.kt1.dto.CommunicatonLinkDTO;
import bsep.kt1.model.Certificate;
import bsep.kt1.model.CommunicationLink;
import bsep.kt1.repository.CommunicationLinkRepository;

@Service
public class CommunicationLinkService {

	@Autowired
	CommunicationLinkRepository clRepository;
	
	public void save(CommunicationLink cl) {
		clRepository.save(cl);
	}
	
	public List<CommunicatonLinkDTO> getAll(){
		List<CommunicationLink> cls = clRepository.findAll();
		List<CommunicatonLinkDTO> clsDTO = new ArrayList<CommunicatonLinkDTO>();
		
		for(CommunicationLink cl : cls) {
			clsDTO.add(new CommunicatonLinkDTO(cl));
		}
		
		return clsDTO;
	}
	
}

