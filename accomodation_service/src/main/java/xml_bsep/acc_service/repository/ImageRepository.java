package xml_bsep.acc_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eureka.common.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

	@Query("select image from Image image where image.accomodationUnit.id = :accId")
	public List<Image> getImagesByAccUnit(@Param("accId") long accId);
	
}
