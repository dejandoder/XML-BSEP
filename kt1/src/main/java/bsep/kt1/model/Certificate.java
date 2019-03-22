package bsep.kt1.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Certificate {
	
	
	@Id
	@Column(length = 100)
    @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String serialNumber;
	private String city;
	private String softwareModule;
	private Date fromDate;
	private Date toDate;
	private boolean ca;
	private String caSerialNumber;
	private boolean revoked;
	
	public Certificate() {
	}
	
	public Certificate(String country, String city, String softwareModule, Date fromDate, Date toDate, boolean ca, String caSerialNumber) {
		super();
		this.city = city;
		this.softwareModule = softwareModule;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.ca = ca;
		this.caSerialNumber = caSerialNumber;
		this.revoked = false;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	public String getCaSerialNumber() {
		return caSerialNumber;
	}

	public void setCaSerialNumber(String caSerialNumber) {
		this.caSerialNumber = caSerialNumber;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}
	
	
	
}
