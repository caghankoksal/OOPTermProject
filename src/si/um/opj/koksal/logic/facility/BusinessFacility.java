package si.um.opj.koksal.logic.facility;

import si.um.opj.koksal.logic.Location;

import java.io.Serializable;

public class BusinessFacility implements Serializable {
    protected Location location ;
    protected String name ;

    public BusinessFacility(String name, Location location)
    {
        this.name = name;
        this.location = location;

    }
    protected BusinessFacility()
    {}


    /**
     * Setter Function
     * @param location lcation
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Setter Functio
     * @param name Name of the store
     */
    public void setName( String name) {
        this.name = name;
    }

    /**
     *
     * @return name of the store
     */
    public String getName() {
        return name;
    }


    /**
     * @return
     */
    @Override
    public String toString() {
        return  name + " : "+location ;
    }
}
