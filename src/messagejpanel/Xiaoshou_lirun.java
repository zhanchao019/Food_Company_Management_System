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
    String JDriver = "com.mysql.jdbc.Driver";  // MySQL提供的JDBC驱动，要保证它在CLASSPATH里可见

    String conURL = "jdbc:mysql://localhost/" + Test.database;  // 本地计算机上的MySQL数据库Company的URL

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
            dataset.addValue(0, "销售额", i + "");
            dataset.addValue(0, "支出", i + "");
        }


        int money_get = 0;
        int money_put = 0;
        int money_sum = 0;
        try {//收入
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // 连接数据库

            Statement s = con.createStatement();  // Statement类用来提交SQL语句

            ResultSet rs = s.executeQuery("select * from xiaoshou_mounth where time like '" + df.format(new Date()) + "%'");  // 提交查询，返回的表格保存在rs中


            System.out.println(rs.getRow());
            while (rs.next()) {  // ResultSet指针指向下一个“行”
                money_get += Integer.parseInt(rs.getString("money"));
                String mounth = rs.getString("time").substring(5, 7);
                if (mounth.charAt(0) == '0') mounth = mounth.substring(1, 2);
                dataset.addValue(Integer.parseInt(rs.getString("money")), "销售额", mounth);
                System.out.println(rs.getString("time") + "haha");
            }

            s.close();     // 释放Statement对象
            con.close();   // 关闭到MySQL服务器的连接
        } catch (SQLException sql_e) {     // 都是SQLException
            System.out.println("xiaoshoumounth" + sql_e);
        }

        try {//支出
            Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // 连接数据库

            Statement s = con.createStatement();  // Statement类用来提交SQL语句

            ResultSet rs = s.executeQuery("select * from ruku_mounth where time like '" + df.format(new Date()) + "%'");  // 提交查询，返回的表格保存在rs中


            System.out.println(rs.getRow());
            while (rs.next()) {  // ResultSet指针指向下一个“行”
                money_put += Integer.parseInt(rs.getString("money"));
                String mounth = rs.getString("time").substring(5, 7);
                if (mounth.charAt(0) == '0') mounth = mounth.substring(1, 2);
                dataset.addValue(Integer.parseInt(rs.getString("money")), "支出", mounth);
                System.out.println(rs.getString("time") + "haha");
            }

            s.close();     // 释放Statement对象
            con.close();   // 关闭到MySQL服务器的连接
        } catch (SQLException sql_e) {     // 都是SQLException
            System.out.println("xiaoshoumounth" + sql_e);
        }

        // 添加数据
        for (int i = 1; i <= 12; i++) {
            //lirun
            try {//利润
                Connection con = DriverManager.getConnection(conURL, Test.mysqlname, Test.mysqlpassword);  // 连接数据库

                Statement s = con.createStatement();  // Statement类用来提交SQL语句
                Statement s1 = con.createStatement();  // Statement类用来提交SQL语句

                ResultSet rs = s.executeQuery("select * from ruku_mounth where time like '" + df.format(new Date()) + "-" + (i / 10 == 0 ? "0" + i : i) + "%'");  // 提交查询，返回的表格保存在rs中
                ResultSet rs1 = s1.executeQuery("select * from xiaoshou_mounth where time like '" + df.format(new Date()) + "-" + (i / 10 == 0 ? "0" + i : i) + "%'");

                int get = 0, put = 0, sum = 0;
                while (rs.next()) {  // ResultSet指针指向下一个“行”
                    put += Integer.parseInt(rs.getString("money"));
                }
                while (rs1.next()) {
                    get += Integer.parseInt(rs1.getString("money"));
                }
                dataset.addValue(get - put, "利润", i + "");
                money_sum += get - put;

                s.close();     // 释放Statement对象
                con.close();   // 关闭到MySQL服务器的连接
            } catch (SQLException sql_e) {     // 都是SQLException
                System.out.println("lirun" + sql_e);
            }

        }


        JFreeChart chart = ChartFactory.createLineChart("食品公司年利润", // 主标题的名称
                "",// X轴的标签
                "金额（元）",// Y轴的标签
                dataset, // 图标显示的数据集合
                PlotOrientation.VERTICAL, // 图像的显示形式（水平或者垂直）
                true,// 是否显示子标题
                true,// 是否生成提示的标签
                false); // 是否生成URL链接
        // 处理图形上的乱码
        // 处理主标题的乱码
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 18));
        // 处理子标题乱码
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));
        // 获取图表区域对象
        CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
        // 获取X轴的对象
        CategoryAxis categoryAxis = (CategoryAxis) categoryPlot.getDomainAxis();
        // 获取Y轴的对象
        NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
        // 处理X轴上的乱码
        categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理X轴外的乱码
        categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上的乱码
        numberAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));

        // 处理Y轴外的乱码
        numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 15));
        // 处理Y轴上显示的刻度，以10作为1格
        numberAxis.setAutoTickUnitSelection(false);

        // 获取绘图区域对象
        LineAndShapeRenderer lineAndShapeRenderer = (LineAndShapeRenderer) categoryPlot
                .getRenderer();
        // 在图形上显示数字
        lineAndShapeRenderer
                .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        lineAndShapeRenderer.setBaseItemLabelsVisible(true);
        lineAndShapeRenderer
                .setBaseItemLabelFont(new Font("宋体", Font.BOLD, 15));
        // 在图形上添加转折点（使用小矩形显示）
        Rectangle shape = new Rectangle(10, 10);
        for (int i = 0; i < 3; i++) {
            lineAndShapeRenderer.setSeriesShape(i, shape);
            lineAndShapeRenderer.setSeriesShapesVisible(i, true);
        }


        //在D盘目录下生成图片
        File file = new File("chart1.jpg");
        try {
            ChartUtilities.saveChartAsJPEG(file, chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 使用ChartFrame对象显示图像

        ChartPanel panel = new ChartPanel(chart);
        panel.setVisible(true);
        panel.setBounds(0, 0, 637, 532);
        add(panel);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBackground(Color.LIGHT_GRAY);
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
        lblNewLabel.setBounds(136, 542, 150, 28);
        lblNewLabel.setText("总销售额：" + money_get + "元");
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("总支出：" + money_put + "元");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(286, 542, 150, 28);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("总利润：" + money_sum + "元");
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
        lblNewLabel_2.setBounds(436, 542, 156, 38);
        add(lblNewLabel_2);

        int d = 0, money = money_put + money_get;
        while (money / 10 != 0) {
            d++;
            money /= 10;
        }
        NumberTickUnit unit = new NumberTickUnit(Math.pow(10, d));//设置边距
        numberAxis.setTickUnit(unit);

    }
}
