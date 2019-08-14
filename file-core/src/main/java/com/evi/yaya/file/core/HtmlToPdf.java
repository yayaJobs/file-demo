package com.evi.yaya.file.core;

import com.evi.yaya.file.freeMarker.FreeMarkerUtil;
import com.evi.yaya.file.core.bean.Customer;
import com.evi.yaya.file.core.bean.Machine;
import com.evi.yaya.file.iText.ITextUtil;
import com.evi.yaya.file.jfreechart.JFreeChartUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：TODO
 *
 * @Title: HtmlToPdf
 * @Package com.evi.yaya.file.core
 * @See: [] </br>
 * @Copyright: Copyright (c) 2017 </br>
 * @Company: sany huax witsight team by product</br>
 * @author: wangzy
 * @date: 2019/8/13 17:18
 * @version: V1.0
 */
public class HtmlToPdf {
    public static void main(String[] args) {
        //jfreeChart生成图表图片
        Map<String, Object> params = new HashMap<>();
        params.put("00点-04点", 100);
        params.put("04点-08点", 200);
        params.put("08点-12点", 300);
        params.put("12点-16点", 400);
        params.put("16点-20点", 500);
        params.put("20点-24点", 600);
        Font font = new Font("宋体", Font.BOLD, 12);
        String pieChartImagePath = "E:\\data\\pdf\\images\\pieChartImage.jpg";
        JFreeChartUtil.createPieChartAsJPEG("今日放屁次数:时间段分布图", params, font, font, 600, 300, pieChartImagePath);
        String ringChartImagePath = "E:\\data\\pdf\\images\\ringChartImage.jpg";
        String ringChartImageTempPath = "E:\\data\\pdf\\images\\ringChartImageTemp.jpg";
        String[] names = {"私募股权", "房地产", "基金", "现金宝宝", "股票"};
        int[] values = {20, 20, 20, 30, 10};
        JFreeChartUtil.createRingChartAsPNG("投资种类占比", names, values, font, font, 600, 300, ringChartImageTempPath, ringChartImagePath);
        String multiplePieChartImagePath = "E:\\data\\pdf\\images\\multiplePieChartImage.jpg";
        String rowKeys[] = {"社科类", "文学类", "体育类", "少儿类"};
        String columnKeys[] = {"第1周", "第2周", "第3周", "第4周"};
        int[][] multipleValues = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        JFreeChartUtil.createMultiplePieChartAsPNG("各州图书销量", rowKeys, columnKeys, multipleValues, font, font, 800, 600, multiplePieChartImagePath);
        String barChartImagePath = "E:\\data\\pdf\\images\\barChartImage.jpg";
        String[] colKeys = {"1987", "1997", "2007", "2017"};
        double[][] data = {
                {50, 20, 30, 80.25},
                {20, 10D, 40D, 41.53},
                {40, 30.0008D, 38.24D, 23.2},
                {77, 85, 45, 68}
        };
        JFreeChartUtil.createBarChartAsPNG("阶段年图书销量", "年份", "数量：万册", rowKeys, colKeys, data, font, font, 800, 600, barChartImagePath);
        String stackedBarChartImagePath = "E:\\data\\pdf\\images\\stackedBarChartImage.jpg";
        JFreeChartUtil.createStackedBarChart("阶段年图书销量", "年份", "数量：万册", rowKeys, colKeys, data, font, font, 800, 600, stackedBarChartImagePath);

        //freeMarker转化模板to html
        Map<String, Object> parmas = new HashMap<>();
        Customer customer = new Customer();
        customer.setName("Test");
        Machine machine = new Machine();
        machine.setSerialNo("85541256ASDDAS4545");
        machine.setTypeName("第五型号");
        parmas.put("customer", customer);
        parmas.put("machine", machine);
        parmas.put("pieChartImagePath", pieChartImagePath);
        parmas.put("ringChartImagePath", ringChartImagePath);
        parmas.put("multiplePieChartImagePath", multiplePieChartImagePath);
        parmas.put("barChartImagePath", barChartImagePath);
        parmas.put("stackedBarChartImagePath", stackedBarChartImagePath);
        FreeMarkerUtil.processTemplate(parmas, "freeMarker01.html", "E:\\data\\pdf\\html\\freeMarker01.html");
        //itext html to pdf
        ITextUtil.parseXHtml("E:\\data\\pdf\\html\\freeMarker01.html", "E:\\data\\pdf\\result\\freeMarker01.pdf");
    }
}
