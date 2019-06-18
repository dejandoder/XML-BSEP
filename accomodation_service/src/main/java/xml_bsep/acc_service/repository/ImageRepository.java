package xml_bsep.acc_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eureka.common.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
