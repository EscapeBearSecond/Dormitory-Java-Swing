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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class repairInfo extends JPanel {
    JPanel northPanel;
    JScrollPane middlePanel;
    JPanel southPanel;
    JTextField searchField;
    JButton searchBtn;
    JButton addBtn;
    JButton delBtn;
    JTable jTable;
    public repairInfo(){
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
        tableModel.addColumn("维修物件");
        tableModel.addColumn("维修状态");
        tableModel.addColumn("维修时间");
        tableModel.addColumn("维修员编号");

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);


        //设置字居中
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,tableCellRenderer);
        jTable.setFocusable(false);

        try {
            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select * from repairinfo";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Vector<String> data = new Vector<>();
                data.add(resultSet.getString("宿舍编号"));
                data.add(resultSet.getString("维修物件"));
                data.add(resultSet.getString("维修状态"));
                data.add(resultSet.getString("维修时间"));
                data.add(resultSet.getString("管理员编号"));
                tableModel.addRow(data);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        middlePanel.setViewportView(jTable);

        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        addBtn = new JButton("维修");
        southPanel.add(addBtn);
        southPanel.setBackground(new Color(240,226,233));
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable.getSelectedRow();
                if (selectedRow == -1){
                    JOptionPane.showMessageDialog(jTable,"请选择一条未完成的维修记录");
                }else {
                    String status = jTable.getValueAt(selectedRow, 2).toString();
                    if (status.equals("已完成")){
                        JOptionPane.showMessageDialog(jTable,"该维修已完成");
                    }else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Calendar calendar = Calendar.getInstance();
                        Date date =  calendar.getTime();
                        String dateStr = sdf.format(date);
                        String people = Login.AdminId;
                        jTable.setValueAt("已完成",selectedRow,2);
                        jTable.setValueAt(dateStr,selectedRow,3);
                        jTable.setValueAt(people,selectedRow,4);
                        try {
                            Statement statement = MainClass.connection.getConnection().createStatement();
                            String sql = "update repairinfo set 维修状态='%s',维修时间 = '%s',管理员编号 = '%s' where 宿舍编号 = '%s' and 维修物件 = '%s'";
                            int i = statement.executeUpdate(String.format(sql, "已完成", dateStr, Login.AdminId, jTable.getValueAt(selectedRow, 0).toString(), jTable.getValueAt(selectedRow, 1)));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }
        });
        add(BorderLayout.NORTH,northPanel);
        add(BorderLayout.CENTER,middlePanel);
        add(BorderLayout.SOUTH,southPanel);

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
