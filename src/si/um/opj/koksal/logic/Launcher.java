package si.um.opj.koksal.logic;


import si.um.opj.koksal.logic.facility.Store;
import si.um.opj.koksal.logic.facility.Warehouse;
import si.um.opj.koksal.logic.transport.Truck;
import si.um.opj.koksal.logic.transport.Van;
import si.um.opj.koksal.logic.transport.Vehicle;

import java.time.LocalDate;

import static si.um.opj.koksal.logic.FoodItemType.FRESH;

public class Launcher {
    public static void main(String[] args)  {

        String loccity = "Amsterdam" ;
        String loccountry = "Netherlands" ;

        String arrivalCity  = "Maribor" ;
        String arrivalCountry  ="Slovenia" ;

        String storeName = "Hofer" ;
        String nameWarehouse = "amsterdamWarehouse" ;


        int registrationNumber = 1 ;
        int volume = 10000;
        int maxWeight =   100 ;
        int averageSpeed = 1000 ;
        int numberOfTrails = 12 ;


        try {
            if(registrationNumber < 0 || averageSpeed <= 0 || maxWeight <= 0 || volume <= 0)
            {
                throw new java.lang.IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("UNVALID ARGUMENT");
            e.printStackTrace();
        }


        LocalDate today = LocalDate.now();
        LocalDate  later =  LocalDate.of(2019,04,27);

        Location holland = new Location(loccity,loccountry) ;
        Location slovenia = new Location(arrivalCity,arrivalCountry) ;

        FoodItem apple = new FoodItem("apple" , 250 , 10 ,later ,FRESH);
        FoodItem peace = new FoodItem("peace" , 500 , 10 ,later,FRESH );
        FoodItem orange = new FoodItem("orange" , 200 , 10 ,later,FRESH );
        FoodItem kiwi = new FoodItem("kiwi" , 100 , 10 ,later ,FRESH);

        Warehouse amsterdamWareHouse = new Warehouse(nameWarehouse ,holland,280);

        Vehicle vehicle_2  =new Van(registrationNumber ,averageSpeed ,maxWeight ,volume,FRESH);

        Van van = new Van(registrationNumber ,averageSpeed ,maxWeight ,volume,FRESH) ;

        amsterdamWareHouse.addItem(apple);
        amsterdamWareHouse.addItem(peace);
        amsterdamWareHouse.addItem(orange);
        amsterdamWareHouse.addItem(kiwi);

        System.out.println(amsterdamWareHouse.toString());



        System.out.println("BEFORE ADDING");
        try {
            //amsterdamWareHouse.acceptVehicle(van);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("AFTER ACCEPTING");


        System.out.println(amsterdamWareHouse.toString());




        System.out.println("---------------------");

        System.out.println(van);


        Store slovStore = new Store(storeName , slovenia) ;

        Route route = new Route (slovStore , amsterdamWareHouse,5000) ;


        //-------------------------------------









//-------------------------------------

        Truck truck = new Truck(registrationNumber ,averageSpeed ,maxWeight ,2000,1);

        amsterdamWareHouse.addItem(apple);
        amsterdamWareHouse.addItem(orange);


        System.out.println("TRUCK STARTS \n");
        System.out.println(truck.toString() );
        try {
            amsterdamWareHouse.acceptVehicle(truck);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(truck.toString() );

        System.out.println(truck.returnTheNumberOfFoodItems() );

        System.out.println(truck.getTakenSpace() );

        truck.unloadFoodItems() ;


    }



}

