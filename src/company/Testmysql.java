package company;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import jframe.Login;
import jframe.Main;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Testmysql {
	public static String Name;
	public static String limite;

	public Testmysql() {
		System.out.println("����ִ��sql����");
		Name = Login.yonghu.getText();
		limite = Login.limite.getSelectedItem().toString();
		String s2 = Login.passwordField.getText();
		String s3 = Login.limite.getSelectedItem().toString();
		String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

		String conURL = "jdbc:mysql://localhost:3306/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

		try {
			Class.forName(JDriver);
		} catch (ClassNotFoundException cnf_e) {  // ����Ҳ���������
			System.out.println("Driver Not Found: " + cnf_e);
		}
		try {

			System.out.println("building connection");


			Connection con = (Connection) DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�
			Connection con1 = (Connection) DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

			Statement s = (Statement) con.createStatement();  // Statement�������ύSQL���
			Statement s1 = (Statement) con1.createStatement();  // Statement�������ύSQL���

			ResultSet rs = s.executeQuery("select * from users ");  // �ύ��ѯ�����صı�񱣴���rs��
			boolean flag = false;
			while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
				if (rs.getString("name").equals(Name) && rs.getString("password").equals(s2) && rs.getString("limite").equals(s3)) {
					flag = true;
					Test.zhu = new Main();
					JOptionPane.showMessageDialog(Test.frame, Name + "��½�ɹ�", "��½�ɹ�", 1);
					Test.frame.dispose();//�����ťʱframe1����,newһ��frame2
					Test.zhu.setVisible(true);

					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
					//System.out.println("insert into news(time,news,limit) values('"+df.format(new Date())+"','"+Name+"����ϵͳ"+"','"+limite+"')");
					String insert = "insert into news(time,news,limite) values('" + df.format(new Date()) + "','" + limite + Name + "����ϵͳ" + "','" + limite + "')";
					s1.executeUpdate(insert);

					s1.close();     // �ͷ�Statement����
					con1.close();   // �رյ�MySQL������������
					break;
				}
			}
			if (!flag) {
				JOptionPane.showMessageDialog(Test.frame, Name + s2 + "�û����������������������", "����", 2);
				Login.yonghu.setText("");
				Login.passwordField.setText("");
				return;
			}
			s.close();
			con.close();
		} catch (SQLException sql_e) {     // ����SQLException
			System.out.println("testmyql  " + sql_e);
		}
		return;
	}
}
