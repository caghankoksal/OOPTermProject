package si.um.opj.koksal.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

import static si.um.opj.koksal.ui.Gui.*;


public class Independent implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Vehicle with registration number : " + vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber() + " is deleted");
        try {
            writer.write(("Vehicle with registration number : "+ vehicleALL.get(comboCar.getSelectedIndex()).getRegistrationNumber() +" is deleted at:"+ LocalDateTime.now()).getBytes());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        vehicleALL.remove(comboCar.getSelectedIndex());

        comboCar.updateUI();
        card2.revalidate();
        card2.repaint();




    }
    }

