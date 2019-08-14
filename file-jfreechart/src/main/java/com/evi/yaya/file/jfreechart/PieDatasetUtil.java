package com.evi.yaya.file.jfreechart;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

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
}
