package si.um.opj.koksal.logic.transport;

public class Truck extends Vehicle {

    private int numberOfTrailers;

    /**
     * Default constructor
     */
    private Truck()
    {

    }

    /**
     *
     * @param registrationNumber registration number of vehicle
     * @param averageSpeed avg. speed of vehicle
     * @param maxWeight max weight of vehicle
     * @param volume volume of vehicle
     * @param numberOfTrailers of vehicle
     */


    public Truck(int registrationNumber ,  double averageSpeed , double maxWeight , double volume,int numberOfTrailers)
    {
        super(registrationNumber,averageSpeed,maxWeight,volume) ;
        this.numberOfTrailers = numberOfTrailers ;
    }

    public int getNumberOfTrailers() {
        return numberOfTrailers;
    }

    public void setNumberOfTrailers(int numberOfTrailers) {
        this.numberOfTrailers = numberOfTrailers;
    }

    @Override
    public double getVehiclesMaxVolume() {
        return numberOfTrailers*volume;
    }

    @Override
    public String toString() {

        return  "Truck reg.Num: "+getRegistrationNumber() ;
        //return "Truck's " + super.toString() + "number of trailors is :" + numberOfTrailers ;
    }

    @Override
    public void run() {

    }
}
