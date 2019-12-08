package jframe;

import company.Buttonmouse;
import company.Test;
import company.Testmysql;
import messagejpanel.*;
import titlejpanel.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Main extends JFrame {

    public static Kucun_ruku rukuframe;
    private JPanel contentPane;
    int windowwidth = 850;
    int windowheight = 680;
    static Main frame;
    public static Kucun_chuku kucun_chuku;
    public static JLabel exit;
    public static JLabel control;
    public static Message_goods message_goods;
    public static Kucun_Sykucun kucun_sykucun;
    public static Message_sponsor message_sponsor;
    public static Xiaoshou_day xiaoshou_day;
    public static Xiaoshou_mounth xiaoshou_mounth;
    public static Xiaoshou_lirun xiaoshou_lirun;
    public static Xiaoshou_num xiaoshou_num;
    public static Dingdan_ruku dingdan_ruku;
    public static Dingdan_chuku dingdan_chuku;
    public static Dingdan_chaxun dingdan_chaxun;
    public static Message_employee message_employee;

    public static HashSet leiset;
    public static JPanel panel_4;
    public static JLabel lblNewLabel_3;
    public static Log log;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new Main();
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
    public Main() {

        initlei();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ʳƷ��˾����ϵͳ");
        setSize(windowwidth, windowheight);
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((screenWidth - windowwidth) / 2, (screenHeight - windowheight) / 2);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 230, 250));
        panel.setBounds(0, 0, 834, 642);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        panel_1.setBounds(0, 619, 834, 23);
        panel.add(panel_1);
        panel_1.setLayout(new GridLayout(0, 4, 0, 0));

        JLabel lblNewLabel = new JLabel("\u98DF\u54C1\u516C\u53F8\u7BA1\u7406\u7CFB\u7EDF");
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u6B22\u8FCE\u4F7F\u7528\uFF01");
        lblNewLabel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.add(lblNewLabel_1);

        JLabel label = new JLabel("\u64CD\u4F5C\u5458\uFF1A" + Testmysql.Name);
        label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.add(label);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
        lblNewLabel_2.setText("��ǰ���ڣ�" + df.format(new Date()));
        panel_1.add(lblNewLabel_2);
        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
		
		/*JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 834, 23);
		panel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u57FA\u672C\u4FE1\u606F\u7BA1\u7406");
		mnNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		mnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JMenu menu = new JMenu("\u5165\u5E93\u7BA1\u7406");
		menu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(menu);
		
		JMenu menu_1 = new JMenu("\u9500\u552E\u7BA1\u7406");
		menu_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("\u9000\u8D27\u7BA1\u7406");
		menu_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(menu_2);
		
		JMenu menu_3 = new JMenu("\u5E93\u5B58\u7BA1\u7406");
		menu_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(menu_3);
		
		JMenu menu_4 = new JMenu("\u7CFB\u7EDF\u7EF4\u62A4");
		menu_4.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(menu_4);
		
		JMenu menu_5 = new JMenu("\u5E2E\u52A9");
		menu_5.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(menu_5);
		*/


        panel_4 = new JPanel();
        panel_4.setBackground(new Color(255, 255, 255));
        panel_4.setBounds(0, 49, 834, 572);
        panel.add(panel_4);
        panel_4.setLayout(null);
		
		/*JTextArea txtOut=new JTextArea();
		txtOut.setBackground(new Color(255, 255, 255));
		txtOut.setLineWrap(true);
		//txtOut.setPreferredSize(new Dimension(x*19+30,y*3-25));//ȥ����һ��
		JScrollPane scrollPaneOut=new JScrollPane(txtOut);
		scrollPaneOut.setSize(300, 200);
		scrollPaneOut.setLocation(100, 100);
		//scrollPaneOut.setPreferredSize(new Dimension(100,50));//�����һ��
		panel_4.add(scrollPaneOut);
		*/

        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(230, 230, 250));
        toolBar.setBounds(0, 0, 614, 50);
        panel.add(toolBar);

        Message panel1 = new Message();
        panel_4.add(panel1);
        panel1.setVisible(false);

        Kucun panel2 = new Kucun();
        panel_4.add(panel2);
        panel2.setVisible(false);

        Xiaoshou panel3 = new Xiaoshou();
        panel_4.add(panel3);
        panel3.setVisible(false);

        Dingdan panel4 = new Dingdan();
        panel_4.add(panel4);
        panel4.setVisible(false);

        log = new Log();
        panel_4.add(log);
        log.setVisible(false);


        JButton button = new JButton("\u57FA\u672C\u4FE1\u606F\u7BA1\u7406");
        button.setBackground(Color.WHITE);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panel1.setVisible(true);
                panel2.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(false);

            }
        });
        button.addMouseListener(new Buttonmouse(button));

        JButton button_7 = new JButton("\u9996\u9875");
        button_7.setBackground(Color.WHITE);
        button_7.addMouseListener(new Buttonmouse(button_7));
        toolBar.add(button_7);
        toolBar.add(button);


        JButton button_1 = new JButton("\u5E93\u5B58\u7BA1\u7406");
        button_1.setBackground(Color.WHITE);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panel1.setVisible(false);
                ;
                panel2.setVisible(true);
                panel3.setVisible(false);
                panel4.setVisible(false);
            }
        });
        button_1.addMouseListener(new Buttonmouse(button_1));
        toolBar.add(button_1);


        JButton button_2 = new JButton("\u9500\u552E\u7BA1\u7406");
        button_2.setBackground(new Color(255, 255, 255));
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panel1.setVisible(false);
                ;
                panel2.setVisible(false);
                panel3.setVisible(true);
                panel4.setVisible(false);
            }
        });
        button_2.addMouseListener(new Buttonmouse(button_2));
        if (Login.limite.getSelectedItem().equals("����Ա")) toolBar.add(button_2);


        JButton button_3 = new JButton("\u8BA2\u5355\u7BA1\u7406");
        button_3.setBackground(Color.WHITE);
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panel1.setVisible(false);
                panel2.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(true);
            }
        });
        button_3.addMouseListener(new Buttonmouse(button_3));
        toolBar.add(button_3);

        JButton btnNewButton = new JButton("\u65E5\u5FD7");
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.addMouseListener(new Buttonmouse(btnNewButton));
        toolBar.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panel1.setVisible(false);
                panel2.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(false);
                lblNewLabel_3.setVisible(false);
                panel_4.remove(log);
                log = new Log();
                panel_4.add(log);
                log.setVisible(true);
                if (Main.message_goods != null) Main.message_goods.setVisible(false);
                if (Main.kucun_chuku != null) Main.kucun_chuku.setVisible(false);
                if (Main.kucun_sykucun != null) Main.kucun_sykucun.setVisible(false);
                if (Main.xiaoshou_day != null) Main.xiaoshou_day.setVisible(false);
                if (Main.xiaoshou_mounth != null) Main.xiaoshou_mounth.setVisible(false);
                if (Main.xiaoshou_lirun != null) Main.xiaoshou_lirun.setVisible(false);
                if (Main.xiaoshou_num != null) Main.xiaoshou_num.setVisible(false);
                if (Main.dingdan_ruku != null) Main.dingdan_ruku.setVisible(false);
                if (Main.dingdan_chuku != null) Main.dingdan_chuku.setVisible(false);
                if (Main.message_sponsor != null) Main.message_sponsor.setVisible(false);
                if (Main.dingdan_chaxun != null) Main.dingdan_chaxun.setVisible(false);
                if (Main.message_employee != null) Main.message_employee.setVisible(false);
            }
        });


        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(0, 0, 834, 572);
        ImageIcon prince = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/picture/5.jpg")));
        prince.getImage();
        Image img = prince.getImage().getScaledInstance(lblNewLabel_3.getWidth(), lblNewLabel_3.getHeight(), Image.SCALE_DEFAULT);
        prince = new ImageIcon(img);
        lblNewLabel_3.setIcon(prince);
        panel_4.add(lblNewLabel_3);


        exit = new JLabel("\u9000\u51FA");
        exit.setFont(new Font("����", Font.PLAIN, 15));
        exit.setBounds(786, 13, 38, 26);
        panel.add(exit);
        exit.addMouseListener(new Mouselistener(exit));

        control = new JLabel("\u7BA1\u7406");
        control.setFont(new Font("����", Font.PLAIN, 15));
        control.setBounds(738, 19, 38, 15);
        if (Login.limite.getSelectedItem().equals("����Ա")) panel.add(control);
        control.addMouseListener(new Mouselistener(control));

        button_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                panel1.setVisible(false);
                ;
                panel2.setVisible(false);
                panel3.setVisible(false);
                panel4.setVisible(false);
                lblNewLabel_3.setVisible(true);
                log.setVisible(false);
            }
        });
    }

    private void seteditable(boolean b) {
        // TODO Auto-generated method stub

    }


    public void initlei() {
        leiset = new HashSet();
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

            ResultSet rs = s.executeQuery("select * from fenlei;");  // �ύ��ѯ�����صı�񱣴���rs��

            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                leiset.add(rs.getString("lei"));
            }
            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLException
            System.out.println(sql_e);
        }
    }
}
