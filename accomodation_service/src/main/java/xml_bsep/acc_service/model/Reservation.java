//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.30 at 02:14:23 PM CEST 
//


package xml_bsep.acc_service.model;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://www.ftn.uns.ac.rs/hotel-team1}user"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="confirmed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fromDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="toDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="agentReserved" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservation", propOrder = {
    "user",
    "id",
    "status",
    "fromDate",
    "toDate",
    "agentReserved",
    "accUnit"
})
public class Reservation {

	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "RESERVATION_USER_ID_FK"))
    @XmlElement(required = true)
    protected User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    
    @XmlElement(required = true)
    protected ReservationStatus status;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date fromDate;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date toDate;
    
    @XmlElement(required = true)
    protected boolean agentReserved;

    @ManyToOne
	@JoinColumn(name = "acc_unit_id", foreignKey = @ForeignKey(name = "RESERVATION_ACC_UNIT_ID_FK"))
    @XmlElement(required = true)
    protected AccomodationUnit accUnit;
    
    public Reservation() {
    	
    }
    
    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }
    
    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getFromDate() {
        return fromDate;
    }

    public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	/**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFromDate(Date value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setToDate(Date value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the agentReserved property.
     * 
     */
    public boolean isAgentReserved() {
        return agentReserved;
    }

    /**
     * Sets the value of the agentReserved property.
     * 
     */
    public void setAgentReserved(boolean value) {
        this.agentReserved = value;
    }

	public AccomodationUnit getAccUnit() {
		return accUnit;
	}

	public void setAccUnit(AccomodationUnit accUnit) {
		this.accUnit = accUnit;
	}   
}
