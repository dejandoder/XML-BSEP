package xml_bsep.acc_service.dto;

import java.util.ArrayList;
import java.util.List;

import xml_bsep.acc_service.model.AccomodationType;
import xml_bsep.acc_service.model.AccomodationUnit;
import xml_bsep.acc_service.model.Location;



public class AccomodationUnitDTO {

    protected AccomodationType accomodationType;
    protected int category;
    protected String description;
    protected List<String> images = new ArrayList<>();
    protected int capacity;
    protected long id;
    protected Integer cancelingPeriod;
    protected Location location;
    protected String name;
    protected float price;
    protected double distance;
    protected float rating;

    public AccomodationUnitDTO(AccomodationUnit accUnit) {
		// TODO Auto-generated constructor stub
    	this.accomodationType = accUnit.getAccomodationType();
    	this.category = accUnit.getCategory();
    	this.description = accUnit.getDescription();
    	this.capacity = accUnit.getCapacity();
    	this.id = accUnit.getId();
    	this.cancelingPeriod = accUnit.getCancelingPeriod();
    	this.location = accUnit.getLocation();
    	this.name = accUnit.getName();	
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public void addImage(String image) {
		images.add(image);
	}
}
