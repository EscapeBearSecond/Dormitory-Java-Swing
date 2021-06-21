package Handler;

import Student.*;
import sun.applet.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.OpenOption;

public class HomeHandler implements ActionListener {
    private Home home;
    public HomeHandler(Home home){
        this.home = home;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String text = btn.getText();
        if ("修改信息".equals(text)){
        }
    }
}
