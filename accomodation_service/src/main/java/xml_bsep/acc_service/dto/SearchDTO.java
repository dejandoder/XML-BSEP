package xml_bsep.acc_service.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import xml_bsep.acc_service.model.AccomodationService;
import xml_bsep.acc_service.model.AccomodationType;
import xml_bsep.acc_service.model.Location;

public class SearchDTO {
	
	@NotEmpty
	private ArrayList<Date> dates;
	
	@Valid
	private Location location;
	
	@Min(1)
	@Max(20)
	private int persons;
	
	@Valid
	private AccomodationType type;
	
	@Min(-1)
	private int category;
	
	@Min(0)
	private int maxDistance;
	
	@Min(0)
	private int cancelationPeriod;
	
	private ArrayList<AccomodationService> services;
	
	public SearchDTO() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Date> getDates() {
		return dates;
	}

	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public AccomodationType getType() {
		return type;
	}

	public void setType(AccomodationType type) {
		this.type = type;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public int getCancelationPeriod() {
		return cancelationPeriod;
	}

	public void setCancelationPeriod(int cancelationPeriod) {
		this.cancelationPeriod = cancelationPeriod;
	}

	public ArrayList<AccomodationService> getServices() {
		return services;
	}

	public void setServices(ArrayList<AccomodationService> services) {
		this.services = services;
	}
	
}
