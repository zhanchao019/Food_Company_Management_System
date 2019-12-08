package titlejpanel;

import jframe.Main;

import javax.swing.*;
import java.awt.*;


public class Message extends JPanel {

	public static JLabel label_2;
	public static JLabel label_3;
	public static JLabel label_4;

	public Message() {
		setBackground(new Color(230, 230, 250));
		setBounds(0, 0, 198, 571);
		setVisible(true);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(0, 0, 198, 217);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{196, 0};
		gbl_panel_2.rowHeights = new int[]{68, 34, 39, 40, 36, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel_2);
		add(panel);

		JLabel label_1 = new JLabel("\u57FA\u672C\u4FE1\u606F\u7BA1\u7406");
		label_1.setFont(new Font("����", Font.PLAIN, 18));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 0);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 0;
		panel.add(label_1, gbc_label_5);

		label_2 = new JLabel("  \u4F9B\u5E94\u5546\u57FA\u672C\u4FE1\u606F");
		label_2.setFont(new Font("����", Font.PLAIN, 15));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel.add(label_2, gbc_label_1);
		label_2.addMouseListener(new Mouselistener(label_2));

		label_3 = new JLabel("  \u5458\u5DE5\u57FA\u672C\u4FE1\u606F");
		label_3.setFont(new Font("����", Font.PLAIN, 15));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 2;
		panel.add(label_3, gbc_label_2);
		label_3.addMouseListener(new Mouselistener(label_3));

		label_4 = new JLabel("  \u5546\u54C1\u4FE1\u606F");
		label_4.setFont(new Font("����", Font.PLAIN, 15));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panel.add(label_4, gbc_label_3);
		label_4.addMouseListener(new Mouselistener(label_4));


		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(29, 333, 168, 238);
		ImageIcon prince = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/picture/6.png")));
		prince.getImage();
		Image img = prince.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT);
		prince = new ImageIcon(img);
		lblNewLabel.setIcon(prince);
		add(lblNewLabel);
	}

}
