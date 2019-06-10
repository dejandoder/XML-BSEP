package xml_bsep.agent_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xml_bsep.agent_app.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	
}
