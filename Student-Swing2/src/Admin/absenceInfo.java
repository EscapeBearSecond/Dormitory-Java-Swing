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

public class absenceInfo extends JPanel {
    JPanel northPanel;
    JScrollPane middlePanel;
    JPanel southPanel;
    JTextField searchField;
    JButton searchBtn;
    JButton addBtn;
    JButton delBtn;
    JTable jTable;

    public absenceInfo(){
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
        tableModel.addColumn("学号");
        tableModel.addColumn("晚归日期");
        tableModel.addColumn("晚归时间");
        tableModel.addColumn("晚归原因");
        tableModel.addColumn("管理员编号");

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);


        //设置字居中
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,tableCellRenderer);
        jTable.setFocusable(false);

        try {
            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select * from absence";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Vector<String> data = new Vector<>();
                data.add(resultSet.getString("学号"));
                data.add(resultSet.getString("晚归日期"));
                data.add(resultSet.getString("晚归时间"));
                data.add(resultSet.getString("晚归原因"));
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
                System.out.println(Login.AdminId);
                    String id = JOptionPane.showInputDialog("学号");
                    if (id != null){
                        String day = JOptionPane.showInputDialog("晚归日期");
                        if (day != null){
                            String time = JOptionPane.showInputDialog("晚归时间");
                            if (time != null){
                                String reason = JOptionPane.showInputDialog("晚归原因");
                                if (reason != null){
                                        Vector<String> data = new Vector<>();
                                        data.add(id);
                                        data.add(day);
                                        data.add(time);
                                        data.add(reason);
                                        data.add(Login.AdminId);
                                        tableModel.addRow(data);

                                        Connection connection = MainClass.connection.getConnection();
                                        String sql = "insert into absence values(?,?,?,?,?)";
                                        try {
                                            PreparedStatement statement = connection.prepareStatement(sql);
                                            statement.setString(1,day);
                                            statement.setString(2,time);
                                            statement.setString(3,reason);
                                            statement.setString(4,Login.AdminId);
                                            statement.setString(5,id);
                                            statement.execute();
                                        } catch (SQLException throwables) {
                                            throwables.printStackTrace();
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
                    String getDay = jTable.getValueAt(selectedRow,1).toString();
                    String getTime = jTable.getValueAt(selectedRow,2).toString();
                    String getReason = jTable.getValueAt(selectedRow,3).toString();
                    String getId = jTable.getValueAt(selectedRow,0).toString();
                    tableModel.removeRow(selectedRow);

                    String sql = "delete from absence where 晚归日期=? and 晚归时间=? and 晚归原因=? and 管理员编号=? and 学号=? ";
                    Connection connection = MainClass.connection.getConnection();
                    try {
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1,getDay);
                        statement.setString(2,getTime);
                        statement.setString(3,getReason);
                        statement.setString(4, Login.AdminId);
                        statement.setString(5,getId);
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
