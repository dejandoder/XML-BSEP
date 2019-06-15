package xml_bsep.agent_app.dto;

import java.math.BigInteger;
import java.util.ArrayList;

import xml_bsep.agent_app.model.AccomodationType;
import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.Location;

public class AccomodationUnitDTO {

    protected AccomodationType accomodationType;   
    protected int category;
    //protected List<AccomodationService> services;
    protected String description;
    //protected List<PricePlan> pricePlans;
    protected BigInteger capacity;
    protected long id;
    protected Integer cancelingPeriod;
    protected Location location;
    protected String name;
    protected ArrayList<String> images;
    protected long distance;
    protected float rating;
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
