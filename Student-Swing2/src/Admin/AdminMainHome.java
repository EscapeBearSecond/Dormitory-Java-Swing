package Admin;

import LoadSkin.LoadSkin;
import Student.Login;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainHome extends JFrame {
    JPanel btnPanel;
    JButton stuBtn;
    JButton absenceBtn;
    JButton alterBtn;
    JButton exitBtn;
    JButton healthBtn;
    JButton repairBtn;
    stuInfo adminHome;
    JPanel jPanel;
    stuInfo stu;
    absenceInfo absence;
    healthInfo health;
    repairInfo repair;
static {
    LoadSkin.LoadMySkin();
}

    public AdminMainHome(){
        adminHome = new stuInfo();
        btnPanel = new JPanel();
        btnPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        stuBtn = new JButton("学生信息");
        stuBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\学生管理.png"));
        stuBtn.setToolTipText("学生信息");

        absenceBtn = new JButton("晚归情况");
        absenceBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\缺勤1.png"));
        absenceBtn.setToolTipText("晚归信息");

        healthBtn = new JButton("卫生情况");
        healthBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\卫生1.png"));
        healthBtn.setToolTipText("卫生信息");

        repairBtn = new JButton("维修情况");
        repairBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\维修1.png"));
        repairBtn.setToolTipText("维修信息");

        alterBtn= new JButton("切换账户");
        alterBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\切换.png"));
        alterBtn.setToolTipText("切换账户");

        exitBtn = new JButton("退出程序");
        exitBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\退出1.png"));
        exitBtn.setToolTipText("退出程序");

        btnPanel.setPreferredSize(new Dimension(100,alterBtn.getHeight()*6));

        btnPanel.add(stuBtn);
        btnPanel.add(absenceBtn);
        btnPanel.add(healthBtn);
        btnPanel.add(repairBtn);
        btnPanel.add(alterBtn);
        btnPanel.add(exitBtn);
        btnPanel.setBackground(new Color(218,172,197));

        jPanel = new JPanel();
        CardLayout cardLayout = new CardLayout(5, 5);
        jPanel.setLayout(cardLayout);
        stu = new stuInfo();
        absence = new absenceInfo();
        health = new healthInfo();
        repair = new repairInfo();

        jPanel.add(stu,"stu");
        jPanel.add(absence,"absence");
        jPanel.add(health,"health");
        jPanel.add(repair,"repair");
        jPanel.setBackground(new Color(240,226,233));

        stuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel,"stu");
            }
        });
        absenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel,"absence");
            }
        });
        healthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel,"health");
            }
        });
        repairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(jPanel,"repair");
            }
        });

        alterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        this.add(BorderLayout.CENTER,jPanel);
        this.add(BorderLayout.WEST,btnPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        setResizable(false);
    }

}
