package bsep.kt1.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Certificate {
	
	@Id
	private long serialNumber;
	private String country;
	private String city;
	private String softwareModule;
	private Date fromDate;
	private Date toDate;
	private boolean ca;
	private long caSerialNumber;
	
	public Certificate(String country, String city, String softwareModule, Date fromDate, Date toDate, boolean ca, long caSerialNumber) {
		super();
		this.country = country;
		this.city = city;
		this.softwareModule = softwareModule;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.ca = ca;
		this.caSerialNumber = caSerialNumber;
	}

	public long getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSoftwareModule() {
		return softwareModule;
	}

	public void setSoftwareModule(String softwareModule) {
		this.softwareModule = softwareModule;
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

	public boolean isCa() {
		return ca;
	}

	public void setCa(boolean ca) {
		this.ca = ca;
	}

	public long getCaSerialNumber() {
		return caSerialNumber;
	}

	public void setCaSerialNumber(long caSerialNumber) {
		this.caSerialNumber = caSerialNumber;
	}
	
}
