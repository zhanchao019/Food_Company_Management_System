package messagejpanel;

import company.Test;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Xiaoshou_num extends JPanel {

    /**
     * Create the panel.
     */
    String JDriver = "com.mysql.jdbc.Driver";  // MySQL�ṩ��JDBC������Ҫ��֤����CLASSPATH��ɼ�

    String conURL = "jdbc:mysql://localhost/" + Test.database;  // ���ؼ�����ϵ�MySQL���ݿ�Company��URL

    public Xiaoshou_num() {
        setBackground(Color.WHITE);
        setBounds(197, 0, 637, 570);
        setLayout(null);
        init();
    }

    public void init() {
        //��������Դ
        DefaultPieDataset mDataset = new DefaultPieDataset();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        try {
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // �������ݿ�

            Statement s = con.createStatement();  // Statement�������ύSQL���
            Statement s1 = con.createStatement();  // Statement�������ύSQL���

            ResultSet rs = s.executeQuery("select * from goods;");  // �ύ��ѯ�����صı�񱣴���rs��

            while (rs.next()) {  // ResultSetָ��ָ����һ�����С�
                ResultSet rs1 = s1.executeQuery("select * from chuku where lei='" + rs.getString("lei") + "' and name = '" + rs.getString("name") + "'");  // �ύ��ѯ�����صı�񱣴���rs��
                int num = 0;
                while (rs1.next()) {
                    if (rs1.getString("time").substring(0, 4).equals(df.format(new Date()))) {
                        num += Integer.parseInt(rs1.getString("num"));
                    }
                }
                mDataset.setValue(rs.getString("lei") + ":" + rs.getString("name"), new Double(num));
            }

            s.close();     // �ͷ�Statement����
            con.close();   // �رյ�MySQL������������
        } catch (SQLException sql_e) {     // ����SQLException
            System.out.println(sql_e);
        }

        //����ͼ��  
        JFreeChart mChart = ChartFactory.createPieChart("��Ŀ���ȷֲ�", mDataset, true, true, false);
        //����ͼ�����  
        mChart.setTitle(new TextTitle("��Ŀ״̬�ֲ�", new Font("����", Font.CENTER_BASELINE, 20)));
        //����Legend����  
        mChart.getLegend().setItemFont(new Font("����", Font.ROMAN_BASELINE, 15));

        PiePlot mPiePlot = (PiePlot) mChart.getPlot();
        //��Ĭ�Ϸ�ʽ��ʾ�ٷֱ�  
        //mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));  
        // ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С�������λ  
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
        // �ײ�ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ����   
        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
        //���ñ�ͼ��ǩ����  
        mPiePlot.setLabelFont(new Font("����", Font.PLAIN, 15));
        //���ö�����ʾͼ��  
        ChartPanel panel = new ChartPanel(mChart);
        panel.setVisible(true);
        panel.setBounds(0, 0, 637, 532);
        add(panel);
    }
}
