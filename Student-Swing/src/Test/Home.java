package Test;

import LoadSkin.LoadSkin;

import javax.swing.*;

public class Home extends JFrame {
    JMenuBar jmb;
    JMenu menu1;
    JMenu menu2;
    JMenu menu3;
    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
    JMenuItem item4;
    JMenuItem item5;
    JMenuItem item6;
    JMenuItem item7;
    JMenuItem item8;
    JMenuItem item9;
    JPanel panel;

    static {
        LoadSkin.LoadMySkin();
    }
    public Home(){
        this.setTitle("主页");
        jmb = new JMenuBar();

        panel = new JPanel();

        menu1 = new JMenu("F1");
        menu2 = new JMenu("F2");
        menu3 = new JMenu("F3");

        item1 = new JMenuItem("F1-1");
        item2 = new JMenuItem("F1-2");
        item3 = new JMenuItem("F1-3");
        item4 = new JMenuItem("F2-1");
        item5 = new JMenuItem("F2-2");
        item6 = new JMenuItem("F2-3");
        item7 = new JMenuItem("F3-1");
        item8 = new JMenuItem("F3-2");
        item9 = new JMenuItem("F3-3");

        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu2.add(item4);
        menu2.add(item5);
        menu2.add(item6);
        menu3.add(item7);
        menu3.add(item8);
        menu3.add(item9);

        jmb.add(menu1);
        jmb.add(menu2);
        jmb.add(menu3);
        this.add(panel);

        setJMenuBar(jmb);
        setSize(400,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Home home = new Home();
    }
}
