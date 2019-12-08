package messagejpanel;

import company.Test;
import company.Testmysql;

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

public class Kucun_Sykucun extends JPanel {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTable table;
    public int num = 0;

    public Kucun_Sykucun() {
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


        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(113, 385, 450, 159);
        add(panel1);
        panel1.setLayout(null);
        panel1.setVisible(false);
        panel1.setBorder(BorderFactory.createTitledBorder("�޸���Ʒ"));


        JLabel label1 = new JLabel("\u79CD\u7C7B\u540D\u79F0\uFF1A");
        label1.setFont(new Font("����", Font.PLAIN, 15));
        label1.setBounds(19, 30, 75, 15);
        panel1.add(label1);

        JLabel label1_2 = new JLabel("\u5546\u54C1\u540D\u79F0\uFF1A");
        label1_2.setFont(new Font("����", Font.PLAIN, 15));
        label1_2.setBounds(19, 52, 75, 30);
        panel1.add(label1_2);

        textField1 = new JTextField();
        textField1.setBounds(109, 27, 149, 21);
        panel1.add(textField1);
        textField1.setColumns(10);
        textField1.enable(false);

        textField2 = new JTextField();
        textField2.setBounds(109, 57, 149, 21);
        panel1.add(textField2);
        textField2.setColumns(10);
        textField2.enable(false);

        textField3 = new JTextField();
        textField3.setBounds(109, 88, 149, 21);
        panel1.add(textField3);
        textField3.setColumns(10);

        table = new JTable();
        table.setBounds(0, 0, 438, 250);
        JScrollPane scrollPane1 = new JScrollPane();
        String[] columnNames = {"��Ʒ����", "��Ʒ����", "����"};
        Vector<String> columnNameV = new Vector<String>();
        for (int column = 0; column < columnNames.length; column++) {
            columnNameV.add(columnNames[column]);
        }


        Vector<Vector<String>> tableValueV = new Vector<Vector<String>>();
        try {
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from goods;");  // �ύ��ѯ�����صı�񱣴���rs��

            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                Vector<String> rowV = new Vector<String>();
                if (!rs.getString("num").equals("0")) {
                    rowV.add(rs.getString("lei"));
                    rowV.add(rs.getString("name"));
                    rowV.add(rs.getString("num"));
                    tableValueV.add(rowV);
                    num++;
                }

            }

            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLExceptionss
            System.out.println(sql_e);
        }


        final DefaultTableModel tableModel = new DefaultTableModel(tableValueV,
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
        scrollPane1.setBounds(110, 56, 438, 250);
        //table.setBounds(0, 0, 438, 250);
        //add(table);
        TableColumnModel cm = table.getColumnModel();
        TableColumn column = cm.getColumn(0);//�õ���i���ж���
        column.setPreferredWidth(140);//�����е���ѡ�������Ϊ preferredWidth��
        TableColumn column1 = cm.getColumn(1);//�õ���i���ж���
        column1.setPreferredWidth(140);//�����е���ѡ�������Ϊ preferredWidth��
        TableColumn column2 = cm.getColumn(2);//�õ���i���ж���
        column2.setPreferredWidth(140);//�����е���ѡ�������Ϊ preferredWidth��
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);

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
                    textField1.setText(oa.toString());  //���ı���ֵ
                    textField2.setText(ob.toString());
                    textField3.setText(oc.toString());
                } else {
                    textField1.setText("");  //���ı���ֵ
                    textField2.setText("");
                    textField3.setText("");
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
            }
        });
        btnNewButton.setBounds(442, 338, 106, 27);
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
                    String c = tableModel.getValueAt(selectedRow, 2).toString();

                    tableModel.setValueAt(textField1.getText(), selectedRow, 0);
                    tableModel.setValueAt(textField2.getText(), selectedRow, 1);
                    tableModel.setValueAt(textField3.getText(), selectedRow, 2);
                    try {
                        Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                        Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);  // Statement�������ύSQ

                        ResultSet rs = s.executeQuery("select lei,name,num from goods");  // �ύ��ѯ�����صı�񱣴���rs��
                        while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                            if (rs.getString("lei").equals(a) && rs.getString("name").equals(b) && rs.getString("num").equals(c)) {
                                rs.updateString("num", textField3.getText());
                                rs.updateRow();
                                break;
                            }
                        }

                        Statement s2 = con.createStatement();  // Statement�������ύSQL���
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                        //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                        String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "�޸�ʣ����" + a + ":" + b + " " + c + "�ɹ�" + "','" + Testmysql.limite + "')";
                        s2.executeUpdate(insert1);

                        s2.close();     // �ͷ�Statement����
                        s.close();     // �ͷ�Statement����
                        con.close();   // �رյ�MySQL������������
                    } catch (SQLException sql_e) {     // ����SQLException
                        System.out.println(sql_e);
                    }

                }

            }

        });
        button1.setBounds(76, 126, 93, 23);
        panel1.add(button1);

        JLabel label = new JLabel("\u6570\u91CF\uFF1A");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("����", Font.PLAIN, 15));
        label.setBounds(19, 83, 75, 30);
        panel1.add(label);

        JButton button_2 = new JButton("\u5220\u9664");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"ȷ��", "ȡ��"};
                int flag = JOptionPane.showOptionDialog(Test.zhu, "ȷ��ɾ���ù�Ӧ����Ϣ��", "ɾ��", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (flag == 0) {
                    int selectedRow = table.getSelectedRow();//���ѡ���е�����
                    int rowcount = table.getRowCount();
                    if (selectedRow != -1 && selectedRow != rowcount)  //����ѡ����
                    {
                        Object oa = tableModel.getValueAt(selectedRow, 0);
                        Object ob = tableModel.getValueAt(selectedRow, 1);
                        Object oc = tableModel.getValueAt(selectedRow, 2);
                        tableModel.removeRow(selectedRow);  //ɾ����

                        table.getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
                        Rectangle rect = table.getCellRect(selectedRow, 0, true);
                        table.scrollRectToVisible(rect);

                        try {
                            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                            Statement s = con.createStatement();  // Statement�������ύ

                            String delete = "Delete From kucun Where name='" + ob.toString() + "'";

                            Statement s2 = con.createStatement();  // Statement�������ύSQL���
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
                            //System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
                            String insert1 = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + Testmysql.limite + Testmysql.Name + "ɾ��ʣ����" + oa.toString() + ":" + ob.toString() + " " + oc.toString() + "�ɹ�" + "','" + Testmysql.limite + "')";
                            s2.executeUpdate(insert1);

                            s2.close();     // �ͷ�Statement����

                            s.executeUpdate(delete);
                            s.close();     // �ͷ�Statement����
                            con.close();   // �رյ�MySQL������������
                        } catch (SQLException sql_e) {     // ����SQLException
                            System.out.println(sql_e);
                        }
                    }
                }
            }
        });
        button_2.setBounds(113, 338, 106, 27);
        add(button_2);
    }
}
