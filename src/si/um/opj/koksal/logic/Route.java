package si.um.opj.koksal.logic;

/**
 * @author : caghankoksal
 * @version  : 1.3
 */
/**
 * ROUTE CLASS
 */


import si.um.opj.koksal.logic.facility.Store;
import si.um.opj.koksal.logic.facility.Warehouse;

public class Route {
    private Store store ;
    private Warehouse warehouse;
    private double distance ;

    /**
     *
     * @param store Store object  which containsLocation object and name
     * @param warehouse object contains name, location object and food Items array
     * @param distance integer distance
     */
    public Route(Store store ,Warehouse warehouse ,double distance)
    {
        this.store = store ;
        this.warehouse = warehouse ;
        this.distance = distance ;
    }

    /**
     * Default constructor
     */
    public Route(){}


    /**
     * Setter
     * @param distance distance of Route
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Setter
     * @param store Set store attirbute
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Setter
     * @param warehouse Set warehouse attirbute
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Getter
     * @return distance of route
     */
    public double getDistance() {
        return distance;
    }

    /**
     * getter
     * @return store of Route
     */
    public Store getStore() {
        return store;
    }

    /**
     * getter
     * @return warehouse of ROute
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public String toString() {
        return  store + " " + warehouse + " distance of Route is : " + distance ;
    }
}
