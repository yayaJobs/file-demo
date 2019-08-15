package com.evi.yaya.file.jfreechart;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 说明：TODO
 *
 * @Title: PieDatasetUtil
 * @Package com.evi.yaya.file.jfreechart
 * @See: [] </br>
 * @Copyright: Copyright (c) 2017 </br>
 * @Company: sany huax witsight team by product</br>
 * @author: wangzy
 * @date: 2019/8/14 14:54
 * @version: V1.0
 */
public class PieDatasetUtil {

    public static PieDataset createPieDataset(Map<String, Object> params) {
        if (null == params) {
            return null;
        }
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (String key : params.keySet()) {
            dataSet.setValue(key, (Number) params.get(key));
        }
        return dataSet;
    }

    //String[] key = { "私募股权","房地产","基金","现金宝宝","股票" };
    //int[] value = { 20, 20, 20, 30, 10 };
    public static PieDataset createPieDataset(String[] names, int[] values) {
        if (names == null || values == null || names.length != values.length) {
            return null;
        }
        DefaultPieDataset dataSet = new DefaultPieDataset();
        for (int i = 0; i < names.length; i++) {
            dataSet.setValue(names[i], values[i]);
        }
        return dataSet;
    }

    //String rowKeys[] = { "社科类", "文学类", "体育类", "少儿类" }
    //String columnKeys[] = { "第1周", "第2周", "第3周", "第4周" };
    public static CategoryDataset createCategoryDataset(String[] rowKeys, String[] columnKeys, int[][] values) {
        if (null == values || null == rowKeys || null == columnKeys || values.length != rowKeys.length || columnKeys.length != values[0].length) {
            return null;
        }
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
        for (int i = 0; i < rowKeys.length; i++) {
            for (int j = 0; j < columnKeys.length; j++) {
                categoryDataset.addValue(values[i][j], rowKeys[i], columnKeys[j]);
            }
        }
        return categoryDataset;
    }

    public static CategoryDataset createCategoryDataset(String[] rowKeys, String[] colKeys, double[][] data) {
        return DatasetUtils.createCategoryDataset(rowKeys, colKeys, data);
    }

    public static IntervalCategoryDataset createSampleDataset() {
        final TaskSeries s1 = new TaskSeries("Scheduled");
        s1.add(new Task("Write Proposal",
                new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                        date(5, Calendar.APRIL, 2001))));
        s1.add(new Task("Obtain Approval",
                new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
                        date(9, Calendar.APRIL, 2001))));
        s1.add(new Task("Requirements Analysis",
                new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                        date(5, Calendar.MAY, 2001))));
        s1.add(new Task("Design Phase",
                new SimpleTimePeriod(date(6, Calendar.MAY, 2001),
                        date(30, Calendar.MAY, 2001))));
        s1.add(new Task("Design Signoff",
                new SimpleTimePeriod(date(2, Calendar.JUNE, 2001),
                        date(2, Calendar.JUNE, 2001))));
        s1.add(new Task("Alpha Implementation",
                new SimpleTimePeriod(date(3, Calendar.JUNE, 2001),
                        date(31, Calendar.JULY, 2001))));
        s1.add(new Task("Design Review",
                new SimpleTimePeriod(date(1, Calendar.AUGUST, 2001),
                        date(8, Calendar.AUGUST, 2001))));
        s1.add(new Task("Revised Design Signoff",
                new SimpleTimePeriod(date(10, Calendar.AUGUST, 2001),
                        date(10, Calendar.AUGUST, 2001))));
        s1.add(new Task("Beta Implementation",
                new SimpleTimePeriod(date(12, Calendar.AUGUST, 2001),
                        date(12, Calendar.SEPTEMBER, 2001))));
        s1.add(new Task("Testing",
                new SimpleTimePeriod(date(13, Calendar.SEPTEMBER, 2001),
                        date(31, Calendar.OCTOBER, 2001))));
        s1.add(new Task("Final Implementation",
                new SimpleTimePeriod(date(1, Calendar.NOVEMBER, 2001),
                        date(15, Calendar.NOVEMBER, 2001))));
        s1.add(new Task("Signoff",
                new SimpleTimePeriod(date(28, Calendar.NOVEMBER, 2001),
                        date(30, Calendar.NOVEMBER, 2001))));
        final TaskSeries s2 = new TaskSeries("Actual");
        s2.add(new Task("Write Proposal",
                new SimpleTimePeriod(date(1, Calendar.APRIL, 2001),
                        date(5, Calendar.APRIL, 2001))));
        s2.add(new Task("Obtain Approval",
                new SimpleTimePeriod(date(9, Calendar.APRIL, 2001),
                        date(9, Calendar.APRIL, 2001))));
        s2.add(new Task("Requirements Analysis",
                new SimpleTimePeriod(date(10, Calendar.APRIL, 2001),
                        date(15, Calendar.MAY, 2001))));
        s2.add(new Task("Design Phase",
                new SimpleTimePeriod(date(15, Calendar.MAY, 2001),
                        date(17, Calendar.JUNE, 2001))));
        s2.add(new Task("Design Signoff",
                new SimpleTimePeriod(date(30, Calendar.JUNE, 2001),
                        date(30, Calendar.JUNE, 2001))));
        s2.add(new Task("Alpha Implementation",
                new SimpleTimePeriod(date(1, Calendar.JULY, 2001),
                        date(12, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Design Review",
                new SimpleTimePeriod(date(12, Calendar.SEPTEMBER, 2001),
                        date(22, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Revised Design Signoff",
                new SimpleTimePeriod(date(25, Calendar.SEPTEMBER, 2001),
                        date(27, Calendar.SEPTEMBER, 2001))));
        s2.add(new Task("Beta Implementation",
                new SimpleTimePeriod(date(27, Calendar.SEPTEMBER, 2001),
                        date(30, Calendar.OCTOBER, 2001))));
        s2.add(new Task("Testing",
                new SimpleTimePeriod(date(31, Calendar.OCTOBER, 2001),
                        date(17, Calendar.NOVEMBER, 2001))));
        s2.add(new Task("Final Implementation",
                new SimpleTimePeriod(date(18, Calendar.NOVEMBER, 2001),
                        date(5, Calendar.DECEMBER, 2001))));
        s2.add(new Task("Signoff",
                new SimpleTimePeriod(date(10, Calendar.DECEMBER, 2001),
                        date(11, Calendar.DECEMBER, 2001))));
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
        collection.add(s2);
        return collection;
    }

    private static Date date(final int day, final int month, final int year) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        final Date result = calendar.getTime();
        return result;
    }

    public static XYDataset createXYDataset(){
        XYSeriesCollection data = new XYSeriesCollection();
        XYSeries series1 = createRandomData("图案一", 75.0, 10.0);
        XYSeries series2 = createRandomData("图案二", 50.0, 5.0);
        XYSeries series3 = createRandomData("图案三", 25.0, 1.0);
        data.addSeries(series1);
        data.addSeries(series2);
        data.addSeries(series3);
        return data;
    }

    private static XYSeries createRandomData(final String name,
                                             final double baseRadius,
                                             final double thetaInc) {
        final XYSeries series = new XYSeries(name);
        for (double theta = 0.0; theta < 360.0; theta += thetaInc) {
            final double radius = baseRadius * (1.0 + Math.random());
            series.add(theta, radius);
        }
        return series;
    }
}
