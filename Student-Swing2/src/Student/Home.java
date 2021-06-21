package Student;

import LoadSkin.LoadSkin;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Home extends JPanel {
    JLabel nameLabel;
    JLabel idLabel;
    JLabel sexLabel;
    JLabel ageLabel;
    JLabel nativeLabel;
    JLabel birthLabel;
    JLabel UIDLabel;
    JLabel PhoneLabel;
    JLabel dormitoryLabel;
    JLabel lateLabel;
    JLabel facultyLabel;
    JLabel HomeName;
    JLabel userName;
    JButton Modify;
    JButton Save;
    static JTextField nameField;
    static JTextField sexField;
    static JTextField ageField;
    static JComboBox<String> nativeChoose;
    static JTextField idField;
    static JTextField birthField;
    static JTextField UIDField;
    static JTextField PhoneField;
    static JTextField dormitoryField;
    static JTextField lateField;
    static JTextField facultyField;
    static JTextField userNameField;
    static {
        LoadSkin.LoadMySkin();
    }
    public Home(){

        //信息标签相关
        HomeName = new JLabel("个人信息");
        HomeName.setFont(new Font("宋体",Font.BOLD,30));

        Modify = new JButton("修改信息");
        Save = new JButton("保存信息");
        Modify.setSize(100,20);
        Save.setSize(100,20);
        Modify.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\修改.png"));
        Save.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\保存.png"));

        //标签

        nameLabel = new JLabel("姓名：");
        idLabel = new JLabel("学号：");
        sexLabel = new JLabel("性别：");
        ageLabel = new JLabel("年龄：");
        nativeLabel = new JLabel("名族：");
        birthLabel = new JLabel("生日：");
        UIDLabel = new JLabel("身份证：");
        PhoneLabel = new JLabel("联系电话：");
        dormitoryLabel = new JLabel("宿舍：");
        lateLabel = new JLabel("晚归：");
        facultyLabel = new JLabel("院系：");

        //标签的对应编辑框
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100,25));
        nameField.setEditable(false);
        sexField = new JTextField();
        sexField.setPreferredSize(new Dimension(100,25));
        sexField.setEditable(false);
        ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(100,25));
        ageField.setEditable(false);
        nativeChoose = new JComboBox<>();
        nativeChoose.setSize(90,25);
        nativeChoose.addItem("选择名族");
        nativeChoose.addItem("汉族");
        nativeChoose.addItem("壮族");
        nativeChoose.addItem("彝族");
        nativeChoose.addItem("满族");
        nativeChoose.addItem("朝鲜族");
        nativeChoose.addItem("回族");
        nativeChoose.addItem("蒙古族");
        nativeChoose.setEnabled(false);

        idField = new JTextField();
        idField.setPreferredSize(new Dimension(100,25));
        idField.setEditable(false);
        birthField = new JTextField();
        birthField.setPreferredSize(new Dimension(100,25));
        birthField.setEditable(false);
        UIDField = new JTextField();
        UIDField.setPreferredSize(new Dimension(100,25));
        UIDField.setEditable(false);
        PhoneField = new JTextField();
        PhoneField.setPreferredSize(new Dimension(100,25));
        PhoneField.setEditable(false);
        dormitoryField = new JTextField();
        dormitoryField.setPreferredSize(new Dimension(100,25));
        dormitoryField.setEditable(false);
        lateField = new JTextField();
        lateField.setPreferredSize(new Dimension(100,25));
        lateField.setEditable(false);
        facultyField = new JTextField();
        facultyField.setPreferredSize(new Dimension(300,25));
        facultyField.setEditable(false);
        userName = new JLabel("用户名：");
        userNameField = new JTextField();
        userNameField.setPreferredSize(new Dimension(100,25));
        userNameField.setEditable(false);

        JPanel panel = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();
        JPanel p8 = new JPanel();
        JPanel p9 = new JPanel();
        p1.setBackground(new Color(240,226,233));
        p2.setBackground(new Color(240,226,233));
        p3.setBackground(new Color(240,226,233));
        p4.setBackground(new Color(240,226,233));
        p5.setBackground(new Color(240,226,233));
        p6.setBackground(new Color(240,226,233));
        p7.setBackground(new Color(240,226,233));
        p8.setBackground(new Color(240,226,233));
        p9.setBackground(new Color(240,226,233));
        p1.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p2.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p3.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p4.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p5.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p5.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p6.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p9.setLayout(new FlowLayout(FlowLayout.LEFT,20,0));
        p8.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
        panel.setLayout(new GridLayout(9,1,0,10));
        panel.setBackground(new Color(240,226,233));
//        将信息标签添加到panel上

        p1.add(nameLabel);
        p1.add(nameField);
        p1.add(sexLabel);
        p1.add(sexField);

        p2.add(ageLabel);
        p2.add(ageField);
        p2.add(idLabel);
        p2.add(idField);

        p3.add(nativeLabel);
        p3.add(nativeChoose);
        p3.add(UIDLabel);
        p3.add(UIDField);

        p4.add(birthLabel);
        p4.add(birthField);
        p4.add(PhoneLabel);
        p4.add(PhoneField);

        p5.add(dormitoryLabel);
        p5.add(dormitoryField);
        p5.add(lateLabel);
        p5.add(lateField);

        p6.add(facultyLabel);
        p6.add(facultyField);

        p7.add(HomeName);

        p8.add(Modify);
        p8.add(Save);

        panel.add(p7);
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);
        panel.add(p6);
        panel.add(p9);
        panel.add(p8);
        this.add(panel);
        this.setSize(600,600);
        setVisible(true);
        setBackground(new Color(240,226,233));


        /*
        初始化时，GUI界面上显示的信息是从数据库中读取的信息
        * */
        try {
            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select * from studentinfo where 学号 = '%s'";
            ResultSet resultSet = statement.executeQuery(String.format(sql, Login.StuId));
            while (resultSet.next()){
                String Sname = resultSet.getString("姓名");
                int Sage = resultSet.getInt("年龄");
                String Ssex = resultSet.getString("性别");
                String id = resultSet.getString("学号");
                String Snative = resultSet.getString("名族");
                String Sbirth = resultSet.getString("出生日期");
                String SUID = resultSet.getString("身份证");
                String Sdormitory = resultSet.getString("宿舍编号");
                String Sfaculty = resultSet.getString("院系");
                String Sadd = resultSet.getString("联系电话");
                nameField.setText(Sname);
                ageField.setText(String.valueOf(Sage));
                sexField.setText(Ssex);
                idField.setText(id);
                UIDField.setText(SUID);
                nativeChoose.setSelectedItem( Snative);
                birthField.setText(Sbirth);
                facultyField.setText(Sfaculty);
                PhoneField.setText(Sadd);
                dormitoryField.setText(Sdormitory);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /*
        * 修改和保存按钮对应的事件
        * */
        Modify.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"请修改信息");
            nameField.setEditable(true);
            ageField.setEditable(true);
            sexField.setEditable(true);
            UIDField.setEditable(true);
            nativeChoose.setEnabled(true);
            birthField.setEditable(true);
            facultyField.setEditable(true);
            lateField.setEditable(true);
            PhoneField.setEditable(true);
            dormitoryField.setEditable(true);

        });
        Save.addActionListener(e -> {
            /*
            * 设置home面板中对应的文本编辑框的内容
            * */
            nameField.setText(nameField.getText());
            ageField.setText(ageField.getText());
            sexField.setText(sexField.getText());
            idField.setText(idField.getText());
            UIDField.setText(UIDField.getText());
            nativeChoose.setSelectedItem(nativeChoose.getSelectedItem());
            birthField.setText(birthField.getText());
            facultyField.setText(facultyField.getText());
            lateField.setText(lateField.getText());
            PhoneField.setText(PhoneField.getText());
            dormitoryField.setText(dormitoryField.getText());

            nameField.setEditable(false);
            ageField.setEditable(false);
            sexField.setEditable(false);
//            idField.setEditable(false);
            UIDField.setEditable(false);
            nativeChoose.setEnabled(false);
            birthField.setEditable(false);
            facultyField.setEditable(false);
            lateField.setEditable(false);
            PhoneField.setEditable(false);
            dormitoryField.setEditable(false);
            /*
            * 保存的同时要修改数据库中的内容
            * */
            try {
                Statement statement = MainClass.connection.getConnection().createStatement();
                Statement statement2 = MainClass.connection.getConnection().createStatement();
                String name = nameField.getText();
                int age =  Integer.valueOf(ageField.getText()) ;
                String sex = sexField.getText();
                String studentId = idField.getText();
                String studentUID = UIDField.getText();
                String studentNative = (String) nativeChoose.getSelectedItem();
                String studentBirth = birthField.getText();
                String studentFaculty = facultyField.getText();
                String studentPhone = PhoneField.getText();
                String studentDor = dormitoryField.getText();

                String sql = "update studentinfo set 姓名 = '%s',学号='%s',性别='%s',年龄='%d',名族='%s',出生日期='%s'" +
                        ",身份证='%s',联系电话='%s',宿舍编号='%s',院系='%s' where 学号=%s";
                statement.executeUpdate(String.format(sql,name,studentId,sex,age,studentNative,studentBirth,studentUID,studentPhone,
                        studentDor,studentFaculty,Login.StuId));
//                String sql1 = "update absence set 姓名 = '%s' where 学号 = '%s'";
//                statement2.executeUpdate(String.format(sql1,name,Login.userName));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"保存成功");

        });

    }

    public static JTextField getDormitoryField() {
        return dormitoryField;
    }

    public static JTextField getIdField(){
        return idField;
    }
}
