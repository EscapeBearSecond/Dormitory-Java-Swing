package Student;

import LoadSkin.LoadSkin;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHome extends JFrame {
    JButton PersonBtn;
    JButton AbsenceBtn ;
    JButton AlterAccountBtn;
    JButton ExitBtn;
    JPanel BtnPanel;
    Home home;  //主页面板
    Absence absence;    //缺勤面板
    static {
        LoadSkin.LoadMySkin();
    }

    public MainHome(){
        Container contentPane = getContentPane();
        home = new Home();
        absence = new Absence();


        PersonBtn = new JButton("个人主页");
        PersonBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\主页.png"));
        PersonBtn.setToolTipText("个人主页");

        AbsenceBtn = new JButton("缺勤情况");
        AbsenceBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\缺勤.png"));
        AbsenceBtn.setToolTipText("缺勤情况");

        AlterAccountBtn = new JButton("切换账户");
        AlterAccountBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\切换账户.png"));
        AlterAccountBtn.setToolTipText("切换账户");

        ExitBtn = new JButton("退出程序");
        ExitBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\退出.png"));
        ExitBtn.setToolTipText("退出程序");

        BtnPanel = new JPanel();
        BtnPanel.setPreferredSize(new Dimension(100,PersonBtn.getHeight()*5));
        BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        BtnPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        BtnPanel.add(PersonBtn);
        BtnPanel.add(AbsenceBtn);
        BtnPanel.add(AlterAccountBtn);
        BtnPanel.add(ExitBtn);
        this.add(BorderLayout.WEST,BtnPanel);
        this.add(BorderLayout.CENTER,home);
        this.add(BorderLayout.CENTER,absence);
        this.setSize(600,600);
        home.setVisible(true);
        absence.setVisible(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AbsenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                home.setVisible(false);
                absence.setVisible(true);
            }
        });
        PersonBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                absence.setVisible(false);
                home.setVisible(true);
            }
        });
        AlterAccountBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });
    }

    public Home getHome() {
        return home;
    }

}
