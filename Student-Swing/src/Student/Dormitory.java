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
    JPanel repair;
    JScrollPane repairScroll;
    JLabel label1;
    JLabel label2;
    JLabel dormType;
    JLabel dormMem;
    JButton alterDorm;
    JTable table;
    CardLayout centerCard;
    JButton basicInfoBtn;
    JButton healthBtn;
    JButton repairBtn;
    JButton addBtn;
    JButton delBtn;

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

        basic = new JPanel();
        health = new JPanel();
        repair = new JPanel();
        repair.setLayout(new BorderLayout());

        label1 = new JLabel("基本信息");
        label2 = new JLabel("卫生情况");

        basic.add(label1);
        health.add(label2);

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
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        //字体居中
        DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,tableCellRenderer);
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
                String repairPeo = resultSet.getString("处理人");
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