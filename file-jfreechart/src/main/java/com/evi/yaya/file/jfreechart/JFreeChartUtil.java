package com.evi.yaya.file.jfreechart;

import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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
        DefaultPieDataset pds = new DefaultPieDataset();
        for (String key : params.keySet()) {
            pds.setValue(key, (Number) params.get(key));
        }
        // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
        JFreeChart chart = ChartFactory.createPieChart(title, pds, false, false, true);
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
}
