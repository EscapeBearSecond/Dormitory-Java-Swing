package Test;

import LoadSkin.LoadSkin;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class test3 extends JFrame {
    JButton btn1;
    JButton btn2;
    JLabel label;
    JTable table;
    static {
        LoadSkin.LoadMySkin();
    }

    public test3(){
       table = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.addColumn("姓名");
        tableModel.addColumn("年龄");
        for (int i = 0;i < 5;i++){
            Vector<String> data = new Vector<>();
            data.add("张三"+i);
            data.add("18"+i);
            tableModel.addRow(data);
        }
        JTableHeader tableHeader = table.getTableHeader();
        add(tableHeader);
        add(table);

        String s1 = (String) tableModel.getValueAt(0, 0);
        String s2 = (String) tableModel.getValueAt(0, 1);
        System.out.println(s1);
        System.out.println(s2);

        setSize(600,600);
        setVisible(true);



    }

    public static void main(String[] args) {
        new test3();
    }
}