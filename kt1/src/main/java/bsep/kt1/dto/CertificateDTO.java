package bsep.kt1.dto;

import java.util.Date;



import bsep.kt1.model.Certificate;

public class CertificateDTO {

	private long serialNumber;
	private String country;
	private String city;
	private String softwareModule;
	private Date fromDate;
	private Date toDate;
	private boolean ca;
	
	public CertificateDTO(Certificate certificate) {
		this.country = certificate.getCountry();
		this.city = certificate.getCity();
		this.softwareModule = certificate.getSoftwareModule();
		this.fromDate = certificate.getFromDate();
		this.toDate = certificate.getFromDate();
		this.ca = certificate.isCa();
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
}
