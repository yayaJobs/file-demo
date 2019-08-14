package com.evi.yaya.file.jfreechart;

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
}
