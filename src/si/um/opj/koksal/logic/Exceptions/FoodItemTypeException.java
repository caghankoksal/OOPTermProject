package si.um.opj.koksal.logic.Exceptions;

import si.um.opj.koksal.ui.Gui;

import java.io.IOException;
import java.time.LocalDateTime;

public class FoodItemTypeException extends Exception {
    public FoodItemTypeException()
    {
        super("Food Item Type Exception");
        try{
            Gui.writer.write(("FOOD ITEM TYPE EXPCEPTION at:"+ LocalDateTime.now()+",CRITICAL"+'\n').getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
