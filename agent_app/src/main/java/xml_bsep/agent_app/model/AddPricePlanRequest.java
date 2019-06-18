package xml_bsep.agent_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pricePlan"
})
@XmlRootElement(name = "add_price_plan_request")
public class AddPricePlanRequest {

	@XmlElement(required = true)
	private PricePlan pricePlan;
	
	public AddPricePlanRequest() {
		// TODO Auto-generated constructor stub
	}

	public PricePlan getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(PricePlan pricePlan) {
		this.pricePlan = pricePlan;
	}
	
	
}
