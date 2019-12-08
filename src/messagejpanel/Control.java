package messagejpanel;

import company.Test;
import company.Testmysql;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

public class Control extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */

    private JTable table;

    DefaultTableModel tableModel;
    Vector<Vector<String>> tableValueV;
    Vector<String> columnNameV;
    private JTextField add_name;
    private JTextField add_password;
    private JTextField modify_name;
    private JTextField modify_password;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Control frame = new Control();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Control() {
        int windowwidth = 650, windowheight = 520;
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, windowwidth, windowheight);
        setTitle("����");
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - windowwidth) / 2, (screenHeight - windowheight) / 2);


        table = new JTable();
        table.setBounds(0, 0, 438, 250);
        JScrollPane scrollPane1 = new JScrollPane();
        String[] columnNames = {"�û���", "����", "Ȩ��"};
        columnNameV = new Vector<String>();
        for (int column = 0; column < columnNames.length; column++) {
            columnNameV.add(columnNames[column]);
        }
        tableValueV = new Vector<Vector<String>>();

        String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

        String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

        try {
            Class.forName(JDriver);
        } catch (ClassNotFoundException cnf_e) {  // ����Ҳ���������
            System.out.println("Driver Not Found: " + cnf_e);
        }
        try {
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from users;");  // �ύ��ѯ�����صı�񱣴���rs��

            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                Vector<String> rowV = new Vector<String>();
                rowV.add(rs.getString("name"));
                rowV.add(rs.getString("password"));
                rowV.add(rs.getString("limite"));
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
        getContentPane().add(scrollPane1);
        scrollPane1.setBounds(110, 27, 438, 250);


        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(154, 347, 337, 120);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("��ӹ���Ա"));
        panel.setVisible(false);

        JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel.setBounds(9, 20, 71, 27);
        panel.add(lblNewLabel);

        add_name = new JTextField();
        add_name.setBounds(90, 20, 119, 21);
        panel.add(add_name);
        add_name.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
        lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(20, 50, 45, 26);
        panel.add(lblNewLabel_1);

        add_password = new JTextField();
        add_password.setBounds(90, 50, 119, 21);
        panel.add(add_password);
        add_password.setColumns(10);

        JButton button = new JButton("\u786E\u5B9A");
        button.setBounds(241, 50, 71, 32);
        panel.add(button);

        JLabel lblNewLabel_2 = new JLabel("\u6743\u9650\uFF1A");
        lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(20, 84, 54, 24);
        panel.add(lblNewLabel_2);

        JComboBox add_comboBox = new JComboBox();
        add_comboBox.setModel(new DefaultComboBoxModel(new String[]{"\u7BA1\u7406\u5458", "\u5458\u5DE5"}));
        add_comboBox.setBounds(90, 86, 119, 21);
        panel.add(add_comboBox);


        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

                String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

                String a = add_name.getText(), b = add_password.getText(), c = add_comboBox.getSelectedItem().toString();


                boolean flag = false;
                try {
                    Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                    Statement s = con.createStatement();  // Statement�������ύSQL���
                    Statement s1 = con.createStatement();  // Statement�������ύSQL���


                    ResultSet rs = s.executeQuery("select * from users");  // �ύ��ѯ�����صı�񱣴���rs��

                    while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                        if (a.equals(rs.getString("name"))) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        JOptionPane.showMessageDialog(Control.this, "���û��Ѵ���", "����", 2);
                    } else {
                        JOptionPane.showMessageDialog(Control.this, a + "��ӳɹ�", "�û���ӳɹ�", 1);
                        String insert = "insert into users(name,password,limite) values('" + a + "','" + b + "','" + c + "')";
                        s1.executeUpdate(insert);
                        Vector<String> rowV = new Vector<String>();
                        rowV.add(a);
                        rowV.add(b);
                        rowV.add(c);
                        tableValueV.add(rowV);
                        tableModel.setDataVector(tableValueV, columnNameV);
                        int rowCount = table.getRowCount();
                        table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
                        Rectangle rect = table.getCellRect(rowCount - 1, 0, true);
                        table.scrollRectToVisible(rect);
                        // �ͷ�Statement����
                        TableColumnModel cm = table.getColumnModel();
                        for (int i = 0; i < 3; i++) {
                            TableColumn column = cm.getColumn(i);//�õ���i���ж���
                            column.setPreferredWidth(140);//�����е���ѡ�������Ϊ preferredWidth��
                        }
                        s1.close();

                        Statement s2 = con.createStatement();  // Statement�������ύSQL���
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                        //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                        String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "���" + c + "�û���" + a + "','" + Testmysql.limite + "')";
                        s2.executeUpdate(insert1);

                        s2.close();     // �ͷ�Statement����
                    }


                    s.close();     // �ͷ�Statement����
                    con.close();   // �رյ�MySQL������������
                } catch (SQLException sql_e) {     // ����SQLException
                    System.out.println(sql_e);
                }
            }
        });

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(154, 347, 337, 120);
        contentPane.add(panel1);
        panel1.setLayout(null);
        panel1.setBorder(BorderFactory.createTitledBorder("�޸Ĺ���Ա"));
        panel1.setVisible(false);

        JLabel lblNewLabel1 = new JLabel("\u7528\u6237\u540D\uFF1A");
        lblNewLabel1.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel1.setBounds(9, 20, 71, 27);
        panel1.add(lblNewLabel1);

        modify_name = new JTextField();
        modify_name.setBounds(90, 20, 119, 21);
        panel1.add(modify_name);
        modify_name.setColumns(10);

        JLabel lblNewLabel_11 = new JLabel("\u5BC6\u7801\uFF1A");
        lblNewLabel_11.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel_11.setBounds(20, 50, 45, 26);
        panel1.add(lblNewLabel_11);

        modify_password = new JTextField();
        modify_password.setBounds(90, 50, 119, 21);
        panel1.add(modify_password);
        modify_password.setColumns(10);

        JComboBox modify_comboBox = new JComboBox();
        modify_comboBox.setModel(new DefaultComboBoxModel(new String[]{"\u7BA1\u7406\u5458", "\u5458\u5DE5"}));
        modify_comboBox.setBounds(90, 86, 119, 21);
        panel1.add(modify_comboBox);

        table.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                int selectedRow = table.getSelectedRow();//���ѡ���е�����
                if (selectedRow != -1)   //�Ƿ����ѡ����
                {
                    Object oa = tableModel.getValueAt(selectedRow, 0);
                    Object ob = tableModel.getValueAt(selectedRow, 1);
                    Object oc = tableModel.getValueAt(selectedRow, 2);
                    modify_name.setText(oa.toString());  //���ı���ֵ
                    modify_password.setText(ob.toString());
                    modify_comboBox.setSelectedItem(oc.toString());
                } else {
                    modify_name.setText("");  //���ı���ֵ
                    modify_password.setText("");
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


        JLabel lblNewLabel_3 = new JLabel("\u6743\u9650\uFF1A");
        lblNewLabel_3.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel_3.setBounds(20, 84, 50, 24);
        panel1.add(lblNewLabel_3);


        JButton button1 = new JButton("\u786E\u5B9A");
        button1.setBounds(241, 50, 71, 32);
        panel1.add(button1);


        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int selectedRow = table.getSelectedRow();//���ѡ���е�����

                if (selectedRow != -1)   //�Ƿ����ѡ����
                {
                    //�޸�ָ����ֵ��

                    String a = tableModel.getValueAt(selectedRow, 0).toString();
                    String b = tableModel.getValueAt(selectedRow, 1).toString();
                    String c = tableModel.getValueAt(selectedRow, 2).toString();


                    try {
                        Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                        Statement s = con.createStatement();  // Statement�������ύSQL���
                        Statement s1 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);  // Statement�������ύSQL���

                        ResultSet rs = s.executeQuery("select * from users");  // �ύ��ѯ�����صı�񱣴���rs��

                        boolean flag = false;
                        while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                            if (modify_name.getText().equals(rs.getString("name"))) {
                                flag = true;
                                break;
                            }
                        }
                        System.out.println(flag);
                        if (flag) {
                            if (!modify_name.getText().equals(a)) {
                                JOptionPane.showMessageDialog(Test.zhu, "���û��Ѵ���", "����", 2);
                            } else flag = false;
                        }
                        if (!flag) {

                            tableModel.setValueAt(modify_name.getText(), selectedRow, 0);
                            tableModel.setValueAt(modify_password.getText(), selectedRow, 1);
                            tableModel.setValueAt(modify_comboBox.getSelectedItem().toString(), selectedRow, 2);

                            ResultSet rs1 = s1.executeQuery("select name,password,limite from users");  // �ύ��ѯ�����صı�񱣴���rs��

                            System.out.println(a + "  " + b);
                            while (rs1.next()) {  // ResultSetָ��ָ����һ�����С�
                                //System.out.println(rs1.getString("name")+"  "+rs1.getString("password")+"  "+modify_name.getText()+"   "+modify_password.getText()+rs1.getString("limite")+"  "+modify_comboBox.getSelectedItem().toString());
                                if (rs1.getString("name").equals(a) && rs1.getString("password").equals(b) && rs1.getString("limite").equals(c)) {
                                    rs1.updateString("name", modify_name.getText());
                                    rs1.updateString("password", modify_password.getText());
                                    rs1.updateString("limite", modify_comboBox.getSelectedItem().toString());
                                    rs1.updateRow();
                                    break;
                                }

                            }

                        }

                        Statement s2 = con.createStatement();  // Statement�������ύSQL���
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                        //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                        String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "�޸�" + c + "�û���" + a + "','" + Testmysql.limite + "')";
                        s2.executeUpdate(insert1);

                        s2.close();     // �ͷ�Statement����

                        s1.close();
                        s.close();     // �ͷ�Statement����
                        con.close();   // �رյ�MySQL������������
                    } catch (SQLException sql_e) {     // ����SQLException
                        System.out.println(sql_e);
                    }
                    //table.setValueAt(arg0, arg1, arg2)
                }

            }
        });

        //table.setBounds(0, 0, 438, 250);
        //add(table);
        TableColumnModel cm = table.getColumnModel();
        for (int i = 0; i < 3; i++) {
            TableColumn column = cm.getColumn(i);//�õ���i���ж���
            column.setPreferredWidth(140);//�����е���ѡ�������Ϊ preferredWidth��
        }
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);


        JButton btnNewButton = new JButton("\u5220\u9664");
        btnNewButton.setBounds(110, 297, 93, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\u6DFB\u52A0");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (panel.isVisible() == false) panel.setVisible(true);
                else panel.setVisible(false);
                panel1.setVisible(false);
            }
        });
        btnNewButton_1.setBounds(275, 297, 93, 23);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("\u4FEE\u6539");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (panel1.isVisible() == false) panel1.setVisible(true);
                else panel1.setVisible(false);
                panel.setVisible(false);
            }
        });
        btnNewButton_2.setBounds(440, 297, 93, 23);
        contentPane.add(btnNewButton_2);
		
		/*JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel.setBounds(81, 81, 77, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(91, 129, 54, 23);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(156, 82, 152, 21);
		contentPane.add(name);
		name.setColumns(10);
		
		password = new JTextField();
		password.setBounds(155, 130, 152, 21);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

		        String conURL = "jdbc:mysql://localhost/Company";  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

		        String a=name.getText(),b=password.getText();
		        try {
		            Class.forName(JDriver);
		        }
		        catch(ClassNotFoundException cnf_e) {  // ����Ҳ���������
		            System.out.println("Driver Not Found: " + cnf_e);
		        }

		        boolean flag=false;
		        try {
		            Connection con = DriverManager.getConnection(conURL, "root", "");  // �������ݿ�

		            Statement s = con.createStatement();  // Statement�������ύSQL���
		            Statement s1 = con.createStatement();  // Statement�������ύSQL���
	
		            ResultSet rs = s.executeQuery("select * from user");  // �ύ��ѯ�����صı�񱣴���rs��

		            while(rs.next()) {  // ResultSetָ��ָ����һ�����С�
		               if(a.equals(rs.getString("name"))){flag=true;break;}
		            }
		            
		            if(flag){
		            	JOptionPane.showMessageDialog(Test.frame, "���û��Ѵ���","����",2);
		            }
		            else{
		            	JOptionPane.showMessageDialog(Test.frame, a+"��ӳɹ�","�û���ӳɹ�",1);
		            	String insert = "insert into user(name,password) values('"+a+"','"+b+"')";  
						s1.executeUpdate(insert);
		            }
					
					
		            s.close();     // �ͷ�Statement����
		            con.close();   // �رյ�MySQL������������
		        }
		        catch(SQLException sql_e) {     // ����SQLException
		            System.out.println(sql_e);
		        }
			}
		});
		btnNewButton.setBounds(81, 187, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setBounds(223, 187, 93, 23);
		contentPane.add(btnNewButton_1);*/
    }
}
