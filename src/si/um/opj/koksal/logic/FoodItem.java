package si.um.opj.koksal.logic;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author : caghankoksal
 * @version  : 1.0
 */
/**
 * FOODITEM CLASS
 */



public class FoodItem implements Serializable {
    private String label;
    private int volume;
    private int weight;
    private LocalDate expirationDate ;
    private FoodItemType foodItemType;

    /**
     * Default constructor
     */
    public FoodItem(){} ;

    /**
     *
     * @param label Label is the label of the Item.
     */
    public FoodItem(String label)
    {
        this.label = label ;
    }

    /**
     *
     * @param label  Label is the label of the Item
     * @param volume Volume is volume of the Item
     * @param weight Weight is weight of the Item
     * @param expirationDate expiration date is the last day of the Item to use
     */
    private FoodItem(String label , int volume , int weight , LocalDate expirationDate)
    {
        this(label) ;
        this.volume = volume ;
        this.weight = weight ;
        this.expirationDate = expirationDate ;
    }
    public FoodItem(String label , int volume , int weight , LocalDate expirationDate, FoodItemType type) {
        this(label,volume,weight,expirationDate) ;
        this.foodItemType  = type ;
    }

    /**
     * GETTER
     * @return label of Iten
     */

    public String getLabel() {
        return label;
    }

    /**
     * SETTER FUNCTION
     * @param label takes label and set Item's label
     */
    public void setLabel(String label) {
        this.label = label;
    }


    /**
     *GETTER
     * @return volume of foodItem
     */
    public int getVolume() {
        return volume;
    }

    public FoodItemType getFoodItemType() {
        return foodItemType;
    }

    public int getWeight() {
        return weight;
    }

    public void setFoodItemType(FoodItemType foodItemType) {
        this.foodItemType = foodItemType;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     *SETTER
     * @param volume takes volume and sets it
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "FoodItem' label is :" + label  + " volume of FoodItem is : " + volume + " weight of FoodItem is : " + weight +" Expiration date of Item is : " + expirationDate  ;
    }
}
