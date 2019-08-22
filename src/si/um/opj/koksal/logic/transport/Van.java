package si.um.opj.koksal.logic.transport;

import si.um.opj.koksal.logic.FoodItemType;

public class Van extends Vehicle {

    private FoodItemType foodItemType;


    private Van(){
        super() ;
    }
    /*
    public Van(int registrationNumber ,  double averageSpeed , double maxWeight , double volume)
    {
        super(registrationNumber,averageSpeed,maxWeight,volume) ;

    }*/
    //int registrationNumber ,  double averageSpeed , double maxWeight , double volume
    public Van(int registrationNumber , double averageSpeed , double maxWeight , double volume, FoodItemType type)
    {
        super(registrationNumber,averageSpeed,maxWeight,volume) ;
        this.foodItemType = type ;

    }

    public FoodItemType getFoodItemType() {
        return foodItemType;
    }

    @Override
    public double getVehiclesMaxVolume() {
        return this.volume;
    }

    public void setFoodItemType(FoodItemType foodItemType) {
        this.foodItemType = foodItemType;
    }


    @Override
    public String toString() {
        return  "Van registraion num : " + getRegistrationNumber() ;
        //return "Van's " + super.toString() +"FoodItemType is : " + " " + "Van's food Item Type is :" +foodItemType;
    }

    @Override
    public void run() {

    }
}
