package Admin;

import Student.Login;
import Student.MainClass;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class healthInfo extends JPanel {
    JPanel northPanel;
    JScrollPane middlePanel;
    JPanel southPanel;
    JTextField searchField;
    JButton searchBtn;
    JButton addBtn;
    JButton delBtn;
    JTable jTable;
    public healthInfo(){
        northPanel = new JPanel();
        searchBtn = new JButton("搜索");
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150,30));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(searchField);
        northPanel.add(searchBtn);
        northPanel.setBackground(new Color(240,226,233));

        middlePanel = new JScrollPane();
        middlePanel.setPreferredSize(new Dimension(450,400));
        middlePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        middlePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jTable = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.addColumn("宿舍编号");
        tableModel.addColumn("检查日期");
        tableModel.addColumn("检查得分");
        tableModel.addColumn("扣分项");
        tableModel.addColumn("检察员编号");

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);


        //设置字居中
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,tableCellRenderer);
        jTable.setFocusable(false);

        try {
            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select * from healthinfo";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Vector<String> data = new Vector<>();
                data.add(resultSet.getString("宿舍编号"));
                data.add(resultSet.getString("检查日期"));
                data.add(resultSet.getString("检查得分"));
                data.add(resultSet.getString("扣分项"));
                data.add(resultSet.getString("管理员编号"));
                tableModel.addRow(data);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        middlePanel.setViewportView(jTable);

        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        addBtn = new JButton("添加记录");
        delBtn = new JButton("删除记录");
        southPanel.add(addBtn);
        southPanel.add(delBtn);
        southPanel.setBackground(new Color(240,226,233));

        add(BorderLayout.NORTH,northPanel);
        add(BorderLayout.CENTER,middlePanel);
        add(BorderLayout.SOUTH,southPanel);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dormid = JOptionPane.showInputDialog("宿舍编号");
                if (dormid != null && dormid != ""){
                    System.out.println(dormid);
                    String time = JOptionPane.showInputDialog("检查日期");
                    if (time != null){
                        String score = JOptionPane.showInputDialog("检查得分");
                        if (score != null){
                            String delscore = JOptionPane.showInputDialog("扣分项");
                            if (delscore != null){
                                String recorder = JOptionPane.showInputDialog("检察员编号");
                                if (recorder != null){
                                        Vector<String> data = new Vector<>();
                                        data.add(dormid);
                                        data.add(time);
                                        data.add(score);
                                        data.add(delscore);
                                        data.add(recorder);
                                        tableModel.addRow(data);
                                        Connection connection = MainClass.connection.getConnection();
                                        String sql = "insert into healthinfo values(?,?,?,?,?)";
                                        try {
                                            PreparedStatement statement = connection.prepareStatement(sql);
                                            statement.setString(1,dormid);
                                            statement.setString(2,time);
                                            statement.setString(3,score);
                                            statement.setString(4,delscore);
                                            statement.setString(5,recorder);
                                            statement.execute();
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
                                        }
                                }
                            }
                        }
                    }
                }
            }
        });

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(middlePanel,"请选择要删除的记录");
                }else {
                    String getId = jTable.getValueAt(selectedRow,0).toString();
                    String getTime = jTable.getValueAt(selectedRow,1).toString();
                    String getScore = jTable.getValueAt(selectedRow,2).toString();
                    String getDelScore = jTable.getValueAt(selectedRow,3).toString();
                    tableModel.removeRow(selectedRow);

                    String sql = "delete from healthinfo where 宿舍编号=? and 检查日期=? and 检查得分=? and 扣分项=? and 管理员编号=?";
                    Connection connection = MainClass.connection.getConnection();
                    try {
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1,getId);
                        statement.setString(2,getTime);
                        statement.setString(3,getScore);
                        statement.setString(4,getDelScore);
                        statement.setString(5, Login.AdminId);
                        int i = statement.executeUpdate();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        jTable.setRowSorter(sorter);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchField.getText();
                sorter.setRowFilter(RowFilter.regexFilter(text));
            }
        });
        setBackground(new Color(240,226,233));
    }
}
