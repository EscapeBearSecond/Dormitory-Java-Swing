package Handler;
import Admin.AdminMainHome;
import Dao.DataBaseConnection;
import Student.Home;
import Student.Login;
import Student.MainClass;
import Student.MainHome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginHandler implements ActionListener {
    //连接数据库
    private Login login;
    private MainHome mainHome;
    public  LoginHandler(Login login){
        this.login = login;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if ("重置".equals(text)){
            login.getUserTxt().setText("");
            login.getPwdField().setText("");
        }else if ("登录".equals(text) && login.jrb1Select()){
            try {
                //创建数据库执行状态
                Statement statement = MainClass.connection.getConnection().createStatement();
                /*
                * 查找数据库中的用户名，当用户名与输入框中的一致时
                * 开始匹配密码是否一致
                * 在执行下一次sql语句时要重新创建一个statement
                * */
                ResultSet res = statement.executeQuery("select  * from studentuser");
                while (res.next()){
                    Login.userName = res.getString("userName");
                    Login.StuId = res.getString("id");
                    if (login.getUserTxt().getText().equals(Login.userName)){
                        System.out.println("用户名存在！！");
                        Statement statement1 = MainClass.connection.getConnection().createStatement();
                        String sql = "select password from studentuser where userName = '%s'";
                        ResultSet res1 = statement1.executeQuery(String.format(sql, Login.userName));
                        while (res1.next()){
                            Login.password = res1.getString("password");
                            if (login.getPwdField().getText().equals(Login.password)){
                                Statement statement2 = MainClass.connection.getConnection().createStatement();
//                                ResultSet resultSet = statement2.executeQuery("select 用户名 from studentinfo");
//                                while (resultSet.next()){
//                                    String dataUser = resultSet.getString("用户名");
//                                    if (dataUser.equals(Login.userName)){
//                                        System.out.println("登录成功！！");
//                                        System.out.println("用户名为"+dataUser);
//                                        login.setVisible(false);
//                                        mainHome = new MainHome();
//                                        mainHome.setVisible(true);
//                                        return;
//                                    }
//                                }
                                System.out.println(Login.StuId);
                                login.setVisible(false);
                                mainHome = new MainHome();
                                mainHome.setVisible(true);
                                return;
                            }else {
                                JOptionPane.showMessageDialog(login,"用户名或密码错误");
                                return;
                            }
                        }
                    }

                }
                JOptionPane.showMessageDialog(login,"用户名不存在");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if ("登录".equals(text) && login.jrb2Select()){
            try {
                Statement statement = MainClass.connection.getConnection().createStatement();
                ResultSet res = statement.executeQuery("select  * from adminuser");
                while (res.next()){
                    Login.userName = res.getString("userName");
                    Login.AdminId = res.getString("id");
                    if (login.getUserTxt().getText().equals(Login.userName)){
                        System.out.println("用户名存在！！");
                        Statement statement1 = MainClass.connection.getConnection().createStatement();
                        String sql = "select password from adminuser where userName = '%s'";
                        ResultSet res1 = statement1.executeQuery(String.format(sql, Login.userName));
                        while (res1.next()){
                            Login.password = res1.getString("password");
                            if (login.getPwdField().getText().equals(Login.password)){
                                System.out.println("登录成功");
                                login.dispose();
                                AdminMainHome adminMainHome = new AdminMainHome();
                                return;

                            }else {
                                JOptionPane.showMessageDialog(login,"用户名或密码错误");
                                return;
                            }
                        }
                    }

                }
                JOptionPane.showMessageDialog(login,"用户名不存在");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}
