package Student;
import Handler.LoginHandler;
import LoadSkin.LoadSkin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    public static String userName;
    public static String password;
    public static String AdminId;
    public static String StuId;
    JLabel nameLabel = new JLabel("登录",JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名：");
    JLabel pwdLabel = new JLabel("密码：");
    JTextField userTxt = new JTextField();
    JTextField pwdField = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    SystemTray systemTray;
    TrayIcon trayIcon;
    LoginHandler loginHandler;
    JPanel NorthPanel = new JPanel();
    ButtonGroup group;
    JRadioButton jrb1 = new JRadioButton("学生");
    JRadioButton jrb2 = new JRadioButton("宿舍管理员");
    JSplitPane jSplitPane;
    JPanel panelLogin;
    JPanel panelImg;
    JLabel backImg;
    JLabel iconImg;
    static {
        LoadSkin.LoadMySkin();
    }
    public Login(){
        super("桂电宿舍管理系统");
        loginHandler = new LoginHandler(this);
        Container contentPanel = getContentPane();
        nameLabel.setFont( new Font("华文行楷",Font.PLAIN,20));
        Font centerFont = new Font("楷体", Font.PLAIN, 15);
        userNameLabel.setFont(centerFont);
        pwdLabel.setFont(centerFont);
        loginBtn.setFont(centerFont);
        loginBtn.addActionListener(loginHandler);
        resetBtn.setFont(centerFont);
        resetBtn.addActionListener(loginHandler);
        userTxt.setPreferredSize(new Dimension(150,30));
        pwdField.setPreferredSize(new Dimension(150,30));

        iconImg = new JLabel(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\GUET.PNG"));
        setTitle("桂电宿舍管理系统");
        setSize(730,600);


        //选择学生还是宿舍管理员
        group = new ButtonGroup();
        group.add(jrb1);
        group.add(jrb2);

        //把组件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        centerPanel.add(loginBtn);
        centerPanel.add(resetBtn);
        centerPanel.add(jrb1);
        centerPanel.add(jrb2);
        NorthPanel.setLayout(new BorderLayout());
        NorthPanel.add(BorderLayout.CENTER,iconImg);
        NorthPanel.add(BorderLayout.SOUTH,nameLabel);
        NorthPanel.setPreferredSize(new Dimension(330,150));

        //设置按钮图标
        loginBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\登录.png"));
        loginBtn.setToolTipText("登录");

        resetBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\重置.png"));
        resetBtn.setToolTipText("重置");

        //自定义布局
        layoutCenter();

        /*分割面板的两部分
        * 登录面板
        * */
        panelLogin = new JPanel();
        panelLogin.setLayout(new BorderLayout());
        panelImg = new JPanel();
        jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,panelImg,panelLogin);
        jSplitPane.setDividerLocation(400);
        jSplitPane.setDividerSize(2);
        jSplitPane.setEnabled(false);
        panelLogin.add(centerPanel,BorderLayout.CENTER);
        panelLogin.add(NorthPanel,BorderLayout.NORTH);
        contentPanel.add(jSplitPane);

        /*图片面版布局*/
        backImg = new JLabel(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\Pink.jpg"));
        panelImg.add(backImg);
        if (SystemTray.isSupported()){
            systemTray = SystemTray.getSystemTray();
            trayIcon = new TrayIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\resource\\GUET.PNG").getImage());
            //设置托盘图标自动缩放
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    Login.this.dispose();
                }
            });
            //托盘事件监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if (clickCount == 1){
                        Login.this.setExtendedState(JFrame.NORMAL);
                    }
                    Login.this.setVisible(true);
                }
            });
        }

        //自定义图标
        setIconImage(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\resource\\GUET.PNG").getImage());
        //在屏幕中央
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void  layoutCenter() {
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userTxt)),Spring.constant(20));
        int offsetX = childWidth.getValue()/2;

        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userNameLabel);
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,jrb1,20,SpringLayout.SOUTH,pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,jrb1,20,SpringLayout.WEST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,jrb2,0,SpringLayout.NORTH,jrb1);
        springLayout.putConstraint(SpringLayout.WEST,jrb2,50,SpringLayout.EAST,jrb1);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,jrb1);
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,0,SpringLayout.WEST,jrb1);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,50,SpringLayout.EAST,loginBtn);
    }
    public boolean jrb1Select(){
        return jrb1.isSelected();
    }
    public boolean jrb2Select(){
        return jrb2.isSelected();
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JTextField getPwdField() {
        return pwdField;
    }


}
