package si.um.opj.koksal.logic.transport;

/**
 * @author caghankoksal
 * @version 1.3
 */

import si.um.opj.koksal.logic.Exceptions.CapacityExceededException;
import si.um.opj.koksal.logic.Exceptions.FoodItemTypeException;
import si.um.opj.koksal.logic.Exceptions.VolumeExceededException;
import si.um.opj.koksal.logic.Exceptions.WeightExceededException;
import si.um.opj.koksal.logic.FoodItem;
import si.um.opj.koksal.logic.Route;

import java.io.Serializable;
import java.util.Arrays;

/**
 * VEHICLE CLASS
 */

/*
warehouse store si.um.opj.koksal.logic.facility
um.logic where you start class */
public abstract class Vehicle implements Runnable, Serializable {
    private int registrationNumber ;
    protected double volume ;
    private double maxWeight ;
    private double averageSpeed ;
    private FoodItem[] cargo ;


    /**
     * Default constructor
     */
    public Vehicle(){

    }

    /**
     *
     * @param registrationNumber registration number of si.um.opj.koksal.logic.transport.Vehicle
     * @param averageSpeed averageSpeed of car
     */
    private  Vehicle(int registrationNumber ,  double averageSpeed)
    {
        this.registrationNumber = registrationNumber ;
        this.averageSpeed = averageSpeed ;
    }
    public  Vehicle(int registrationNumber ,  double averageSpeed , double maxWeight , double volume)
    {

            this(registrationNumber, averageSpeed);
            this.maxWeight = maxWeight;
            this.volume = volume;
            this.cargo = new FoodItem[1]; // what is the capacity of Array?????
        }


    /**
     *
     * @param registrationNumber registration number of vehicle
     * @param volume volume of vehicle
     * @param maxWeight  maximum weight of vehicle
     * @param averageSpeed average speed of vehicle
     */
    public Vehicle(int registrationNumber , int volume , int maxWeight , double averageSpeed)
    {
        this(registrationNumber,averageSpeed) ;
        this.maxWeight  = maxWeight ;
        this. volume = volume ;
    }

    /**
     *
     * @return number of foodItem in array
     */
    public int returnTheNumberOfFoodItems()
    {
        int count = 0 ;

        for(int j = 0 ; j < cargo.length ; j++)
        {
            if(cargo[j] != null)
            {
                count ++ ;
            }

        }
        return count ;
    }
    /**
     *
     * @return number of foodItem in array
     */
    public int returnTheNumberOfFoodItemsForAddıtıon(FoodItem[] foodItems) //It counts newly added array
    {
        int count = 0 ;

        for(int j = 0 ; j < foodItems.length ; j++)
        {
            if(foodItems[j] != null)
            {
                count ++ ;
            }

        }
        return count ;
    }
    public void unloadFoodItems()
    {
        int numItems = this.returnTheNumberOfFoodItems();
        for(int k = 0  ; k <numItems ; k++ )
        {
            this.cargo[k] = null ;
        }
    }
    /**
     *
     * @param foodItem  fooditem to add to array
     */
    public void loadFoodItem(FoodItem foodItem) throws CapacityExceededException, VolumeExceededException,WeightExceededException,FoodItemTypeException
    {

            if (this.returnTheNumberOfFoodItems() == cargo.length)
            {
                unloadFoodItems();
                throw new CapacityExceededException();



            }
            else if(foodItem.getVolume() + getTotalVolume(cargo) > this.getVehiclesMaxVolume())
            {
                unloadFoodItems();
                throw new VolumeExceededException();

            }
            else if(foodItem.getWeight() + getTotalWeight(cargo) > getMaxWeight())
            {
                unloadFoodItems();
                throw new WeightExceededException();

            }
            else if(this instanceof Van && ((Van) this).getFoodItemType() != foodItem.getFoodItemType()) //VAN FOOD ITEM TYPE EXCEPTION
            {
                throw new FoodItemTypeException();

            }


            else
            {

                this.cargo[this.returnTheNumberOfFoodItems()] = foodItem;
                System.out.println(foodItem + " is LOADED");


            }




    }
    public void LoadFoodItem(FoodItem[] foodItems) throws Exception {

            if (this.returnTheNumberOfFoodItems() + returnTheNumberOfFoodItemsForAddıtıon(foodItems) > this.cargo.length) {
                unloadFoodItems();
                    throw new Exception("CapacityExceededException");

            }
            else if (getTotalVolume(cargo) + getTotalVolume(foodItems) > getVehiclesMaxVolume()) {
                unloadFoodItems();
                throw new Exception("VolumeExceededException");
            }

            else if(getTotalWeight(foodItems) + getTotalWeight(cargo) > getMaxWeight())
            {
                unloadFoodItems();
                throw new WeightExceededException();
            }

            else
                {
                for (int k = 0; k < returnTheNumberOfFoodItemsForAddıtıon(foodItems); k++) {
                    this.loadFoodItem(foodItems[k]);
                }
            }

    }

    public double getTakenSpace() //which returns the percentage of the volume taken up by loaded food items in a vehicle;
    {
        double totalVolumeInCargo = 0 ;
        double percentage = 0 ;
        int numItems = returnTheNumberOfFoodItems();
        for(int j = 0 ; j < numItems ; j++)
        {
            totalVolumeInCargo += cargo[j].getVolume() ;
        }
        percentage = totalVolumeInCargo / getVehiclesMaxVolume() ;
        return percentage*100 ;

    }
    public double getTotalVolume(FoodItem[] foodItems) //which returns the  volume taken up by loaded food items in a vehicle;
    {
        double totalVolumeInCargo = 0 ;
        int numItems = returnTheNumberOfFoodItems();
        for(int j = 0 ; j < numItems ; j++)
        {
            totalVolumeInCargo += foodItems[j].getVolume() ;
        }
        return totalVolumeInCargo ;

    }
    public double getTotalWeight(FoodItem[] foodItems) //which returns the  volume taken up by loaded food items in a vehicle;
    {
        double totalWeight = 0 ;
        int numItems = returnTheNumberOfFoodItems();
        for(int j = 0 ; j < numItems ; j++)
        {
            totalWeight += foodItems[j].getWeight() ;
        }
        return totalWeight ;

    }





    /**
     *
     * @return returns the total volume of the vehicle
     */
    abstract public double getVehiclesMaxVolume()  ;

    /**
     * Setter
     * @param averageSpeed sets averageSpeed
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * Setter
     * @param maxWeight sets weight
     */
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * Setter
     * @param registrationNumber takes registration number as param and sets it
     */
    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Setter
     * @param volume sets the Volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * getter
     * @return averageSpeed
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * getter
     * @return maxWeight
     */
    public double getMaxWeight() {
        return maxWeight;
    }

    /**
     * Getter
     * @return registrationNumber
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Getter
     * @return volume
     */
    public double getVolume() {
        return volume;
    }

    /**
     *
     * @param route takes Route object as param
     * @return total time of travel in day
     */
    public double calculateTravelTime(Route route )
    {
        //number of travel days based on the averageSpeed and distance attributes.

        double travelTime;
        travelTime = Math.ceil(route.getDistance()/ averageSpeed/24) ;
        return travelTime ;
    }

    @Override
    public String toString() {
        return "registration number is : " + registrationNumber + " Volume is : " + volume + " Maximum Weight is : " + maxWeight + " averageSpeed is : "+ averageSpeed + Arrays.toString(cargo) ;
    }
}
