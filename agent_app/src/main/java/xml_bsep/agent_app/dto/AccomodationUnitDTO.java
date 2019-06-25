package xml_bsep.agent_app.dto;

import java.math.BigInteger;
import java.util.ArrayList;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import xml_bsep.agent_app.model.AccomodationType;
import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.Location;

public class AccomodationUnitDTO {

    protected AccomodationType accomodationType;  
    
    @Min(0)
    protected int category;
    //protected List<AccomodationService> services;
    @Size(min=0,max=200)
    protected String description;
    //protected List<PricePlan> pricePlans;
    @Min(1)
    protected BigInteger capacity;
    @NotBlank(message="id must not be blank")
    protected long id;
    @Min(0)
    protected Integer cancelingPeriod;
    protected Location location;
    @Size(min=1,max=40)
    protected String name;
    protected ArrayList<String> images;
    @Min(0)
    protected long distance;
    @Min(1)
    @Max(5)
    protected float rating;
    @Min(0)
    protected float price;
    
    public AccomodationUnitDTO(AccomodationUnit accUnit){
    	this.id = accUnit.getId();
    	this.category = accUnit.getCategory();
    	this.accomodationType = accUnit.getAccomodationType();
    	this.description = accUnit.getDescription();
    	this.cancelingPeriod = accUnit.getCancelingPeriod();
    	this.capacity = accUnit.getCapacity();
    	this.location = accUnit.getLocation();
    	this.name = accUnit.getName();
    	this.images = new ArrayList<>();
    }
  
    public AccomodationUnitDTO() {
    	
    }

	public AccomodationType getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(AccomodationType accomodationType) {
		this.accomodationType = accomodationType;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getCapacity() {
		return capacity;
	}

	public void setCapacity(BigInteger capacity) {
		this.capacity = capacity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getCancelingPeriod() {
		return cancelingPeriod;
	}

	public void setCancelingPeriod(Integer cancelingPeriod) {
		this.cancelingPeriod = cancelingPeriod;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
	public void addImage(String image) {
		images.add(image);
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}	
}
