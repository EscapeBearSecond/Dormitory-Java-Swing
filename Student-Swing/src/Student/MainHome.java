package Student;

import LoadSkin.LoadSkin;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainHome extends JFrame {
    JButton PersonBtn;
    JButton AbsenceBtn ;
    JButton AlterAccountBtn;
    JButton ExitBtn;
    JButton DormitoryBtn;
    JPanel BtnPanel;
    JPanel MainPanel;
    Home home;  //主页面板
    Absence absence;    //缺勤面板
    Dormitory dormitory; //宿舍信息面板
    CardLayout card;
    static {
        LoadSkin.LoadMySkin();
    }

    public MainHome(){
        Container contentPane = getContentPane();
        home = new Home();
        absence = new Absence();
        dormitory = new Dormitory();
        MainPanel  = new JPanel();
        card = new CardLayout(5,5);
        MainPanel.setLayout(card);
        MainPanel.add(home,"home");
        MainPanel.add(absence,"absence");
        MainPanel.add(dormitory,"dormitory");

        /*
        * 设置按钮文字，图标以及提示信息
        *
        * */
        PersonBtn = new JButton("个人主页");
        PersonBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\主页.png"));
        PersonBtn.setToolTipText("个人主页");

        DormitoryBtn = new JButton("宿舍信息");
        DormitoryBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\宿舍.png"));
        DormitoryBtn.setToolTipText("宿舍信息");

        AbsenceBtn = new JButton("缺勤情况");
        AbsenceBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\缺勤.png"));
        AbsenceBtn.setToolTipText("缺勤情况");


        AlterAccountBtn = new JButton("切换账户");
        AlterAccountBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\切换账户.png"));
        AlterAccountBtn.setToolTipText("切换账户");

        ExitBtn = new JButton("退出程序");
        ExitBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\退出.png"));
        ExitBtn.setToolTipText("退出程序");

        //装载按钮的面板btnPanel
        BtnPanel = new JPanel();
        BtnPanel.setPreferredSize(new Dimension(100,PersonBtn.getHeight()*5));
        BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        BtnPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        BtnPanel.add(PersonBtn);
        BtnPanel.add(AbsenceBtn);
        BtnPanel.add(DormitoryBtn);
        BtnPanel.add(AlterAccountBtn);
        BtnPanel.add(ExitBtn);
        this.add(BorderLayout.WEST,BtnPanel);
        this.add(BorderLayout.CENTER,MainPanel);
        this.setSize(600,600);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        /*
        * 给各个按钮添加事件
        * */
        AbsenceBtn.addActionListener(e -> {
            //card布局，显示absence面板
            card.show(MainPanel,"absence");

        });
        PersonBtn.addActionListener(e -> card.show(MainPanel,"home"));
        AlterAccountBtn.addActionListener(e -> {
            dispose();
            Login login = new Login();
        });
        DormitoryBtn.addActionListener(e -> card.show(MainPanel,"dormitory"));
    }

    public Home getHome() {
        return home;
    }

}
