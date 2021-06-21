package Dao;

import java.sql.*;

public class studentDao {

//    public void addStudent(student s) {
//        String sql = "insert into student(Sname,Sno,Sage,Ssex,Sdept,Sbuilding)"
//                + "values(?,?,?,?,?,?)";
//        PreparedStatement ptmt = null;
//        try {
//            ptmt = connection.prepareStatement(sql);
//            ptmt.setString(1, s.name);
//            ptmt.setString(2, s.id);
//            ptmt.setInt(3, s.age);
//            ptmt.setString(4, s.sex);
//            ptmt.setString(5, s.dept);
//            ptmt.setString(6, s.building);
//            ptmt.execute();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//
//    }
//    public void delStudent(student s){
//        String sql = "delete from student where Sno='%s'";
//        PreparedStatement ptmt = null;
//        try {
//            ptmt = connection.prepareStatement(String.format(sql,s.id));
//            ptmt.execute();
//            System.out.println("删除成功");
//        } catch (SQLException throwables) {
//            System.out.println("删除失败");
//            throwables.printStackTrace();
//        }
//    }

    public static void main(String[] args) {


        Connection connection = new DataBaseConnection().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("select * from studentinfo");
            while (res.next()){
                String name = res.getString("姓名");
                String id = res.getString("学号");
                System.out.println(name);
                System.out.println(id);
            }
            if (res == null){
                res.close();
            }
            if (connection == null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
}
