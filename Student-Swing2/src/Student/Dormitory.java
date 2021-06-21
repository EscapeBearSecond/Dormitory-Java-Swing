package Student;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

class Dormitory extends JPanel {
    JPanel northPanel;
    JPanel centerPanel;
    JPanel basic;
    JPanel health;
    JScrollPane healthScroll;
    JPanel repair;
    JScrollPane repairScroll;
    JLabel dormType;
    JLabel dormID;
    JLabel dormManager;
    JTextField managerField;
    JTextField typeField;
    JTextField idField;
    JTable table;
    CardLayout centerCard;
    JButton basicInfoBtn;
    JButton healthBtn;
    JButton repairBtn;
    JButton addBtn;
    JButton delBtn;
    JTable memTable; /*宿舍成员表格*/
    JTable hTable;

    public Dormitory(){
        /*按钮的提示信息和图标*/
        basicInfoBtn = new JButton("基本信息");
        basicInfoBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\基本.png"));
        basicInfoBtn.setToolTipText("基本信息");
        healthBtn = new JButton("宿舍卫生信息");
        healthBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\卫生.png"));
        healthBtn.setToolTipText("宿舍卫生信息");
        repairBtn = new JButton("维修记录");
        repairBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\维修.png"));
        repairBtn.setToolTipText("维修记录");

        /*将按钮添加到面板并布局在主面板北部*/
        northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(500,50));
        northPanel.add(basicInfoBtn);
        northPanel.add(healthBtn);
        northPanel.add(repairBtn);
        northPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        //basic 面板
        basic = new JPanel();
        basic.setLayout(new BorderLayout());
        JPanel bNorthPanel = new JPanel();
        bNorthPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bNorthPanel.setBackground(new Color(240,226,233));
        dormType = new JLabel("宿舍类型：");
        dormID = new JLabel("宿舍编号：");
        dormManager = new JLabel("宿舍长");
        typeField = new JTextField();
        typeField.setPreferredSize(new Dimension(80,20));
        typeField.setEditable(false);
        idField = new JTextField();
        idField.setEditable(false);
        idField.setPreferredSize(new Dimension(80,20));
        managerField = new JTextField();
        managerField.setEditable(false);
        managerField.setPreferredSize(new Dimension(80,20));



        bNorthPanel.add(dormType);
        bNorthPanel.add(typeField);
        bNorthPanel.add(dormID);
        bNorthPanel.add(idField);
        bNorthPanel.add(dormManager);
        bNorthPanel.add(managerField);
        JScrollPane bCenterPanel = new JScrollPane();
        memTable = new JTable();
        DefaultTableModel  tableModel2 = (DefaultTableModel) memTable.getModel();
        //表头字体剧中
        DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer) memTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        //表格字体居中
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        memTable.setDefaultRenderer(Object.class,tableCellRenderer);
        memTable.setFocusable(false);
        tableModel2.addColumn("姓名");
        tableModel2.addColumn("学号");
        tableModel2.addColumn("年龄");
        tableModel2.addColumn("联系电话");

        try {
            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select * from dormitoryinfo where 宿舍编号 = '%s'";
            ResultSet resultSet = statement.executeQuery(String.format(sql, Home.getDormitoryField().getText()));
            while (resultSet.next()){
                Vector<String> data = new Vector<>();

                data.add(resultSet.getString("宿舍类型"));
                data.add(resultSet.getString("宿舍编号"));
                data.add(resultSet.getString("宿舍长"));
                idField.setText(data.elementAt(1));
                typeField.setText(data.elementAt(0));
                managerField.setText(data.elementAt(2));
            }
            Statement statement1 = MainClass.connection.getConnection().createStatement();
            String sql1 = "select * from studentinfo where 宿舍编号='%s'";
            ResultSet resultSet1 = statement1.executeQuery(String.format(sql1,Home.getDormitoryField().getText()));
            while (resultSet1.next()){
                Vector<String> data = new Vector<>();
                data.add(resultSet1.getString("姓名"));
                data.add(resultSet1.getString("学号"));
                data.add(resultSet1.getString("年龄"));
                data.add(resultSet1.getString("联系电话"));
                tableModel2.addRow(data);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        bCenterPanel.setPreferredSize(new Dimension(400,300));
        bCenterPanel.setViewportView(memTable);
        basic.add(BorderLayout.NORTH,bNorthPanel);
        basic.add(BorderLayout.CENTER,bCenterPanel);
        basic.setBackground(new Color(240,226,233));

        /*宿舍卫生情况*/
        health = new JPanel();
        healthScroll = new JScrollPane();
        hTable = new JTable();

        DefaultTableModel tableModel3 = (DefaultTableModel) hTable.getModel();
        DefaultTableCellRenderer  renderer2 = (DefaultTableCellRenderer) hTable.getTableHeader().getDefaultRenderer();

        DefaultTableCellRenderer tableCellRenderer2 = new DefaultTableCellRenderer();
        tableCellRenderer2.setHorizontalAlignment(JLabel.CENTER);
        hTable.setDefaultRenderer(Object.class,tableCellRenderer);
        hTable.setFocusable(false);
        renderer2.setHorizontalAlignment(SwingConstants.CENTER);


        tableModel3.addColumn("宿舍编号");
        tableModel3.addColumn("检查得分");
        tableModel3.addColumn("扣分项");
        tableModel3.addColumn("检查日期");
        tableModel3.addColumn("检察员编号");

        try {
            Statement statement2 = MainClass.connection.getConnection().createStatement();
            String sql = "select * from healthinfo where 宿舍编号='%s'";
            ResultSet resultSet = statement2.executeQuery(String.format(sql, Home.getDormitoryField().getText()));
            while (resultSet.next()){
                Vector<String> data = new Vector<>();
                data.add(resultSet.getString("宿舍编号"));
                data.add(resultSet.getString("检查得分"));
                data.add(resultSet.getString("扣分项"));
                data.add(resultSet.getString("检查日期"));
                data.add(resultSet.getString("管理员编号"));
                tableModel3.addRow(data);
            }
            if (statement2 == null){
                statement2.close();
            }
            if (resultSet == null){
                resultSet.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        health.setLayout(new BorderLayout());
        healthScroll.setViewportView(hTable);
        healthScroll.setPreferredSize(new Dimension(450,400));
        health.add(BorderLayout.CENTER,healthScroll);


        repair = new JPanel();
        repair.setLayout(new BorderLayout());



        centerCard = new CardLayout(5,5);
        centerPanel = new JPanel();
        centerPanel.setLayout(centerCard);
        centerPanel.add(basic,"basic");
        centerPanel.add(repair,"repair");
        centerPanel.add(health,"health");


        table = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addColumn("宿舍编号");
        tableModel.addColumn("维修物件");
        tableModel.addColumn("维修状态");
        tableModel.addColumn("维修时间");
        tableModel.addColumn("处理人");

        //表头居中
        DefaultTableCellRenderer renderer3 = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer3.setHorizontalAlignment(SwingConstants.CENTER);

        //字体居中
        DefaultTableCellRenderer tableCellRenderer3 = new DefaultTableCellRenderer();
        tableCellRenderer3.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,tableCellRenderer2);
        table.setFocusable(false);


        try {

            Statement statement = MainClass.connection.getConnection().createStatement();
            String sql = "select * from repairinfo where 宿舍编号='%s' ";
            ResultSet resultSet = statement.executeQuery(String.format(sql, Home.getDormitoryField().getText()));
            while (resultSet.next()){
                Vector<String> data = new Vector<>();
                data.add(resultSet.getString("宿舍编号"));
                data.add(resultSet.getString("维修物件"));
                String isRepair = resultSet.getString("维修状态");
                String repairTime = resultSet.getString("维修时间");
                String repairPeo = resultSet.getString("管理员编号");
                data.add(isRepair);
                data.add(repairTime);
                data.add(repairPeo);
                tableModel.addRow(data);
            }
            /*关闭资源*/
            if (resultSet == null){
                resultSet.close();
            }
            if (statement == null){
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        repairScroll = new JScrollPane();
        repairScroll.setPreferredSize(new Dimension(450,400));
        repairScroll.setViewportView(table);

        repair.add(BorderLayout.CENTER,repairScroll);

        /*增加记录和删除记录按钮*/
        JPanel southPane = new JPanel();
        southPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
        addBtn = new JButton("我要报修");
        addBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\增加.png"));
        addBtn.setToolTipText("报修");
        delBtn = new JButton("撤销报修");
        delBtn.setIcon(new ImageIcon("D:\\WorkSpace2\\Java\\Student-Swing\\src\\resource\\撤销.png"));
        delBtn.setToolTipText("撤销");

        southPane.add(addBtn);
        southPane.add(delBtn);
        repair.add(BorderLayout.SOUTH,southPane);


        this.add(BorderLayout.NORTH,northPanel);
        this.add(BorderLayout.CENTER,centerPanel);
        this.setVisible(true);

        basicInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerCard.show(centerPanel,"basic");
            }
        });
        healthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerCard.show(centerPanel,"health");
            }
        });
        repairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerCard.show(centerPanel,"repair");
            }
        });
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row == -1){
                    JOptionPane.showMessageDialog(repair,"请选择要删除的记录");
                }else {
                    String  s0 = (String) table.getValueAt(row, 0);
                    String  s1 = (String) table.getValueAt(row, 1);
                    String  s2 = (String) table.getValueAt(row, 2);
                    String  s3 = (String) table.getValueAt(row, 3);
                    String  s4 = (String) table.getValueAt(row, 4);
                    tableModel.removeRow(row);
                    try {
                        Statement statement = MainClass.connection.getConnection().createStatement();
                        String sql = "delete from repairinfo where 宿舍编号='%s' and 维修物件='%s' and 维修状态='%s' and 维修时间='%s' and 处理人='%s'";
                        statement.execute(String.format(sql, s0, s1, s2, s3, s4));


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(repair,"删除成功");
                }
            }
        });
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dormId = JOptionPane.showInputDialog("请输入宿舍编号");
                if (dormId != null){
                    String detail = JOptionPane.showInputDialog("请输入报修信息");
                    if (detail != null){
                        try {
                            Vector<String> dataAdd = new Vector<>();
                            dataAdd.add(dormId);
                            dataAdd.add(detail);
                            dataAdd.add("未完成");
                            dataAdd.add("----");
                            dataAdd.add("----");
                            tableModel.addRow(dataAdd);
                            Connection connection = MainClass.connection.getConnection();
                            String sql = "insert into repairinfo values (?,?,'未完成','----','----')";
                            PreparedStatement statement = connection.prepareStatement(sql);
                            statement.setString(1,dormId);
                            statement.setString(2,detail);
                            int counts = statement.executeUpdate();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                }
            }
        });
    }
}