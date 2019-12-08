package jframe;


import company.Test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	int windowwidth = 850;
	int windowheight = 680;
	public static JTextField yonghu;
	public static JPasswordField passwordField;
	public static JButton denglu;
	public static JComboBox limite;
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/

	/**
	 * Create the frame.
	 */
	/*public void paint(Graphics g) {
		g.setColor(Color.red);
        // ���߶�
        //g.drawLine(5, 5, 20, 100);
        // ����
        //g.drawLine(20, 20, 20, 20);

        // ����ͨ���ο�
        //g.drawRect(30, 5, 100, 100);
        // �����ͨ����
		
        g.fillRect(140, 5, 100, 100);
		Graphics2D g2d = (Graphics2D) g;// ��ȡ2D��ͼ������    
    	Composite composite = g2d.getComposite();// ���ݺϳ�ģʽ   // ���û�ͼʹ��͸���ϳɹ���  
    	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.1f));  
    	g2d.fillRect(0, 0, 100, 100);// ʹ�õ�ǰ��ɫ�����οռ�   
    	g2d.setComposite(composite);// // �ָ�ԭ�кϳ�ģʽ  
    	//super.paintComponents(g2d);// ִ�г����������Ʒ���  }
	}
	*/
	public Login() {

		setResizable(false);
		setTitle("\u767B\u9646");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(windowwidth, windowheight);
		setTitle("��½");
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().height;
		setLocation((screenWidth - windowwidth) / 2, (screenHeight - windowheight) / 2);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 834, 642);
		contentPane.add(panel);
		panel.setLayout(null);


		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(217, 229, 454, 241);
		panel.add(panel_1);
		panel_1.setLayout(null);

		limite = new JComboBox();
		limite.setToolTipText("");
		limite.setModel(new DefaultComboBoxModel(new String[]{"\u7BA1\u7406\u5458", "\u5458\u5DE5"}));
		limite.setBounds(157, 22, 93, 29);
		panel_1.add(limite);


		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(81, 71, 72, 21);
		panel_1.add(label);
		label.setFont(new Font("����", Font.PLAIN, 18));

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(95, 120, 54, 21);
		panel_1.add(label_1);
		label_1.setFont(new Font("����", Font.PLAIN, 18));

		yonghu = new JTextField();
		yonghu.setBounds(159, 73, 144, 21);
		panel_1.add(yonghu);
		yonghu.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(159, 122, 144, 21);
		panel_1.add(passwordField);
		passwordField.setDropMode(DropMode.INSERT);
		passwordField.setToolTipText("\u8BF7\u8F93\u5165\u5BC6\u7801");

		JLabel log = new JLabel("New label");
		log.setBounds(0, 0, 455, 241);
		ImageIcon ico = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/picture/4.jpg")));
		ico.getImage();
		Image img2 = ico.getImage().getScaledInstance(log.getWidth(), log.getHeight(), Image.SCALE_DEFAULT);
		ico = new ImageIcon(img2);

		denglu = new JButton("\u767B\u9646");
		denglu.addActionListener(new Test());
		denglu.setFont(new Font("����", Font.PLAIN, 18));
		denglu.setBounds(77, 184, 93, 29);
		panel_1.add(denglu);

		JButton chongzhi = new JButton("\u91CD\u7F6E");
		chongzhi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				yonghu.setText("");
				passwordField.setText("");
			}
		});
		chongzhi.setFont(new Font("����", Font.PLAIN, 18));
		chongzhi.setBounds(235, 184, 93, 29);
		panel_1.add(chongzhi);
		log.setIcon(ico);
		panel_1.add(log);


		JLabel bluep = new JLabel("");
		bluep.setForeground(new Color(0, 0, 128));
		bluep.setBounds(0, 0, 834, 642);
		ImageIcon prince = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/picture/3.jpg")));
		prince.getImage();
		Image img1 = prince.getImage().getScaledInstance(bluep.getWidth(), bluep.getHeight(), Image.SCALE_DEFAULT);
		prince = new ImageIcon(img1);

		JLabel lblNewLabel = new JLabel("\u98DF\u54C1\u516C\u53F8\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setBackground(new Color(0, 206, 209));
		lblNewLabel.setFont(new Font("���Ĳ���", Font.BOLD, 58));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(203, 113, 515, 74);
		panel.add(lblNewLabel);
		bluep.setIcon(prince);
		panel.add(bluep);


	}
}
