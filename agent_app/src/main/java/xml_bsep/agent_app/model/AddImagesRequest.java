package xml_bsep.agent_app.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "images"
})
@XmlRootElement(name = "add_images_request")
public class AddImagesRequest {

	@XmlElement(required = true)
	private List<Image> images;
	
	public AddImagesRequest() {
		// TODO Auto-generated constructor stub
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
}
