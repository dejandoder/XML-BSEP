package xml_bsep.agent_app.dto;

import java.util.Date;

import xml_bsep.agent_app.model.PricePlan;

public class PricePlanDTO {
	
    protected Date fromDate;
    protected Date toDate;
    protected float pricePerNight;
    protected long id;
    protected long accID;
    
    public PricePlanDTO(PricePlan pricePlan) {
		// TODO Auto-generated constructor stub
    	this.fromDate = pricePlan.getFromDate();
    	this.toDate = pricePlan.getToDate();
    	this.pricePerNight = pricePlan.getPricePerNight();
    	this.id = pricePlan.getId();
    	
    }

    
    public PricePlanDTO() {
		// TODO Auto-generated constructor stub
	}
    
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public float getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(float pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public long getAccID() {
		return accID;
	}

	public void setAccID(long accID) {
		this.accID = accID;
	}
    	
}
