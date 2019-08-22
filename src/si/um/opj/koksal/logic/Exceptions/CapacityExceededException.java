package si.um.opj.koksal.logic.Exceptions;

import si.um.opj.koksal.ui.Gui;

import java.io.IOException;
import java.time.LocalDateTime;


public class CapacityExceededException extends Exception {
    public CapacityExceededException()
    {
        super("CAPACITY EXCEEDED");
        try{
            Gui.writer.write(("CAPACITY EXCEED EXPCEPTION at:"+ LocalDateTime.now()+",CRITICAL"+'\n').getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
