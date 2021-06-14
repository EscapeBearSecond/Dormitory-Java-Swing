package Student;
import LoadSkin.LoadSkin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Dyg
 */
public class Absence extends JPanel{
    public JLabel label;
    Vector<String> data = null;
    JTable table;
    static {
        LoadSkin.LoadMySkin();
    }
    public Absence(){
        label = new JLabel("缺勤情况");
        label.setFont(new Font("宋体",Font.BOLD,30));
        table = new JTable();
        table.setEnabled(false);
        table.setBackground(Color.PINK);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addColumn("姓名");
        tableModel.addColumn("学号");
        tableModel.addColumn("晚归日期");
        tableModel.addColumn("晚归时间");
        tableModel.addColumn("晚归原因");
        tableModel.addColumn("记录员");
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.CYAN);
        this.add(label);
        this.add(header);


        try {
            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select * from absence where 用户名='%s'";
            ResultSet resultSet = statement.executeQuery(String.format(sql, Login.userName));
            while (resultSet.next()){
                data = new Vector<>();
                data.add(resultSet.getString("姓名"));
                data.add(resultSet.getString("学号"));
                data.add(resultSet.getString("晚归日期"));
                data.add(resultSet.getString("晚归时间"));
                data.add(resultSet.getString("晚归原因"));
                data.add(resultSet.getString("记录员"));
                tableModel.addRow(data);
                data = null;
            }
            while (resultSet == null){
                resultSet.close();
            }
            while (statement == null){
                statement.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        this.add(table);
        this.setVisible(true);

    }
}
