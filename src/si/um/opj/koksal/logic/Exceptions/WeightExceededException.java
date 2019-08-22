package si.um.opj.koksal.logic.Exceptions;

import si.um.opj.koksal.ui.Gui;

import java.io.IOException;
import java.time.LocalDateTime;

public class WeightExceededException extends Exception{
    public WeightExceededException() {
        super("Weight Exceeded");
        try{
            Gui.writer.write(("WEIGHT EXCEEDED  EXPCEPTION at:"+ LocalDateTime.now()+",CRITICAL"+'\n').getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
