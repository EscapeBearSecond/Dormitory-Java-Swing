package Admin;
import LoadSkin.LoadSkin;
import Student.Login;
import Student.MainClass;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class stuInfo extends JPanel {
    JPanel northPanel;
    JScrollPane middlePanel;
    JPanel southPanel;
    JTextField searchField;
    JButton searchBtn;
    JButton addBtn;
    JButton delBtn;
    JTable jTable;

    public stuInfo(){
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        searchField = new JTextField("学号");
        searchField.setPreferredSize(new Dimension(150,30));
        searchBtn = new JButton("搜索");

        setLayout(new BorderLayout());
        northPanel.add(searchField);
        northPanel.add(searchBtn);
        northPanel.setBackground(new Color(240,226,233));
        add(BorderLayout.NORTH,northPanel);

        middlePanel = new JScrollPane();
        middlePanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        middlePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        middlePanel.setPreferredSize(new Dimension(450,400));
        jTable = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.addColumn("姓名");
        tableModel.addColumn("学号");
        tableModel.addColumn("宿舍编号");
        tableModel.addColumn("院系");

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);


        //设置字居中
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.setDefaultRenderer(Object.class,tableCellRenderer);
        jTable.setFocusable(false);

        middlePanel.setViewportView(jTable);
        add(BorderLayout.CENTER,middlePanel);

        southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        addBtn = new JButton("办理入住");
        delBtn = new JButton("办理离舍");
        southPanel.add(addBtn);
        southPanel.add(delBtn);
        southPanel.setBackground(new Color(240,226,233));
        add(BorderLayout.SOUTH,southPanel);

        try {
            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select 姓名,学号,宿舍编号,院系 from studentinfo where 宿舍编号  ";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Vector<String> data = new Vector<>();
                data.add(resultSet.getString("姓名"));
                data.add(resultSet.getString("学号"));
                data.add(resultSet.getString("宿舍编号"));
                data.add(resultSet.getString("院系"));
                tableModel.addRow(data);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("学号");
                System.out.println(id);
                if (id == ""){
                    JOptionPane.showMessageDialog(middlePanel,"学号输入有误!");
                    return;
                }else if (id!="" && id != null){
                    try {
                        Statement statement = MainClass.connection.getConnection().createStatement();
                        String sql1 = "select 学号 from studentinfo";
                        ResultSet resultSet1 = statement.executeQuery(sql1);
                        while (resultSet1.next()){
                            String stuid= resultSet1.getString("学号");
                            if (id.equals(stuid)){
                                String  sql = "select * from studentinfo where 学号='%s'";
                                ResultSet resultSet = statement.executeQuery(String.format(sql, id));
                                while (resultSet.next()){
                                    String dorm = resultSet.getString("宿舍编号");
                                    System.out.println(dorm);
                                    if (dorm != null ){
                                        JOptionPane.showMessageDialog(middlePanel,"该学生已经办理过入住!");
                                        return;
                                    }else {
                                        String dorm1 = JOptionPane.showInputDialog("宿舍编号");
                                        Statement statement1 = MainClass.connection.getConnection().createStatement();
                                        String sql2 = "update studentinfo set 宿舍编号 = '%s' where 学号 = '%s'";
                                        statement1.executeUpdate(String.format(sql2,dorm1,id));
                                        JOptionPane.showMessageDialog(middlePanel,"办理成功!");
                                        Vector<String> data1 = new Vector<>();
                                        data1.add(resultSet.getString("姓名"));
                                        data1.add(id);
                                        data1.add(dorm1);
                                        data1.add(resultSet.getString("院系"));
                                        tableModel.addRow(data1);
                                        return;
                                    }
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(middlePanel,"没有该学生!");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jTable.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(middlePanel,"请选择需要办理离舍的学生!");
                }
                else{

                    String getid= jTable.getValueAt(selectedRow, 1).toString();
                    Statement statement = null;
                    try {
                        statement = MainClass.connection.getConnection().createStatement();
                        String  sql = "update studentinfo set 宿舍编号=null where 学号='%s'";
                        statement.executeUpdate(String.format(sql, getid));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(middlePanel,"离舍成功!");
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
