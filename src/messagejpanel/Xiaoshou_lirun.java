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
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Xiaoshou_lirun extends JPanel {

    /**
     * Create the panel.
     */
    String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

    String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

    public Xiaoshou_lirun() {

        setBackground(Color.WHITE);
        setBounds(197, 0, 637, 570);
        setLayout(null);
        init();

    }

    public void init() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 1; i <= 12; i++) {
            dataset.addValue(0, "���۶�", i + "");
            dataset.addValue(0, "֧��", i + "");
        }


        int money_get = 0;
        int money_put = 0;
        int money_sum = 0;
        try {//����
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from xiaoshou_mounth where time like '" + df.format(new Date()) + "%'");  // �ύ��ѯ�����صı�񱣴���rs��


            System.out.println(rs.getRow());
            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                money_get += Integer.parseInt(rs.getString("money"));
                String mounth = rs.getString("time").substring(5, 7);
                if (mounth.charAt(0) == '0') mounth = mounth.substring(1, 2);
                dataset.addValue(Integer.parseInt(rs.getString("money")), "���۶�", mounth);
                System.out.println(rs.getString("time") + "haha");
            }

            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLException
            System.out.println("xiaoshoumounth" + sql_e);
        }

        try {//֧��
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from ruku_mounth where time like '" + df.format(new Date()) + "%'");  // �ύ��ѯ�����صı�񱣴���rs��


            System.out.println(rs.getRow());
            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                money_put += Integer.parseInt(rs.getString("money"));
                String mounth = rs.getString("time").substring(5, 7);
                if (mounth.charAt(0) == '0') mounth = mounth.substring(1, 2);
                dataset.addValue(Integer.parseInt(rs.getString("money")), "֧��", mounth);
                System.out.println(rs.getString("time") + "haha");
            }

            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLException
            System.out.println("xiaoshoumounth" + sql_e);
        }

        // �������
        for (int i = 1; i <= 12; i++) {
            //lirun
            try {//����
                Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

                Statement s = con.createStatement();  // Statement�������ύSQL���
                Statement s1 = con.createStatement();  // Statement�������ύSQL���

                ResultSet rs = s.executeQuery("select * from ruku_mounth where time like '" + df.format(new Date()) + "-" + (i / 10 == 0 ? "0" + i : i) + "%'");  // �ύ��ѯ�����صı�񱣴���rs��
                ResultSet rs1 = s1.executeQuery("select * from xiaoshou_mounth where time like '" + df.format(new Date()) + "-" + (i / 10 == 0 ? "0" + i : i) + "%'");

                int get = 0, put = 0, sum = 0;
                while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                    put += Integer.parseInt(rs.getString("money"));
                }
                while (rs1.next()) {
                    get += Integer.parseInt(rs1.getString("money"));
                }
                dataset.addValue(get - put, "����", i + "");
                money_sum += get - put;

                s.close();     // �ͷ�Statement����
                con.close();   // �رյ�MySQL������������
            } catch (SQLException sql_e) {     // ����SQLException
                System.out.println("lirun" + sql_e);
            }

        }


        JFreeChart chart = ChartFactory.createLineChart("ʳƷ��˾������", // �����������
                "",// X��ı�ǩ
                "��Ԫ��",// Y��ı�ǩ
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
        for (int i = 0; i < 3; i++) {
            lineAndShapeRenderer.setSeriesShape(i, shape);
            lineAndShapeRenderer.setSeriesShapesVisible(i, true);
        }


        //��D��Ŀ¼������ͼƬ
        File file = new File("chart1.jpg");
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ʹ��ChartFrame������ʾͼ��

        ChartPanel panel = new ChartPanel(chart);
        panel.setVisible(true);
        panel.setBounds(0, 0, 637, 532);
        add(panel);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.LIGHT_GRAY);
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel.setBounds(136, 542, 150, 28);
        lblNewLabel.setText("�����۶" + money_get + "Ԫ");
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("��֧����" + money_put + "Ԫ");
        lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(286, 542, 150, 28);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("������" + money_sum + "Ԫ");
        lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(436, 542, 156, 38);
        add(lblNewLabel_2);

        int d = 0, money = money_put + money_get;
        while (money / 10 != 0) {
            d++;
            money /= 10;
        }
        NumberTickUnit unit = new NumberTickUnit(Math.pow(10, d));//���ñ߾�
        numberAxis.setTickUnit(unit);

    }
}
