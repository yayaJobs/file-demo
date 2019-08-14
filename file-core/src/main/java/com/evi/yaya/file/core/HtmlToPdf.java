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
        JFreeChartUtil.createRingChartAsPNG(names, values, font, 600, 300, ringChartImageTempPath, ringChartImagePath);
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
        FreeMarkerUtil.processTemplate(parmas, "freeMarker01.html", "E:\\data\\pdf\\html\\freeMarker01.html");
        //itext html to pdf
        ITextUtil.parseXHtml("E:\\data\\pdf\\html\\freeMarker01.html", "E:\\data\\pdf\\result\\freeMarker01.pdf");
    }
}
