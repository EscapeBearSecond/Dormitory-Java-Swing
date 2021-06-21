package Handler;

import Student.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AbsenceHandler implements ActionListener {
    private Absence absence;


    public AbsenceHandler(Absence absence){
        this.absence = absence;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == absence.label){

        }
    }
}
