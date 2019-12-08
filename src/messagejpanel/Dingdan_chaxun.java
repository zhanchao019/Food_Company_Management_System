package messagejpanel;

import company.Test;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class Dingdan_chaxun extends JPanel {
    private JTextField message;
    private JComboBox leixing;
    private JComboBox tiaojian;
    private JComboBox fangshi;
    private JTable table;

    /**
     * Create the panel.
     */
    DefaultTableModel tableModel;
    Vector<Vector<String>> tableValueV;
    Vector<String> columnNameV;

    String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

    String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

    public Dingdan_chaxun() {
        setBackground(Color.WHITE);
        setBounds(197, 0, 637, 570);
        setLayout(null);
        init();
    }

    public void init() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(148, 366, 367, 178);
        add(panel);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("������ѯ"));

        JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u7C7B\u76EE\uFF1A");
        lblNewLabel.setBounds(25, 36, 75, 18);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));

        leixing = new JComboBox();
        leixing.setBounds(108, 35, 129, 21);
        panel.add(leixing);
        leixing.setModel(new DefaultComboBoxModel(new String[]{"\u5165\u5E93\u8BA2\u5355", "\u51FA\u5E93\u8BA2\u5355"}));

        JLabel label = new JLabel("\u67E5\u8BE2\u6761\u4EF6\uFF1A");
        label.setBounds(23, 64, 77, 30);
        panel.add(label);
        label.setFont(new Font("����", Font.PLAIN, 15));

        JLabel label_3 = new JLabel("\u683C\u5F0F\uFF1A2015-06-15");
        label_3.setBounds(245, 137, 115, 15);
        panel.add(label_3);
        label_3.setVisible(false);

        tiaojian = new JComboBox();
        tiaojian.setBounds(108, 69, 129, 21);
        panel.add(tiaojian);
        tiaojian.setModel(new DefaultComboBoxModel(new String[]{"\u8BA2\u5355\u53F7", "\u98DF\u54C1\u7C7B\u522B", "\u98DF\u54C1\u540D\u79F0", "\u65F6\u95F4", "\u7ECF\u624B\u4EBA", "\u64CD\u4F5C\u5458"}));
        tiaojian.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (tiaojian.getSelectedItem().equals("ʱ��")) {
                    label_3.setVisible(true);
                } else label_3.setVisible(false);
            }
        });

        JLabel label_1 = new JLabel("\u67E5\u8BE2\u65B9\u5F0F\uFF1A");
        label_1.setBounds(21, 104, 77, 21);
        panel.add(label_1);
        label_1.setFont(new Font("����", Font.PLAIN, 15));

        fangshi = new JComboBox();
        fangshi.setBounds(108, 104, 129, 21);
        panel.add(fangshi);
        fangshi.setModel(new DefaultComboBoxModel(new String[]{"\u7CBE\u786E\u67E5\u8BE2", "\u6A21\u7CCA\u67E5\u8BE2"}));

        JLabel label_2 = new JLabel("\u503C\uFF1A");
        label_2.setBounds(44, 135, 54, 18);
        panel.add(label_2);
        label_2.setFont(new Font("����", Font.PLAIN, 15));

        message = new JTextField();
        message.setBounds(108, 134, 129, 21);
        panel.add(message);
        message.setColumns(10);

        JButton button = new JButton("\u786E\u5B9A");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                getmessage();
                repaint();
            }
        });
        button.setBounds(264, 72, 93, 39);
        panel.add(button);


        JScrollPane scrollPane1 = new JScrollPane();
        String[] columnNames = {"������", "��Ʒ����", "��Ʒ����", "������", "����Ա", "ʱ��", "�۸�", "����"};
        columnNameV = new Vector<String>();
        for (int column = 0; column < columnNames.length; column++) {
            columnNameV.add(columnNames[column]);
        }

        tableValueV = new Vector<Vector<String>>();


        tableModel = new DefaultTableModel(tableValueV,
                columnNameV);
        table = new JTable(tableModel);
        table.setRowSorter(new TableRowSorter<DefaultTableModel>(tableModel));
        scrollPane1.setViewportView(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableColumnModel tableColumnModel = table.getColumnModel();
        ListSelectionModel listSelectionModel = tableColumnModel
                .getSelectionModel();
        add(scrollPane1);
        scrollPane1.setBounds(43, 40, 552, 300);
        //table.setBounds(0, 0, 438, 250);
        //add(table);
        TableColumnModel cm = table.getColumnModel();
        for (int i = 0; i < 8; i++) {
            TableColumn column = cm.getColumn(i);//�õ���i���ж���
            column.setPreferredWidth(67);//�����е���ѡ�������Ϊ preferredWidth��
        }
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
    }

    public void getmessage() {

        table.removeAll();
        tableValueV.removeAllElements();

        String a = leixing.getSelectedItem().toString();
        String b = tiaojian.getSelectedItem().toString();
        String c = fangshi.getSelectedItem().toString();
        String d = message.getText();
        String db, ch1 = "=", ch2 = "", select = "";

        if (a.equals("��ⶩ��")) db = "kucun";
        else db = "chuku";

        if (c.equals("ģ����ѯ")) {
            ch1 = "like";
            ch2 = "%";
        }

        if (b.equals("������")) select = "danhao";
        else if (b.equals("ʳƷ���")) select = "lei";
        else if (b.equals("ʳƷ����")) select = "name";
        else if (b.equals("ʱ��")) select = "time";
        else if (b.equals("������")) select = "jingshouren";
        else if (b.equals("����Ա")) select = "operator";

        try {

            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from " + db + " where " + select + " " + ch1 + " '" + d + ch2 + "'");  // �ύ��ѯ�����صı�񱣴���rs��

            System.out.println("select * from " + db + " where " + select + " " + ch1 + " '" + d + ch2 + "'");
            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�

                Vector<String> rowV = new Vector<String>();
                rowV.add(rs.getString("danhao"));
                rowV.add(rs.getString("lei"));
                rowV.add(rs.getString("name"));
                rowV.add(rs.getString("jingshouren"));
                rowV.add(rs.getString("operator"));
                rowV.add(rs.getString("time"));
                rowV.add(rs.getString("price"));
                rowV.add(rs.getString("num"));
                tableValueV.add(rowV);
                System.out.println("yes");
            }

            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLExceptionss
            System.out.println(sql_e);
        }
        tableModel.setDataVector(tableValueV, columnNameV);
        int rowCount = table.getRowCount();
        table.getSelectionModel().setSelectionInterval(rowCount - 1, rowCount - 1);
        Rectangle rect = table.getCellRect(rowCount - 1, 0, true);
        table.scrollRectToVisible(rect);
        TableColumnModel cm = table.getColumnModel();
        for (int i = 0; i < 8; i++) {
            TableColumn column = cm.getColumn(i);//�õ���i���ж���
            column.setPreferredWidth(67);//�����е���ѡ�������Ϊ preferredWidth��
        }
    }
}
