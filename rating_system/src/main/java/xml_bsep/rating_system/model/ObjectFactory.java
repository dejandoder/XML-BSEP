//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.30 at 02:14:23 PM CEST 
//


package xml_bsep.rating_system.model;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.hotel_team1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.hotel_team1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AccomodationUnit }
     * 
     */
    public AccomodationUnit createAccomodationUnit() {
        return new AccomodationUnit();
    }

    /**
     * Create an instance of {@link AccomodationType }
     * 
     */
    public AccomodationType createAccomodationType() {
        return new AccomodationType();
    }

    /**
     * Create an instance of {@link AccomodationService }
     * 
     */
    public AccomodationService createService() {
        return new AccomodationService();
    }

    /**
     * Create an instance of {@link Recension }
     * 
     */
    public Recension createRecension() {
        return new Recension();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link PricePlan }
     * 
     */
    public PricePlan createPricePlan() {
        return new PricePlan();
    }

    /**
     * Create an instance of {@link AccomodationUnit.Location }
     * 
     */
    public Location createAccomodationUnitLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMesssage() {
        return new Message();
    }
        
    public SyncRecensionsRequest createSyncRecensionsRequest(){
    	return new SyncRecensionsRequest();
    }
    
    public SyncRecensionsResponse createSyncRecensionsResponse(){
    	return new SyncRecensionsResponse();
    }

}
