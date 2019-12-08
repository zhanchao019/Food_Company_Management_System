package messagejpanel;

import company.Test;
import company.Testmysql;
import jframe.Login;

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
import java.util.Vector;

public class Message_employee extends JPanel {

    /**
     * Create the panel.
     */

    private JTextField modify_textField;
    private JTable table;

    String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

    String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL
    DefaultTableModel tableModel;
    Vector<Vector<String>> tableValueV;
    Vector<String> columnNameV;
    private JTextField add_born;
    private JTextField add_addr;
    private JTextField add_name;
    private JTextField add_position;
    private JTextField add_sex;
    private JTextField add_tel;
    private JTextField add_time;
    private JTextField add_school;

    private JTextField modify_born;
    private JTextField modify_addr;
    private JTextField modify_name;
    private JTextField modify_position;
    private JTextField modify_sex;
    private JTextField modify_tel;
    private JTextField modify_time;
    private JTextField modify_school;

    private JTextField search_born;
    private JTextField search_addr;
    private JTextField search_name;
    private JTextField search_position;
    private JTextField search_sex;
    private JTextField search_tel;
    private JTextField search_time;
    private JTextField search_school;

    public Message_employee() {
        setBackground(Color.WHITE);
        setBounds(197, 0, 637, 570);
        setLayout(null);
        init();
    }

    public void init() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(39, 372, 568, 188);
        add(panel);
        panel.setLayout(null);
        panel.setVisible(false);
        panel.setBorder(BorderFactory.createTitledBorder("���Ա��"));

        JPanel panel1 = new JPanel();
        panel1.setBounds(39, 372, 568, 188);
        add(panel1);
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(null);
        panel1.setVisible(false);
        panel1.setBorder(BorderFactory.createTitledBorder("�޸�Ա��"));

        JPanel panel2 = new JPanel();
        panel2.setBounds(39, 372, 568, 188);
        add(panel2);
        panel2.setBackground(Color.WHITE);
        panel2.setLayout(null);
        panel2.setVisible(false);
        panel2.setBorder(BorderFactory.createTitledBorder("��ѯԱ�� "));


        JLabel add_label = new JLabel("\u59D3\u540D\uFF1A");
        add_label.setFont(new Font("����", Font.PLAIN, 15));
        add_label.setBounds(19, 24, 71, 21);
        panel.add(add_label);

        add_name = new JTextField();
        add_name.setBounds(100, 24, 93, 21);
        panel.add(add_name);
        add_name.setColumns(10);

        JLabel add_label_1 = new JLabel("\u5BB6\u5EAD\u4F4F\u5740\uFF1A");
        add_label_1.setFont(new Font("����", Font.PLAIN, 15));
        add_label_1.setBounds(203, 100, 82, 30);
        panel.add(add_label_1);

        add_addr = new JTextField();
        add_addr.setBounds(282, 105, 262, 21);
        panel.add(add_addr);
        add_addr.setColumns(10);

        JLabel add_label_5 = new JLabel("\u51FA\u751F\u5E74\u6708\uFF1A");
        add_label_5.setFont(new Font("����", Font.PLAIN, 15));
        add_label_5.setBounds(19, 59, 82, 30);
        panel.add(add_label_5);

        add_born = new JTextField();
        add_born.setBounds(100, 64, 93, 21);
        panel.add(add_born);
        add_born.setColumns(10);

        JLabel add_label_3 = new JLabel("\u804C\u4F4D\uFF1A");
        add_label_3.setFont(new Font("����", Font.PLAIN, 15));
        add_label_3.setBounds(211, 24, 54, 21);
        panel.add(add_label_3);

        add_position = new JTextField();
        add_position.setBounds(261, 24, 122, 21);
        panel.add(add_position);
        add_position.setColumns(10);

        JLabel add_label_4 = new JLabel("\u6027\u522B\uFF1A");
        add_label_4.setFont(new Font("����", Font.PLAIN, 15));
        add_label_4.setBounds(400, 25, 54, 18);
        panel.add(add_label_4);

        add_sex = new JTextField();
        add_sex.setBounds(451, 24, 93, 21);
        panel.add(add_sex);
        add_sex.setColumns(10);

        JLabel add_label_2 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
        add_label_2.setFont(new Font("����", Font.PLAIN, 15));
        add_label_2.setBounds(203, 63, 79, 23);
        panel.add(add_label_2);

        add_tel = new JTextField();
        add_tel.setBounds(271, 64, 112, 21);
        panel.add(add_tel);
        add_tel.setColumns(10);

        JLabel add_lblNewLabel_4 = new JLabel("\u5B66\u5386\uFF1A");
        add_lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 15));
        add_lblNewLabel_4.setBounds(400, 64, 54, 21);
        panel.add(add_lblNewLabel_4);

        add_school = new JTextField();
        add_school.setBounds(451, 64, 93, 21);
        panel.add(add_school);
        add_school.setColumns(10);

        JLabel add_label_6 = new JLabel("\u5165\u804C\u65F6\u95F4\uFF1A");
        add_label_6.setFont(new Font("����", Font.PLAIN, 15));
        add_label_6.setBounds(19, 102, 82, 26);
        panel.add(add_label_6);

        add_time = new JTextField();
        add_time.setBounds(100, 102, 93, 21);
        panel.add(add_time);
        add_time.setColumns(10);


        JLabel modify_label = new JLabel("\u59D3\u540D\uFF1A");
        modify_label.setFont(new Font("����", Font.PLAIN, 15));
        modify_label.setBounds(19, 24, 71, 21);
        panel1.add(modify_label);

        modify_name = new JTextField();
        modify_name.setBounds(100, 24, 93, 21);
        panel1.add(modify_name);
        modify_name.setColumns(10);

        JLabel modify_label_1 = new JLabel("\u5BB6\u5EAD\u4F4F\u5740\uFF1A");
        modify_label_1.setFont(new Font("����", Font.PLAIN, 15));
        modify_label_1.setBounds(203, 100, 82, 30);
        panel1.add(modify_label_1);

        modify_addr = new JTextField();
        modify_addr.setBounds(282, 105, 262, 21);
        panel1.add(modify_addr);
        modify_addr.setColumns(10);

        JLabel modify_label_5 = new JLabel("\u51FA\u751F\u5E74\u6708\uFF1A");
        modify_label_5.setFont(new Font("����", Font.PLAIN, 15));
        modify_label_5.setBounds(19, 59, 82, 30);
        panel1.add(modify_label_5);

        modify_born = new JTextField();
        modify_born.setBounds(100, 64, 93, 21);
        panel1.add(modify_born);
        modify_born.setColumns(10);

        JLabel modify_label_3 = new JLabel("\u804C\u4F4D\uFF1A");
        modify_label_3.setFont(new Font("����", Font.PLAIN, 15));
        modify_label_3.setBounds(211, 24, 54, 21);
        panel1.add(modify_label_3);

        modify_position = new JTextField();
        modify_position.setBounds(261, 24, 122, 21);
        panel1.add(modify_position);
        modify_position.setColumns(10);

        JLabel modify_label_4 = new JLabel("\u6027\u522B\uFF1A");
        modify_label_4.setFont(new Font("����", Font.PLAIN, 15));
        modify_label_4.setBounds(400, 25, 54, 18);
        panel1.add(modify_label_4);

        modify_sex = new JTextField();
        modify_sex.setBounds(451, 24, 93, 21);
        panel1.add(modify_sex);
        modify_sex.setColumns(10);

        JLabel modify_label_2 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
        modify_label_2.setFont(new Font("����", Font.PLAIN, 15));
        modify_label_2.setBounds(203, 63, 79, 23);
        panel1.add(modify_label_2);

        modify_tel = new JTextField();
        modify_tel.setBounds(271, 64, 112, 21);
        panel1.add(modify_tel);
        modify_tel.setColumns(10);

        JLabel modify_lblNewLabel_4 = new JLabel("\u5B66\u5386\uFF1A");
        modify_lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 15));
        modify_lblNewLabel_4.setBounds(400, 64, 54, 21);
        panel1.add(modify_lblNewLabel_4);

        modify_school = new JTextField();
        modify_school.setBounds(451, 64, 93, 21);
        panel1.add(modify_school);
        modify_school.setColumns(10);

        JLabel modify_label_6 = new JLabel("\u5165\u804C\u65F6\u95F4\uFF1A");
        modify_label_6.setFont(new Font("����", Font.PLAIN, 15));
        modify_label_6.setBounds(19, 102, 82, 26);
        panel1.add(modify_label_6);

        modify_time = new JTextField();
        modify_time.setBounds(100, 102, 93, 21);
        panel1.add(modify_time);
        modify_time.setColumns(10);


        JLabel search_label = new JLabel("\u59D3\u540D\uFF1A");
        search_label.setFont(new Font("����", Font.PLAIN, 15));
        search_label.setBounds(19, 24, 71, 21);
        panel2.add(search_label);

        search_name = new JTextField();
        search_name.setBounds(100, 24, 93, 21);
        panel2.add(search_name);
        search_name.setColumns(10);

        JLabel search_label_1 = new JLabel("\u5BB6\u5EAD\u4F4F\u5740\uFF1A");
        search_label_1.setFont(new Font("����", Font.PLAIN, 15));
        search_label_1.setBounds(203, 100, 82, 30);
        panel2.add(search_label_1);

        search_addr = new JTextField();
        search_addr.setBounds(282, 105, 262, 21);
        panel2.add(search_addr);
        search_addr.setColumns(10);

        JLabel search_label_5 = new JLabel("\u51FA\u751F\u5E74\u6708\uFF1A");
        search_label_5.setFont(new Font("����", Font.PLAIN, 15));
        search_label_5.setBounds(19, 59, 82, 30);
        panel2.add(search_label_5);

        search_born = new JTextField();
        search_born.setBounds(100, 64, 93, 21);
        panel2.add(search_born);
        search_born.setColumns(10);

        JLabel search_label_3 = new JLabel("\u804C\u4F4D\uFF1A");
        search_label_3.setFont(new Font("����", Font.PLAIN, 15));
        search_label_3.setBounds(211, 24, 54, 21);
        panel2.add(search_label_3);

        search_position = new JTextField();
        search_position.setBounds(261, 24, 122, 21);
        panel2.add(search_position);
        search_position.setColumns(10);

        JLabel search_label_4 = new JLabel("\u6027\u522B\uFF1A");
        search_label_4.setFont(new Font("����", Font.PLAIN, 15));
        search_label_4.setBounds(400, 25, 54, 18);
        panel2.add(search_label_4);

        search_sex = new JTextField();
        search_sex.setBounds(451, 24, 93, 21);
        panel2.add(search_sex);
        search_sex.setColumns(10);

        JLabel search_label_2 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
        search_label_2.setFont(new Font("����", Font.PLAIN, 15));
        search_label_2.setBounds(203, 63, 79, 23);
        panel2.add(search_label_2);

        search_tel = new JTextField();
        search_tel.setBounds(271, 64, 112, 21);
        panel2.add(search_tel);
        search_tel.setColumns(10);

        JLabel search_lblNewLabel_4 = new JLabel("\u5B66\u5386\uFF1A");
        search_lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 15));
        search_lblNewLabel_4.setBounds(400, 64, 54, 21);
        panel2.add(search_lblNewLabel_4);

        search_school = new JTextField();
        search_school.setBounds(451, 64, 93, 21);
        panel2.add(search_school);
        search_school.setColumns(10);

        JLabel search_label_6 = new JLabel("\u5165\u804C\u65F6\u95F4\uFF1A");
        search_label_6.setFont(new Font("����", Font.PLAIN, 15));
        search_label_6.setBounds(19, 102, 82, 26);
        panel2.add(search_label_6);

        search_time = new JTextField();
        search_time.setBounds(100, 102, 93, 21);
        panel2.add(search_time);
        search_time.setColumns(10);


        table = new JTable();
        table.setBounds(0, 0, 438, 250);
        JScrollPane scrollPane1 = new JScrollPane();
        String[] columnNames = {"����", "ְλ", "�Ա�", "��������", "��ϵ��ʽ", "ѧ��", "��ְʱ��", "��ַ"};
        columnNameV = new Vector<String>();
        for (int column = 0; column < columnNames.length; column++) {
            columnNameV.add(columnNames[column]);
        }


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


        tableValueV = new Vector<Vector<String>>();
        try {
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from employee;");  // �ύ��ѯ�����صı�񱣴���rs��

            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�

                Vector<String> rowV = new Vector<String>();
                rowV.add(rs.getString("name"));
                rowV.add(rs.getString("position"));
                rowV.add(rs.getString("sex"));
                rowV.add(rs.getString("born"));
                rowV.add(rs.getString("tel"));
                rowV.add(rs.getString("school"));
                rowV.add(rs.getString("time"));
                rowV.add(rs.getString("addr"));
                tableValueV.add(rowV);
            }

            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLExceptionss
            System.out.println(sql_e);
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
        scrollPane1.setBounds(66, 61, 510, 250);
        //table.setBounds(0, 0, 438, 250);
        //add(table);
        TableColumnModel cm = table.getColumnModel();
        for (int i = 0; i < 8; i++) {
            TableColumn column = cm.getColumn(i);//�õ���i���ж���
            column.setPreferredWidth(61);//�����е���ѡ�������Ϊ preferredWidth��
        }

        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

        JButton button_1 = new JButton("\u6DFB\u52A0");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel.isVisible() == false) panel.setVisible(true);
                else panel.setVisible(false);
                panel1.setVisible(false);
                panel2.setVisible(false);
            }
        });
        button_1.setFont(new Font("����", Font.PLAIN, 15));
        button_1.setBounds(197, 321, 106, 27);
        if (Login.limite.getSelectedItem().equals("����Ա")) add(button_1);


        JButton add_button = new JButton("\u786E\u5B9A");//���
        add_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String a = add_name.getText(), b = add_position.getText(), c = add_sex.getText(), d = add_born.getText(), e = add_tel.getText(), f = add_school.getText(), g = add_time.getText(), h = add_addr.getText();
                //txtOut.append(a+"      "+b+"\n");
                try {//����
                    Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                    Statement s = con.createStatement();  // Statement�������ύSQL���

                    String insert = "insert into employee(name,position,sex,born,tel,school,time,addr) values('" + a + "','" + b + "','" + c + "','" + d + "','" + e + "','" + f + "','" + g + "','" + h + "')";
                    s.executeUpdate(insert);
                    Vector<String> rowV = new Vector<String>();
                    rowV.add(a);
                    rowV.add(b);
                    rowV.add(c);
                    rowV.add(d);
                    rowV.add(e);
                    rowV.add(f);
                    rowV.add(g);
                    rowV.add(h);
                    tableValueV.add(rowV);
                    tableModel.setDataVector(tableValueV, columnNameV);
                    int rowCount = table.getRowCount();
                    table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
                    Rectangle rect = table.getCellRect(rowCount - 1, 0, true);
                    table.scrollRectToVisible(rect);

                    TableColumnModel cm = table.getColumnModel();
                    for (int i = 0; i < 8; i++) {
                        TableColumn column = cm.getColumn(i);//�õ���i���ж���
                        column.setPreferredWidth(61);//�����е���ѡ�������Ϊ preferredWidth��
                    }

                    Statement s2 = con.createStatement();  // Statement�������ύSQL���
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                    //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                    String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "���Ա��" + a + "�ɹ�" + "','" + Testmysql.limite + "')";
                    s2.executeUpdate(insert1);

                    s2.close();     // �ͷ�Statement����
                    s.close();     // �ͷ�Statement����
                    con.close();   // �رյ�MySQL������������

                } catch (SQLException sql_e) {     // ����SQLExceptionss
                    System.out.println(sql_e);
                }
            }
        });
        add_button.setBounds(226, 140, 93, 28);
        panel.add(add_button);


        table.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if (selectedRow != -1)   //�Ƿ����ѡ����
                {
                    String oa = tableModel.getValueAt(selectedRow, 0).toString();
                    String ob = tableModel.getValueAt(selectedRow, 1).toString();
                    String oc = tableModel.getValueAt(selectedRow, 2).toString();
                    String od = tableModel.getValueAt(selectedRow, 3).toString();
                    String oe = tableModel.getValueAt(selectedRow, 4).toString();
                    String of = tableModel.getValueAt(selectedRow, 5).toString();
                    String og = tableModel.getValueAt(selectedRow, 6).toString();
                    String oh = tableModel.getValueAt(selectedRow, 7).toString();
			       /* modify_comboBox_1.setText(oa.toString());
			        modify_comboBox_1*/
                    modify_name.setText(oa);
                    modify_position.setText(ob);
                    modify_sex.setText(oc);
                    modify_born.setText(od);
                    modify_tel.setText(oe);
                    modify_school.setText(of);
                    modify_time.setText(og);
                    modify_addr.setText(oh);

                } else {
                    modify_name.setText("");
                    modify_position.setText("");
                    modify_sex.setText("");
                    modify_born.setText("");
                    modify_tel.setText("");
                    modify_school.setText("");
                    modify_time.setText("");
                    modify_addr.setText("");
                }
            }

            private void setText(String string) {
                // TODO Auto-generated method stub

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


        JButton btnNewButton = new JButton("\u4FEE\u6539");//�޸�
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel1.isVisible() == false) {
                    panel1.setVisible(true);
                } else panel1.setVisible(false);
                panel.setVisible(false);
                panel2.setVisible(false);
            }
        });
        btnNewButton.setBounds(337, 321, 106, 27);
        if (Login.limite.getSelectedItem().equals("����Ա")) add(btnNewButton);

        JButton button_2 = new JButton("\u5220\u9664");//ɾ��
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Object[] options = {"ȷ��", "ȡ��"};
                int flag = JOptionPane.showOptionDialog(Test.zhu, "ȷ��ɾ���ù�Ӧ����Ϣ��", "ɾ��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                System.out.println(flag + "");
                if (flag == 0) {
                    int selectedRow = table.getSelectedRow();//���ѡ���е�����
                    int rowcount = table.getRowCount();
                    if (selectedRow != -1 && selectedRow != rowcount)  //����ѡ����
                    {
                        String oa = tableModel.getValueAt(selectedRow, 0).toString();
                        String ob = tableModel.getValueAt(selectedRow, 1).toString();
                        String oc = tableModel.getValueAt(selectedRow, 2).toString();
                        String od = tableModel.getValueAt(selectedRow, 3).toString();
                        String oe = tableModel.getValueAt(selectedRow, 4).toString();
                        String of = tableModel.getValueAt(selectedRow, 5).toString();
                        String og = tableModel.getValueAt(selectedRow, 6).toString();
                        String oh = tableModel.getValueAt(selectedRow, 7).toString();

                        tableModel.removeRow(selectedRow);  //ɾ����

                        table.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                        Rectangle rect = table.getCellRect(selectedRow, 0, true);
                        table.scrollRectToVisible(rect);
                        try {
                            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                            Statement s = con.createStatement();  // Statement�������ύ

                            String delete = "Delete From employee Where name='" + oa + "' and position='" + ob + "' and sex='" + oc + "' and born='" + od + "' and tel='" + oe + "' and school='" + of + "' and time='" + og + "' and addr='" + oh + "'";

                            s.executeUpdate(delete);

                            Statement s2 = con.createStatement();  // Statement�������ύSQL���
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                            //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                            String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "ɾ��Ա��" + oa.toString() + "�ɹ�" + "','" + Testmysql.limite + "')";
                            s2.executeUpdate(insert1);

                            s2.close();     // �ͷ�Statement����
                            s.close();     // �ͷ�Statement����
                            con.close();   // �رյ�MySQL������������
                        } catch (SQLException sql_e) {     // ����SQLException
                            System.out.println(sql_e);
                        }
                    }
                }
            }
        });
        button_2.setBounds(66, 321, 106, 27);
        if (Login.limite.getSelectedItem().equals("����Ա")) add(button_2);


        JButton button1 = new JButton("\u786E\u5B9A");//�޸�
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if (selectedRow != -1)   //�Ƿ����ѡ����
                {
                    //�޸�ָ����ֵ��
                    //tableModel.setValueAt(modify_comboBox_1.getSelectedItem().toString(),selectedRow,0);


                    String a = tableModel.getValueAt(selectedRow, 0).toString();
                    String b = tableModel.getValueAt(selectedRow, 1).toString();
                    String c = tableModel.getValueAt(selectedRow, 2).toString();
                    String d = tableModel.getValueAt(selectedRow, 3).toString();
                    String e = tableModel.getValueAt(selectedRow, 4).toString();
                    String f = tableModel.getValueAt(selectedRow, 5).toString();
                    String g = tableModel.getValueAt(selectedRow, 6).toString();
                    String h = tableModel.getValueAt(selectedRow, 7).toString();

                    tableModel.setValueAt(modify_name.getText(), selectedRow, 0);
                    tableModel.setValueAt(modify_position.getText(), selectedRow, 1);
                    tableModel.setValueAt(modify_sex.getText(), selectedRow, 2);
                    tableModel.setValueAt(modify_born.getText(), selectedRow, 3);
                    tableModel.setValueAt(modify_tel.getText(), selectedRow, 4);
                    tableModel.setValueAt(modify_school.getText(), selectedRow, 5);
                    tableModel.setValueAt(modify_time.getText(), selectedRow, 6);
                    tableModel.setValueAt(modify_addr.getText(), selectedRow, 7);

                    try {
                        Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);  // Statement�������ύSQL���

                        ResultSet rs = s.executeQuery("select name,position,sex,born,tel,school,time,addr from employee");  // �ύ��ѯ�����صı�񱣴���rs��

                        while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                            System.out.println("" + rs.getString("name").equals(a) + rs.getString("position").equals(b) + rs.getString("sex").equals(c) + rs.getString("born").equals(d) + rs.getString("tel").equals(e) + rs.getString("school").equals(f) + rs.getString("time").equals(g) + rs.getString("addr").equals(h));
                            if (rs.getString("name").equals(a) && rs.getString("position").equals(b) && rs.getString("sex").equals(c) && rs.getString("born").equals(d) && rs.getString("tel").equals(e) && rs.getString("school").equals(f) && rs.getString("time").equals(g) && rs.getString("addr").equals(h)) {
                                rs.updateString("name", modify_name.getText());
                                rs.updateString("position", modify_position.getText());
                                rs.updateString("sex", modify_sex.getText());
                                rs.updateString("born", modify_born.getText());
                                rs.updateString("tel", modify_tel.getText());
                                rs.updateString("school", modify_school.getText());
                                rs.updateString("time", modify_time.getText());
                                rs.updateString("addr", modify_addr.getText());
                                rs.updateRow();
                                System.out.println("yes");
                                break;
                            }
                        }

                        Statement s2 = con.createStatement();  // Statement�������ύSQL���
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                        //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                        String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "�޸�Ա��" + a + "�ɹ�" + "','" + Testmysql.limite + "')";
                        s2.executeUpdate(insert1);

                        s2.close();     // �ͷ�Statement����
                        s.close();     // �ͷ�Statement����
                        con.close();   // �رյ�MySQL������������
                    } catch (SQLException sql_e) {     // ����SQLException
                        System.out.println(sql_e);
                    }
                    //table.setValueAt(arg0, arg1, arg2)
                }

            }

        });
        button1.setBounds(226, 140, 93, 28);
        panel1.add(button1);

        JButton button_3 = new JButton("\u67E5\u8BE2");//��ѯ
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel2.isVisible() == false) {
                    panel2.setVisible(true);
                } else panel2.setVisible(false);
                panel.setVisible(false);
                panel1.setVisible(false);
            }
        });
        button_3.setBounds(470, 321, 106, 27);
        add(button_3);

        JButton btnNewButton_1 = new JButton("\u786E\u5B9A");//ȷ����ѯ
        btnNewButton_1.setBounds(145, 140, 93, 28);
        panel2.add(btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String a = search_name.getText();
                String b = search_position.getText();
                String c = search_sex.getText();
                String d = search_born.getText();
                String e1 = search_tel.getText();
                String f = search_school.getText();
                String g = search_time.getText();
                String h = search_addr.getText();

                if (a.equals("")) a = "any(select name from employee)";
                else a = "'" + a + "'";
                if (b.equals("")) b = "any(select position from employee)";
                else b = "'" + b + "'";
                if (c.equals("")) c = "any(select sex from employee)";
                else c = "'" + c + "'";
                if (d.equals("")) d = "any(select born from employee)";
                else d = "'" + d + "'";
                if (e1.equals("")) e1 = "any(select tel from employee)";
                else e1 = "'" + e1 + "'";
                if (f.equals("")) f = "any(select school from employee)";
                else f = "'" + f + "'";
                if (g.equals("")) g = "any(select time from employee)";
                else g = "'" + g + "'";
                if (h.equals("")) h = "any(select addr from employee)";
                else h = "'" + h + "'";

                int num = tableModel.getRowCount();
                while (num-- != 0) {
                    tableModel.removeRow(0);
                }
                try {//����

                    Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                    Statement s = con.createStatement();  // Statement�������ύSQL���


                    String select = "select * from employee where name=" + a + " and position=" + b + " and sex = " + c + " and born = " + d + " and tel = " + e1 + " and school = " + f + " and time = " + g + " and addr = " + h;
                    ResultSet rs = s.executeQuery(select);  // �ύ��ѯ�����صı�񱣴���rs��

                    while (rs.next()) {  // ResultSetָ��ָ����һ�����С�

                        Vector<String> rowV = new Vector<String>();
                        rowV.add(rs.getString("name"));
                        rowV.add(rs.getString("position"));
                        rowV.add(rs.getString("sex"));
                        rowV.add(rs.getString("born"));
                        rowV.add(rs.getString("tel"));
                        rowV.add(rs.getString("school"));
                        rowV.add(rs.getString("time"));
                        rowV.add(rs.getString("addr"));
                        tableValueV.add(rowV);
                        System.out.println("yes");

                    }
                    tableModel.setDataVector(tableValueV, columnNameV);
                    int rowCount = table.getRowCount();
                    table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
                    Rectangle rect = table.getCellRect(rowCount - 1, 0, true);
                    table.scrollRectToVisible(rect);
                    s.close();     // �ͷ�Statement����
                    con.close();   // �رյ�MySQL������������
                    TableColumnModel cm = table.getColumnModel();
                    for (int i = 0; i < 8; i++) {
                        TableColumn column = cm.getColumn(i);//�õ���i���ж���
                        column.setPreferredWidth(61);//�����е���ѡ�������Ϊ preferredWidth��
                    }
                } catch (SQLException sql_e) {     // ����SQLExceptionss
                    System.out.println("modify  " + sql_e);
                }

            }

        });

        JButton canel = new JButton("\u53D6\u6D88");
        canel.setBounds(280, 140, 93, 28);
        canel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                modify_name.setText("");
                modify_position.setText("");
                modify_sex.setText("");
                modify_born.setText("");
                modify_tel.setText("");
                modify_school.setText("");
                modify_time.setText("");
                modify_addr.setText("");

                table.removeAll();
                tableValueV.removeAllElements();


                try {//����

                    Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                    Statement s = con.createStatement();  // Statement�������ύSQL���

                    ResultSet rs = s.executeQuery("select * from employee");  // �ύ��ѯ�����صı�񱣴���rs��

                    while (rs.next()) {  // ResultSetָ��ָ����һ�����С�

                        Vector<String> rowV = new Vector<String>();
                        rowV.add(rs.getString("name"));
                        rowV.add(rs.getString("position"));
                        rowV.add(rs.getString("sex"));
                        rowV.add(rs.getString("born"));
                        rowV.add(rs.getString("tel"));
                        rowV.add(rs.getString("school"));
                        rowV.add(rs.getString("time"));
                        rowV.add(rs.getString("addr"));
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
                    for (int i = 0; i < 8; i++) {
                        TableColumn column = cm.getColumn(i);//�õ���i���ж���
                        column.setPreferredWidth(61);//�����е���ѡ�������Ϊ preferredWidth��
                    }

                } catch (SQLException sql_e) {     // ����SQLExceptionss
                    System.out.println(sql_e);
                }

            }

        });
        panel2.add(canel);
    }
}
