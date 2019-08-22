package si.um.opj.koksal.logic.facility;

import si.um.opj.koksal.logic.FoodItem;
import si.um.opj.koksal.logic.Location;
import si.um.opj.koksal.logic.Transportable;
import si.um.opj.koksal.logic.transport.Truck;
import si.um.opj.koksal.logic.transport.Van;
import si.um.opj.koksal.logic.transport.Vehicle;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * CLASS FOR WAREHOUSE
 * @author : caghankoksal
 * @version  : 1.3
 */

public class Warehouse extends BusinessFacility implements Transportable, Serializable {
    //private String name ;
    //private Location location ;
    private FoodItem[] foodItems ;


    /**
     * Default constructor
     */
    private Warehouse()
    {

        super() ;
    }

    /**
     *
     * @param name name of the Warehouse
     * @param location location of the Warehouse
     */
    private Warehouse(String name , Location location)
    {

        super(name, location);
    }

    /**
     * NEW CONSTRUCTOR FOR ASSIGNMENT2
     * @param name name of the warehouse
     * @param location location of the warehouse
     * @param capacity capacity of the warehouse
     */
    public Warehouse(String name , Location location , int capacity)
    {
        super(name,location);
        this.foodItems = new FoodItem[capacity] ;
    }


    /*  Warehouse class must load the accepted vehicle with food items that are
currently in the warehouse */
    @Override
    public void acceptVehicle(Vehicle vehicle) throws Exception {
      int numberOffoodItem = returnTheNumberOfFoodItems();

        if(vehicle instanceof Truck)
        {
         vehicle.LoadFoodItem(foodItems);
         for(int k = 0 ; k < numberOffoodItem; k++)
            this.removeItem(foodItems[0]);


        }
        if(vehicle instanceof Van) {
            for (int k = 0 ; k < numberOffoodItem ; k++)
            {
                    vehicle.loadFoodItem(foodItems[k]);
                    //this.removeItem(foodItems[k]);


            }

             for (int k = 0 ; k <numberOffoodItem ; k++)
             {
                     this.removeItem(foodItems[0]);


             }

        }
    }

    /* Onto a truck, we can load a whole array of food items,
while loading onto a van is done one food item at a time.*/


    /**
     *
     * @param foodItem  fooditem to add to array
     */

    public void addItem(FoodItem foodItem)
    {
        if(stillValid(foodItem)) {
            this.foodItems[this.returnTheNumberOfFoodItems()] = foodItem;
        }
        else
            System.out.println("It cannot be added");

    }

    /**
     *
     * @param foodItem foodItem for removing from array
     */
    public void removeItem(FoodItem foodItem)
    {
        int i=0;
        int k = 0 ;

        if(this.foodItemExists(foodItem)) // If there the si.um.opj.koksal.logic.FoodItem exist in the array
        {
            for(i = 0 ; i < this.returnTheNumberOfFoodItems(); i++)
            {
                if(foodItems[i] == foodItem) // What if There is 2 same foodItem
                {
                    break;
                }
                FoodItem storedFoodItem = foodItem ; //THINK ABOUT IT
            }
            for( k = i ; k < this.returnTheNumberOfFoodItems()-1 ; k++ )
            {
                foodItems[k] = foodItems[k+1];
            }
            foodItems[k] = null ;

            System.out.println("foodItem : " + foodItem.getLabel() + " is deleted ");
        }


    }

    /**
     *
     * @return number of foodItem in array
     */
    public int returnTheNumberOfFoodItems()
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

    /**
     *
     *
     * @param foodItem Item for searching in Array
     * @return boolean true or false
     */
    public boolean foodItemExists(FoodItem foodItem)
    {
        boolean found = false ;
        for (int i = 0 ; i < this.returnTheNumberOfFoodItems() ; i++)
        {
            if(foodItems[i].getLabel() == foodItem.getLabel())
            {
                found = true ;
            }
        }
        return found;
    }

    private boolean stillValid(FoodItem foodItem)
    {
        if(LocalDate.now().plusDays(3).isBefore(foodItem.getExpirationDate())) {
            return true;
        }
        else
            return false;
    }

    public FoodItem[] getFoodItems() {
        return foodItems;
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setLocation(Location location) {
        super.setLocation(location);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public void setFoodItems(FoodItem[] foodItems) {
        this.foodItems = foodItems;
    }
    public void setCapacity(int capacity)
    {
        FoodItem [] foodItemArray = Arrays.copyOf(foodItems,capacity);
        this.setFoodItems(foodItemArray);

    }

    @Override
    public String toString() {

        return name +" is Warehouse"+" in " +location.getCity()  ;

        /*String ware_house =  "Name of warehouse is : " + super.toString() + "foodItems in the store is :" +'\n'  ;

        for(int k = 0 ; k < this.returnTheNumberOfFoodItems() ; k++)
        {
            ware_house = ware_house + foodItems[k] + '\n' ;
        }

        return ware_house ;*/

    }



}
