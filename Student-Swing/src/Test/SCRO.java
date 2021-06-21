package Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class SCRO extends JFrame {
    JScrollPane jScrollPane;
    JTable table;
    JPanel northPane;
    public SCRO(){
        JLabel label = new JLabel("滚动条面板");
        jScrollPane = new JScrollPane();
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        table = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addColumn("姓名");
        tableModel.addColumn("年龄");
        tableModel.addColumn("性别");
        tableModel.addColumn("出生日期");
        JTableHeader header = table.getTableHeader();
        northPane = new JPanel();
        northPane.add(header);
        this.add(BorderLayout.CENTER,jScrollPane);
        this.add(BorderLayout.NORTH,northPane);
        this.setSize(600,600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new  SCRO();
    }
}
