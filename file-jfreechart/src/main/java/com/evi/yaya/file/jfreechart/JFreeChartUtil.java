package com.evi.yaya.file.jfreechart;

import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：TODO
 *
 * @Title: JFreeChartUtil
 * @Package com.evi.yaya.file.jfreechart
 * @See: [] </br>
 * @Copyright: Copyright (c) 2017 </br>
 * @Company: sany huax witsight team by product</br>
 * @author: wangzy
 * @date: 2019/8/13 16:59
 * @version: V1.0
 */
@Slf4j
public class JFreeChartUtil {
    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        params.put("00点-04点", 100);
        params.put("04点-08点", 200);
        params.put("08点-12点", 300);
        params.put("12点-16点", 400);
        params.put("16点-20点", 500);
        params.put("20点-24点", 600);
        Font font = new Font("宋体", Font.BOLD, 12);
        String filePath = "E:\\data\\pdf\\images\\pie.jpg";
        createPieChartAsJPEG("今日放屁次数:时间段分布图", params, font, font, 600, 300, filePath);
    }

    public static Boolean createPieChartAsJPEG(String title, Map<String, Object> params, Font titleFont, Font labelFont, int width, int height, String filePath) {
        log.info("saveChartAsJPEG param={},filePath={}", params, filePath);
        if (null == params) {
            return Boolean.FALSE;
        }
        PieDataset pds = PieDatasetUtil.createPieDataset(params);
        // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
        JFreeChart chart = ChartFactory.createPieChart(title, pds, true, false, false);
        // 设置图片标题的字体
        chart.getTitle().setFont(titleFont);
        // 得到图块,准备设置标签的字体
        PiePlot plot = (PiePlot) chart.getPlot();
        // 设置标签字体
        plot.setLabelFont(labelFont);
        plot.setStartAngle(new Float(3.14f / 2f));
        // 设置plot的前景色透明度
        plot.setForegroundAlpha(0.7f);
        // 设置plot的背景色透明度
        plot.setBackgroundAlpha(0.0f);
        // 设置标签生成器(默认{0})
        // {0}:key {1}:value {2}:百分比 {3}:sum
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}占{2})"));
        // 将内存中的图片写到本地硬盘
        try {
            ChartUtils.saveChartAsJPEG(new File(filePath), chart, width, height);
        } catch (IOException e) {
            log.error("createPieChartAsJPEG IOException e={}", e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static Boolean createRingChartAsPNG(String title, String[] names, int[] values, Font labelFont, int width, int height, String tempPath, String filePath) {
        JFreeChart chart = ChartFactory.createRingChart(title, PieDatasetUtil.createPieDataset(names, values), true, false, false);
        chart.getLegend().setVisible(false);
        // 环形图
        RingPlot ringplot = (RingPlot) chart.getPlot();
        ringplot.setOutlineVisible(false);
        //{2}表示显示百分比
        ringplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
        ringplot.setBackgroundPaint(new Color(253, 253, 253));
        ringplot.setOutlineVisible(false);
        //设置标签样式
        ringplot.setLabelFont(labelFont);
        ringplot.setSimpleLabels(true);
        ringplot.setLabelLinkPaint(Color.WHITE);
        ringplot.setLabelOutlinePaint(Color.WHITE);
        ringplot.setLabelLinksVisible(false);
        ringplot.setLabelShadowPaint(null);
        ringplot.setLabelOutlinePaint(new Color(0, true));
        ringplot.setLabelBackgroundPaint(new Color(0, true));
        ringplot.setLabelPaint(Color.WHITE);
        ringplot.setSectionOutlinePaint(0, Color.WHITE);
        ringplot.setSeparatorsVisible(true);
        ringplot.setSeparatorPaint(Color.WHITE);
        ringplot.setShadowPaint(new Color(253, 253, 253));
        ringplot.setSectionDepth(0.58);
        ringplot.setStartAngle(90);
        ringplot.setDrawingSupplier(new DefaultDrawingSupplier(
                new Paint[]{
                        new Color(134, 212, 222),
                        new Color(174, 145, 195),
                        new Color(255, 162, 195),
                        new Color(249, 163, 86),
                        new Color(119, 173, 195)
                },
                DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
        try {
            FileOutputStream fos_jpg = new FileOutputStream(tempPath);
            ChartUtils.writeChartAsPNG(fos_jpg, chart, width - 5, height);
            //以下由于jfreechart设置背景色后，背景会有留白，直接将目标图片截取
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ChartUtils.writeChartAsPNG(baos, chart, width, height, null);
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
            BufferedImage sub = bi.getSubimage(5, 0, width - 10, height - 5);
            ImageIO.write(sub, "png", new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return Boolean.TRUE;
    }
}
