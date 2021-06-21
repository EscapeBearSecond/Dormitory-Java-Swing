package Test;

import LoadSkin.LoadSkin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test4 extends JFrame{
    JButton btn1;
    JButton btn2;
    JLabel label;
    static {
        LoadSkin.LoadMySkin();
    }
    public Test4(){
        btn1 = new JButton("顶顶顶顶顶");
        btn2 = new JButton("顶顶顶顶顶");


        add(BorderLayout.NORTH,btn1);
        add(BorderLayout.SOUTH,btn2);

        setSize(600,600);
        setVisible(true);
    }
}
