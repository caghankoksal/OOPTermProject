package si.um.opj.koksal.logic.Exceptions;

import si.um.opj.koksal.ui.Gui;

import java.io.IOException;
import java.time.LocalDateTime;

public class VolumeExceededException extends Exception {
    public VolumeExceededException() {
        super("Volume Exceeded");
        try{
            Gui.writer.write(("VOLUME EXCEEDED  EXPCEPTION at:"+ LocalDateTime.now()+",CRITICAL"+'\n').getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
