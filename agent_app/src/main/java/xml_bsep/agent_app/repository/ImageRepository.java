package xml_bsep.agent_app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.agent_app.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	@Query("select image from Image image where image.accomodationUnit.id = :accId ")
	public List<Image> getImagesByAccomodationUnit(@Param("accId") long accId);

	@Query("select image.id from Image image where image.accomodationUnit.id = :accId ")
	public List<Long> getImagesId(@Param("accId") long accId);
}
