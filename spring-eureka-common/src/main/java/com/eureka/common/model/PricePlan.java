//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.30 at 02:14:23 PM CEST 
//


package com.eureka.common.model;

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
 *         &lt;element name="from" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="to" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="pricePerNight">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *               &lt;minExclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/hotel-team1}accomodation_unit"/>
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
@XmlType(name = "pricePlan", propOrder = {
    "fromDate",
    "toDate",
    "pricePerNight",
    "id",
    "accomodationUnit"
})
public class PricePlan {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date fromDate;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected Date toDate;
   
    protected float pricePerNight;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(required = true)
    protected long id;
    
    @ManyToOne
    @JoinColumn(name = "accomodation_unit_id", foreignKey = @ForeignKey(name = "PRICE_PLAN_ACCOMODATION_UNIT_ID_FK"))
    @XmlElement(name = "accomodation_unit", required = true)
    protected AccomodationUnit accomodationUnit;

    public PricePlan() {
    	
    }
    
    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the from property.
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
     * Gets the value of the to property.
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
     * Sets the value of the to property.
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
     * Gets the value of the pricePerNight property.
     * 
     */
    public float getPricePerNight() {
        return pricePerNight;
    }

    /**
     * Sets the value of the pricePerNight property.
     * 
     */
    public void setPricePerNight(float value) {
        this.pricePerNight = value;
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
     * Gets the value of the accomodationUnit property.
     * 
     * @return
     *     possible object is
     *     {@link AccomodationUnit }
     *     
     */
    public AccomodationUnit getAccomodationUnit() {
        return accomodationUnit;
    }

    /**
     * Sets the value of the accomodationUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccomodationUnit }
     *     
     */
    public void setAccomodationUnit(AccomodationUnit value) {
        this.accomodationUnit = value;
    }

}
