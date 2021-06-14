package Student;
import LoadSkin.LoadSkin;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * @author Dyg
 */
public class Absence extends JPanel{
    public JLabel label;
    Vector<String> data = null;
    JTable table;
    JScrollPane scrollPane;
    JPanel northPane;
    static {
        LoadSkin.LoadMySkin();
    }
    public Absence(){
        label = new JLabel("缺勤情况");
        label.setFont(new Font("宋体",Font.BOLD,30));
        table = new JTable();
        table.setEnabled(false);

        setLayout(new BorderLayout());

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addColumn("姓名");
        tableModel.addColumn("学号");
        tableModel.addColumn("晚归日期");
        tableModel.addColumn("晚归时间");
        tableModel.addColumn("晚归原因");
        tableModel.addColumn("记录员");

        DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);


        //设置字居中
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,tableCellRenderer);
        table.setFocusable(false);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.CYAN);

        northPane = new JPanel();
        northPane.add(label);

        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(450,400));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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

//        northPane.add(header);
        scrollPane.setViewportView(table);
        this.add(BorderLayout.NORTH,northPane);
        this.add(BorderLayout.CENTER,scrollPane);
        this.setVisible(true);

    }
}
