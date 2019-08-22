package si.um.opj.koksal.logic.facility;
/**
 * @author : caghankoksal
 * @version  : 1.0
 */

import si.um.opj.koksal.logic.Location;
import si.um.opj.koksal.logic.Transportable;
import si.um.opj.koksal.logic.transport.Vehicle;

import java.io.Serializable;

/**
 * STORE CLASS
 */

public class Store extends BusinessFacility implements Transportable, Serializable {

    /**
     *
     * @param name Name of the store
     * @param location location object
     */
    public Store(String name , Location location)
    {
        super(name,location);

    }

    /**
     *Default Constructor
     */
    private Store(){
        super();
    }


    /**
     * @return
     */
    @Override
    public String toString() {

        return name +" is Store in : " + location.getCity();
        /*
        return "store's " + super.toString();
         */
    }

    /* The method acceptVehicle in the class
Store unloads all food items from the vehicle.*/
    @Override
    public void acceptVehicle(Vehicle vehicle)
    {
        vehicle.unloadFoodItems();

    }
}
