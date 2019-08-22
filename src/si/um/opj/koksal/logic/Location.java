package si.um.opj.koksal.logic;
/**
 * @author : caghankoksal
 * @version  : 1.3
 */

import java.io.Serializable;

/**
 * LOCATION CLASS
 */
public class Location implements Serializable {
    private String city ;
    private String country ;

    /**
     * Patametrized constructor
     * @param city city of .Location object
     * @param country country of Location object
     */
    public Location(String city , String country)
    {
        this.city = city ;
        this.country =  country ;
    }

    /**
     * Default constructor
     */
    Location(){}

    /**
     * Setter
     * @param city city attirbute of Location object
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Setter
     * @param country country attribute of Location object
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Getter
     * @return city of Location object
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter
     * @return country of Object
     */
    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "city of Location is: " + city  + " country of Location is : " + country ;
    }

}
