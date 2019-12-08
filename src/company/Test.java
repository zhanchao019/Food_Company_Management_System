package company;

import jframe.Login;
import jframe.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test implements ActionListener {

	public static Login frame;
	public static Main zhu;
	public static String mysqlname = "root";
	public static String mysqlpassword = "8088211a";
	public static String database = "company";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
					frame.setVisible(true);
					//zhu =new Main();
					//zhu.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e1) {
		// TODO Auto-generated method stub
		if (e1.getSource() == Login.denglu) {
			new Testmysql();
		}

	}

}
