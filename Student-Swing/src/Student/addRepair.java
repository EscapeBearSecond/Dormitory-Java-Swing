package Student;

import javax.swing.*;

/**
 * @author Dyg
 */
public class addRepair extends JDialog {
    JPanel panel;
    public addRepair(){
        panel = new JPanel();
        this.add(panel);
        this.setSize(200,200);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
