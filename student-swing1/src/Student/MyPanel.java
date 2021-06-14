package Student;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private String picture;
    public MyPanel(String picture){
        super();
        this.picture = picture;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       ImageIcon img = new ImageIcon(picture);
       img.setImage(img.getImage().getScaledInstance(this.getWidth(),this.getHeight(), Image.SCALE_AREA_AVERAGING));
       g.drawImage(img.getImage(),0,0,this);
    }
}
