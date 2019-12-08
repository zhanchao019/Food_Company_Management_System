package titlejpanel;

import company.Test;
import company.Testmysql;
import jframe.Login;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class Log extends JPanel {

	/**
	 * Create the panel.
	 */

	private JTable table;

	DefaultTableModel tableModel;
	Vector<Vector<String>> tableValueV;
	Vector<String> columnNameV;


	public Log() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 834, 572);
		setVisible(true);
		setLayout(null);


		String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

		String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

		try {
			Class.forName(JDriver);
		} catch (ClassNotFoundException cnf_e) {  // ����Ҳ���������
			System.out.println("Driver Not Found: " + cnf_e);
		}

		table = new JTable();
		table.setBounds(0, 0, 438, 250);
		JScrollPane scrollPane1 = new JScrollPane();
		String[] columnNames = {"ʱ��", "����"};
		columnNameV = new Vector<String>();
		for (int column = 0; column < columnNames.length; column++) {
			columnNameV.add(columnNames[column]);
		}

		tableValueV = new Vector<Vector<String>>();
		try {
			Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

			Statement s = con.createStatement();  // Statement�������ύSQL���

			String a = "";
			if (!Login.limite.getSelectedItem().equals("����Ա")) {
				a = "where limite != '����Ա'";
			}
			ResultSet rs = s.executeQuery("select * from news " + a + " order by time DESC");  // �ύ��ѯ�����صı�񱣴���rs��

			while (rs.next()) {  // ResultSetָ��ָ����һ�����С�

				Vector<String> rowV = new Vector<String>();
				rowV.add(rs.getString("time"));
				rowV.add(rs.getString("news"));
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
		scrollPane1.setBounds(110, 47, 633, 443);

		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				table.removeAll();
				tableValueV.removeAllElements();
				tableModel = new DefaultTableModel(tableValueV,
						columnNameV);
				try {
					Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

					Statement s = con.createStatement();  // Statement�������ύSQL���


					String delete = "Delete From news";
					s.executeUpdate(delete);
					repaint();
					s.close();     // �ͷ�Statement����
					con.close();   // �رյ�MySQL������������
				} catch (SQLException sql_e) {     // ����SQLException
					System.out.println(sql_e);
				}
			}
		});
		button.setBounds(389, 515, 93, 23);
		if (Testmysql.limite.equals("����Ա")) add(button);
		//table.setBounds(0, 0, 438, 250);
		//add(table);
		TableColumnModel cm = table.getColumnModel();
		TableColumn column = cm.getColumn(0);//�õ���i���ж���
		column.setPreferredWidth(173);//�����е���ѡ�������Ϊ preferredWidth��
		TableColumn column1 = cm.getColumn(1);//�õ���i���ж���
		column1.setPreferredWidth(440);//�����е���ѡ�������Ϊ preferredWidth��
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
	}
}
