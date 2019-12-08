package messagejpanel;

import company.Test;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Xiaoshou_day extends JPanel {

    /**
     * Create the panel.
     */

    String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

    String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

    int day;
    int mounth;
    int year;
    ChartPanel panel;
    String now;
    JLabel lblNewLabel;

    public Xiaoshou_day() {


        setBackground(Color.WHITE);
        setBounds(197, 0, 637, 570);
        setLayout(null);
        init();
    }

    public void init() {

        int[][] d = {{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());

        lblNewLabel = new JLabel("New label");
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel.setBounds(260, 495, 176, 28);
        add(lblNewLabel);


        getchart(date);
        add(panel);


        day = Integer.parseInt(date.substring(8, 10));
        mounth = Integer.parseInt(date.substring(5, 7));
        year = Integer.parseInt(date.substring(0, 4));


        JLabel lblNewLabel_1 = new JLabel(date);
        lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(260, 0, 134, 25);
        add(lblNewLabel_1);

        JButton button_1 = new JButton("\u540E\u4E00\u5929");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                day++;
                int y = isleapyear(year);
                if (day > d[y][mounth - 1]) {
                    day = 1;
                    mounth++;
                }
                if (mounth > 12) {
                    mounth = 1;
                    year++;
                }
                now = year + "-" + mounth + "-" + day / 10 + day % 10;
                getchart(now);
                lblNewLabel_1.setText(now);

                if (now.equals(date)) {
                    button_1.setEnabled(false);
                }
            }
        });
        button_1.setBounds(357, 537, 93, 23);
        add(button_1);
        button_1.setEnabled(false);

        JButton button = new JButton("\u524D\u4E00\u5929");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                day--;
                int y = isleapyear(year);
                if (day == 0) {
                    mounth--;
                    day = d[y][mounth - 1];
                }
                if (mounth == 0) {
                    year--;
                    mounth = 12;

                }
                now = year + "-" + mounth + "-" + day / 10 + day % 10;
                getchart(now);
                button_1.setEnabled(true);
                lblNewLabel_1.setText(now);
            }
        });
        button.setBounds(225, 537, 93, 23);
        add(button);


    }

    public int isleapyear(int y) {
        if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) return 1;
        return 0;
    }

    int money;

    public void getchart(String date) {

        if (panel != null) remove(panel);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from xiaoshou_day where time like '" + date + "%'");  // �ύ��ѯ�����صı�񱣴���rs��

            money = 0;
            System.out.println(rs.getRow());
            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                money += Integer.parseInt(rs.getString("money"));
                dataset.addValue(Integer.parseInt(rs.getString("money")), "���۶�", rs.getString("lei") + ":" + rs.getString("name"));
                System.out.println(rs.getString("time"));
            }
            lblNewLabel.setText("�������۽�" + money + "Ԫ");
            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLException
            System.out.println("xiaoshou" + sql_e);
        }


        // �������

        JFreeChart chart = ChartFactory.createLineChart("ʳƷ��˾�����۶�", // �����������
                "",// X��ı�ǩ
                "���",// Y��ı�ǩ
                dataset, // ͼ����ʾ�����ݼ���
                PlotOrientation.VERTICAL, // ͼ�����ʾ��ʽ��ˮƽ���ߴ�ֱ��
                true,// �Ƿ���ʾ�ӱ���
                true,// �Ƿ�������ʾ�ı�ǩ
                false); // �Ƿ�����URL����
        // ����ͼ���ϵ�����
        // ���������������
        chart.getTitle().setFont(new Font("����", Font.BOLD, 18));
        // �����ӱ�������
        chart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));
        // ��ȡͼ���������
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        // ��ȡX��Ķ���
        CategoryAxis categoryAxis = (CategoryAxis) categoryPlot.getDomainAxis();
        // ��ȡY��Ķ���
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        // ����X���ϵ�����
        categoryAxis.setTickLabelFont(new Font("����", Font.BOLD, 15));
        // ����X���������
        categoryAxis.setLabelFont(new Font("����", Font.BOLD, 15));
        // ����Y���ϵ�����
        numberAxis.setTickLabelFont(new Font("����", Font.BOLD, 15));

        // ����Y���������
        numberAxis.setLabelFont(new Font("����", Font.BOLD, 15));
        // ����Y������ʾ�Ŀ̶ȣ���10��Ϊ1��
        numberAxis.setAutoTickUnitSelection(false);

        // ��ȡ��ͼ�������
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot
                .getRenderer();
        // ��ͼ������ʾ����
        lineAndShapeRenderer
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer
                .setBaseItemLabelFont(new Font("����", Font.BOLD, 15));
        // ��ͼ�������ת�۵㣨ʹ��С������ʾ��
        Rectangle shape = new Rectangle(10, 10);
        lineAndShapeRenderer.setSeriesShape(0, shape);
        lineAndShapeRenderer.setSeriesShapesVisible(0, true);

        //��D��Ŀ¼������ͼƬ
        File file = new File("chart1.jpg");
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ʹ��ChartFrame������ʾͼ��

        panel = new ChartPanel(chart);
        panel.setVisible(true);
        panel.setBounds(0, 36, 637, 449);
        add(panel);
        repaint();

        int d = 0, m = money;
        while (m / 10 != 0) {
            d++;
            m /= 10;
        }
        NumberTickUnit unit = new NumberTickUnit(Math.pow(10, d));//���ñ߾�
        numberAxis.setTickUnit(unit);
    }
}
