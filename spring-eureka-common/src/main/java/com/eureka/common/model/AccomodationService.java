//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.30 at 02:14:23 PM CEST 
//


package com.eureka.common.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="PARKING"/>
 *               &lt;enumeration value="WIFI"/>
 *               &lt;enumeration value="DORUCAK"/>
 *               &lt;enumeration value="POLU_PANSION"/>
 *               &lt;whiteSpace value="preserve"/>
 *               &lt;enumeration value="PANSION"/>
 *               &lt;enumeration value="ALL_INCLUSIVE"/>
 *               &lt;enumeration value="KUCNI_LJUBIMCI"/>
 *               &lt;enumeration value="TV"/>
 *               &lt;enumeration value="KUHINJA"/>
 *               &lt;enumeration value="PRIVATNO_KUPATILO"/>
 *               &lt;enumeration value="BESPLATNO_OTKAZIVANJE"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "", propOrder = {
    "name",
    "id"
})
@XmlRootElement(name = "accomodation_service")
public class AccomodationService {

    @XmlElement(required = true)
    protected String name;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(required = true)
    protected long id;
    
    public AccomodationService() {
    	
    }
    
    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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

}
