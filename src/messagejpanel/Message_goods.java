package messagejpanel;

import company.Test;
import company.Testmysql;
import jframe.Main;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class Message_goods extends JPanel {
    private JTextField textField;
    private JTextField textField2;
    private JTextField search_textField1;
    private JTextField search_textField2;
    private JTable table;

    DefaultTableModel tableModel;
    Vector<Vector<String>> tableValueV;
    Vector<String> columnNameV;

    /**
     * Create the panel.
     */
    public Message_goods() {
        setBackground(Color.WHITE);


        setBounds(197, 0, 637, 570);
        setLayout(null);

        String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

        String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

        try {
            Class.forName(JDriver);
        } catch (ClassNotFoundException cnf_e) {  // ����Ҳ���������
            System.out.println("Driver Not Found: " + cnf_e);
        }
	
		/*JTextArea txtOut=new JTextArea();
		txtOut.setBackground(new Color(255, 255, 255));
		txtOut.setLineWrap(true);
		//txtOut.setPreferredSize(new Dimension(x*19+30,y*3-25));//ȥ����һ��
		JScrollPane scrollPaneOut=new JScrollPane(txtOut);
		scrollPaneOut.setSize(438, 250);
		scrollPaneOut.setLocation(110, 90);
		//scrollPaneOut.setPreferredSize(new Dimension(100,50));//�����һ��
		add(scrollPaneOut);
		*/

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(113, 391, 450, 128);
        add(panel);
        panel.setLayout(null);
        panel.setVisible(false);
        panel.setBorder(BorderFactory.createTitledBorder("�����Ʒ"));

        JLabel label = new JLabel("\u79CD\u7C7B\u540D\u79F0\uFF1A");
        label.setFont(new Font("����", Font.PLAIN, 15));
        label.setBounds(19, 30, 80, 15);
        panel.add(label);

        JLabel label_2 = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
        label_2.setFont(new Font("����", Font.PLAIN, 15));
        label_2.setBounds(19, 52, 80, 30);
        panel.add(label_2);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(109, 27, 149, 21);
        panel.add(comboBox);
        comboBox.setEditable(true);

        textField = new JTextField();
        textField.setBounds(109, 57, 149, 21);
        panel.add(textField);
        textField.setColumns(10);


        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(113, 391, 450, 128);
        add(panel1);
        panel1.setLayout(null);
        panel1.setVisible(false);
        panel1.setBorder(BorderFactory.createTitledBorder("�޸���Ʒ"));


        JLabel label1 = new JLabel("\u79CD\u7C7B\u540D\u79F0\uFF1A");
        label1.setFont(new Font("����", Font.PLAIN, 15));
        label1.setBounds(19, 30, 80, 15);
        panel1.add(label1);

        JLabel label1_2 = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
        label1_2.setFont(new Font("����", Font.PLAIN, 15));
        label1_2.setBounds(19, 52, 80, 30);
        panel1.add(label1_2);


        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        panel2.setBounds(113, 391, 450, 128);
        add(panel2);
        panel2.setLayout(null);
        panel2.setVisible(false);
        panel2.setBorder(BorderFactory.createTitledBorder("��ѯ��Ʒ"));


        textField2 = new JTextField();
        textField2.setBounds(109, 57, 149, 21);
        panel1.add(textField2);
        textField2.setColumns(10);

        search_textField1 = new JTextField();
        search_textField1.setBounds(109, 27, 149, 21);
        panel2.add(search_textField1);
        search_textField1.setColumns(10);

        search_textField2 = new JTextField();
        search_textField2.setBounds(109, 57, 149, 21);
        panel2.add(search_textField2);
        search_textField2.setColumns(10);


        JLabel label_1 = new JLabel("\u79CD\u7C7B\u540D\u79F0\uFF1A");
        label_1.setFont(new Font("����", Font.PLAIN, 15));
        label_1.setBounds(19, 30, 80, 15);
        panel2.add(label_1);

        JLabel label_3 = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
        label_3.setFont(new Font("����", Font.PLAIN, 15));
        label_3.setBounds(19, 52, 80, 30);
        panel2.add(label_3);


        table = new JTable();
        table.setBounds(0, 0, 438, 250);
        JScrollPane scrollPane1 = new JScrollPane();
        String[] columnNames = {"��Ʒ����", "��Ʒ����"};
        columnNameV = new Vector<String>();
        for (int column = 0; column < columnNames.length; column++) {
            columnNameV.add(columnNames[column]);
        }

        HashSet set1 = new HashSet();
        tableValueV = new Vector<Vector<String>>();
        try {
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from goods;");  // �ύ��ѯ�����صı�񱣴���rs��

            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                if (set1.contains(rs.getString("lei") + rs.getString("name")) == false) {
                    Vector<String> rowV = new Vector<String>();
                    rowV.add(rs.getString("lei"));
                    rowV.add(rs.getString("name"));
                    tableValueV.add(rowV);
                    set1.add(rs.getString("lei") + rs.getString("name"));
                }
            }

            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLExceptionss
            System.out.println(sql_e);
        }

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setBounds(109, 27, 149, 21);
        panel1.add(comboBox_1);
        comboBox_1.setEditable(false);

        Iterator it = Main.leiset.iterator();
        while (it.hasNext()) {
            String s = it.next().toString();
            comboBox.addItem(s);
            comboBox_1.addItem(s);
            //Chuku.
        }

        tableModel = new DefaultTableModel(tableValueV,
                columnNameV);
        final JTable table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));
        scrollPane1.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableColumnModel tableColumnModel = table.getColumnModel();
        ListSelectionModel listSelectionModel = tableColumnModel
                .getSelectionModel();
        add(scrollPane1);
        scrollPane1.setBounds(110, 65, 438, 250);
        //table.setBounds(0, 0, 438, 250);
        //add(table);
        TableColumnModel cm = table.getColumnModel();
        TableColumn column = cm.getColumn(0);//�õ���i���ж���
        column.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��
        TableColumn column1 = cm.getColumn(1);//�õ���i���ж���
        column1.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        JButton button_1 = new JButton("\u6DFB\u52A0\u5546\u54C1");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel.isVisible() == false) panel.setVisible(true);
                else panel.setVisible(false);
                panel1.setVisible(false);
                panel2.setVisible(false);
            }
        });
        button_1.setFont(new Font("����", Font.PLAIN, 15));
        button_1.setBounds(216, 338, 106, 27);
        add(button_1);

        JButton button = new JButton("\u786E\u5B9A");//���
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String a = (String) comboBox.getSelectedItem(), b = textField.getText();
                if (Main.leiset.contains(a) == false) {
                    comboBox.addItem(a);
                    Main.leiset.add(a);
                    System.out.println(Main.leiset.size() + "goods");
                    Connection con;
                    try {
                        con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);
                        Statement s = con.createStatement();  // Statement�������ύ
                        String insert = "insert into fenlei(lei) values('" + a + "')";
                        s.executeUpdate(insert);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }  // �������ݿ�


                }

                //txtOut.append(a+"      "+b+"\n");
                if (set1.contains(a + b) == false) {
                    set1.add(a + b);
                    try {//����
                        Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                        Statement s = con.createStatement();  // Statement�������ύSQL���

                        String insert = "insert into goods(lei,name,num) values('" + a + "','" + b + "','0')";
                        s.executeUpdate(insert);
                        Vector<String> rowV = new Vector<String>();
                        rowV.add(a);
                        rowV.add(b);
                        tableValueV.add(rowV);
                        tableModel.setDataVector(tableValueV, columnNameV);
                        int rowCount = table.getRowCount();
                        table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
                        Rectangle rect = table.getCellRect(rowCount - 1, 0, true);
                        table.scrollRectToVisible(rect);

                        TableColumnModel cm = table.getColumnModel();
                        TableColumn column = cm.getColumn(0);//�õ���i���ж���
                        column.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��
                        TableColumn column1 = cm.getColumn(1);//�õ���i���ж���
                        column1.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��

                        Statement s2 = con.createStatement();  // Statement�������ύSQL���
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                        //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                        String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "�����Ʒ" + a + ":" + b + "�ɹ�" + "','" + Testmysql.limite + "')";
                        s2.executeUpdate(insert1);

                        s2.close();     // �ͷ�Statement����
                        s.close();     // �ͷ�Statement����
                        con.close();   // �رյ�MySQL������������
                    } catch (SQLException sql_e) {     // ����SQLExceptionss
                        System.out.println(sql_e);
                    }
                }


            }
        });
        button.setBounds(86, 95, 93, 23);
        panel.add(button);


        table.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if (selectedRow != -1)   //�Ƿ����ѡ����
                {
                    Object oa = tableModel.getValueAt(selectedRow, 0);
                    Object ob = tableModel.getValueAt(selectedRow, 1);
                    comboBox_1.setSelectedItem(oa.toString());
                    textField2.setText(ob.toString());
                } else {
                    textField2.setText("");
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });


        JButton btnNewButton = new JButton("\u4FEE\u6539");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel1.isVisible() == false) {
                    panel1.setVisible(true);

                } else panel1.setVisible(false);
                panel.setVisible(false);
                panel2.setVisible(false);
            }
        });
        btnNewButton.setBounds(354, 338, 106, 27);
        add(btnNewButton);

        JButton button1 = new JButton("\u786E\u5B9A");//�޸�
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if (selectedRow != -1)   //�Ƿ����ѡ����
                {
                    //�޸�ָ����ֵ��
                    String a = tableModel.getValueAt(selectedRow, 0).toString();
                    String b = tableModel.getValueAt(selectedRow, 1).toString();

                    tableModel.setValueAt(comboBox_1.getSelectedItem(), selectedRow, 0);
                    tableModel.setValueAt(textField2.getText(), selectedRow, 1);

                    try {
                        Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);  // Statement�������ύSQL���

                        ResultSet rs = s.executeQuery("select lei,name from goods");  // �ύ��ѯ�����صı�񱣴���rs��

                        while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                            if (rs.getString("lei").equals(a) && rs.getString("name").equals(b)) {
                                rs.updateString("lei", comboBox_1.getSelectedItem().toString());
                                rs.updateString("name", textField2.getText());
                                rs.updateRow();
                                Statement s2 = con.createStatement();  // Statement�������ύSQL���
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                                //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                                String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "�޸���Ʒ��Ϣ" + a + ":" + b + "�ɹ�" + "','" + Testmysql.limite + "')";
                                s2.executeUpdate(insert1);

                                s2.close();     // �ͷ�Statement����
                                break;
                            }
                        }


                        s.close();     // �ͷ�Statement����
                        con.close();   // �رյ�MySQL������������
                    } catch (SQLException sql_e) {     // ����SQLException
                        System.out.println(sql_e);
                    }
                    //table.setValueAt(arg0, arg1, arg2)
                }

            }

        });
        button1.setBounds(86, 95, 93, 23);
        panel1.add(button1);


        JButton button_2 = new JButton("\u5220\u9664");//ɾ��
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"ȷ��", "ȡ��"};
                int flag = JOptionPane.showOptionDialog(Test.zhu, "ȷ��ɾ���ù�Ӧ����Ϣ��", "ɾ��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (flag == 0) {
                    int selectedRow = table.getSelectedRow();//���ѡ���е�����
                    int rowcount = table.getRowCount();
                    if (selectedRow != -1 && !set1.isEmpty() && selectedRow != rowcount)  //����ѡ����
                    {
                        Object oa = tableModel.getValueAt(selectedRow, 0);
                        Object ob = tableModel.getValueAt(selectedRow, 1);
                        set1.remove(oa.toString() + ob.toString());
                        tableModel.removeRow(selectedRow);  //ɾ����

                        table.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                        Rectangle rect = table.getCellRect(selectedRow, 0, true);
                        table.scrollRectToVisible(rect);

                        try {
                            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                            Statement s = con.createStatement();  // Statement�������ύ

                            String delete = "Delete From goods Where lei='" + oa.toString() + "' and name='" + ob.toString() + "'";
                            s.executeUpdate(delete);

                            ResultSet rs = s.executeQuery("select * from goods;");  // �ύ��ѯ�����صı�񱣴���rs��

                            boolean flag1 = false;
                            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                                if (rs.getString("lei").equals(oa.toString())) {
                                    flag1 = true;
                                    break;
                                }
                            }
                            if (!flag1) {
                                Main.leiset.remove(oa.toString());
                                String delete1 = "Delete From fenlei Where lei='" + oa.toString() + "'";
                                s.executeUpdate(delete1);
                                comboBox.removeItem(oa.toString());

                                Statement s2 = con.createStatement();  // Statement�������ύSQL���
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                                //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                                String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "ɾ����Ʒ" + oa.toString() + ":" + ob.toString() + "�ɹ�" + "','" + Testmysql.limite + "')";
                                s2.executeUpdate(insert1);

                                s2.close();     // �ͷ�Statement����
                            }


                            s.close();     // �ͷ�Statement����
                            con.close();   // �رյ�MySQL������������
                        } catch (SQLException sql_e) {     // ����SQLException
                            System.out.println(sql_e);
                        }
                    }
                }

            }
        });
        button_2.setBounds(83, 338, 106, 27);
        add(button_2);

        JButton btnNewButton_2 = new JButton("\u67E5\u8BE2");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel2.isVisible() == false) panel2.setVisible(true);
                else panel2.setVisible(false);
                panel1.setVisible(false);
                panel.setVisible(false);
            }
        });
        btnNewButton_2.setBounds(487, 338, 106, 27);
        add(btnNewButton_2);

        JButton btnNewButton_1 = new JButton("\u786E\u5B9A");//ȷ����ѯ
        btnNewButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String a = search_textField1.getText();
                String b = search_textField2.getText();

                if (a.equals("")) a = "any(select lei from goods)";
                else a = "'" + a + "'";
                if (b.equals("")) b = "any(select name from goods)";
                else b = "'" + b + "'";

                table.removeAll();
                tableValueV.removeAllElements();
                try {//����

                    Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                    Statement s = con.createStatement();  // Statement�������ύSQL���


                    String select = "select * from goods where lei=" + a + " and name =" + b;
                    ResultSet rs = s.executeQuery(select);  // �ύ��ѯ�����صı�񱣴���rs��

                    while (rs.next()) {  // ResultSetָ��ָ����һ�����С�

                        Vector<String> rowV = new Vector<String>();
                        rowV.add(rs.getString("lei"));
                        rowV.add(rs.getString("name"));
                        tableValueV.add(rowV);
                        System.out.println(rs.getString("lei"));

                    }
                    tableModel.setDataVector(tableValueV, columnNameV);
                    int rowCount = table.getRowCount();
                    table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
                    Rectangle rect = table.getCellRect(rowCount - 1, 0, true);
                    table.scrollRectToVisible(rect);
                    s.close();     // �ͷ�Statement����
                    con.close();   // �رյ�MySQL������������
                    TableColumnModel cm = table.getColumnModel();
                    TableColumn column = cm.getColumn(0);//�õ���i���ж���
                    column.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��
                    TableColumn column1 = cm.getColumn(1);//�õ���i���ж���
                    column1.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��

                } catch (SQLException sql_e) {     // ����SQLExceptionss
                    System.out.println(sql_e);
                }

            }

        });
        btnNewButton_1.setBounds(86, 95, 93, 23);
        panel2.add(btnNewButton_1);

        JButton btnNewButton_3 = new JButton("\u53D6\u6D88");//ȡ��
        btnNewButton_3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                search_textField1.setText("");
                search_textField2.setText("");
                int num = tableModel.getRowCount();
                while (num-- != 0) {
                    tableModel.removeRow(0);
                }

                try {//����

                    Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                    Statement s = con.createStatement();  // Statement�������ύSQL���

                    ResultSet rs = s.executeQuery("select * from goods");  // �ύ��ѯ�����صı�񱣴���rs��

                    while (rs.next()) {  // ResultSetָ��ָ����һ�����С�

                        Vector<String> rowV = new Vector<String>();
                        rowV.add(rs.getString("lei"));
                        rowV.add(rs.getString("name"));
                        tableValueV.add(rowV);
                    }
                    tableModel.setDataVector(tableValueV, columnNameV);
                    int rowCount = table.getRowCount();
                    table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
                    Rectangle rect = table.getCellRect(rowCount - 1, 0, true);
                    table.scrollRectToVisible(rect);
                    s.close();     // �ͷ�Statement����
                    con.close();   // �رյ�MySQL������������
                    TableColumnModel cm = table.getColumnModel();
                    TableColumn column = cm.getColumn(0);//�õ���i���ж���
                    column.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��
                    TableColumn column1 = cm.getColumn(1);//�õ���i���ж���
                    column1.setPreferredWidth(210);//�����е���ѡ�������Ϊ preferredWidth��


                } catch (SQLException sql_e) {     // ����SQLExceptionss
                    System.out.println(sql_e);
                }
            }

        });
        btnNewButton_3.setBounds(200, 95, 93, 23);
        panel2.add(btnNewButton_3);

    }
}


